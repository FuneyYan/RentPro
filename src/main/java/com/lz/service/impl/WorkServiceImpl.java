package com.lz.service.impl;

import com.google.common.collect.Lists;
import com.lz.dto.WorkRentDto;
import com.lz.exception.NotFoundException;
import com.lz.mapper.WorkRentDetailMapper;
import com.lz.mapper.WorkRentDocMapper;
import com.lz.mapper.WorkRentMapper;
import com.lz.mapper.WorkTypeMapper;
import com.lz.pojo.*;
import com.lz.service.WorkService;
import com.lz.util.SerialNumberUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkTypeMapper workTypeMapper;

    @Autowired
    private WorkRentMapper rentMapper;

    @Autowired
    private WorkRentDetailMapper detailMapper;

    @Autowired
    private WorkRentDocMapper docMapper;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<WorkType> findAll() {
        return workTypeMapper.findAll();
    }

    @Override
    public WorkType findWorkById(Integer id) {
        return workTypeMapper.findWorkById(id);
    }

    @Override
    @Transactional
    public String saveRent(WorkRentDto workRentDto) {
        WorkRent workRent=new WorkRent();
        workRent.setAddress(workRentDto.getAddress());
        workRent.setCardnum(workRentDto.getCardNum());
        workRent.setCompanyname(workRentDto.getCompanyName());
        workRent.setCompanytel(workRentDto.getCompanyTel());
        workRent.setLastcost(0F);
        workRent.setTel(workRentDto.getTel());
        workRent.setLinkman(workRentDto.getLinkMan());
        workRent.setPrecost(0F);
        workRent.setTotalprice(0F);
        workRent.setSerialnumber(SerialNumberUtil.getSerialNumber());

        rentMapper.save(workRent);


        List<WorkRentDto.WorkArrayBean> workArray  = workRentDto.getWorkArray();
        List<WorkRentDetail> detailList = Lists.newArrayList();
        float total = 0F;
        for(WorkRentDto.WorkArrayBean bean : workArray) {
            //查询当前设备库存是否足够
            WorkType workType = workTypeMapper.findWorkById(bean.getId());
            if(workType.getCurrentnum() < bean.getNum()) {
                throw new RuntimeException(workType.getName()+"人手不够");
            } else {
                workType.setCurrentnum(workType.getCurrentnum() - bean.getNum());
                workTypeMapper.updateCurrentNum(workType);
            }


            WorkRentDetail rentDetail = new WorkRentDetail();
            rentDetail.setWorkname(bean.getName());
            rentDetail.setTotalprice(bean.getTotal());
            rentDetail.setWorkprice(bean.getPrice());
            rentDetail.setWorknum(bean.getNum());
            rentDetail.setRentid(workRent.getId());

            detailList.add(rentDetail);

            total += bean.getPrice()*bean.getNum();
        }
        if(!detailList.isEmpty()) {
            detailMapper.batchSave(detailList);
        }

        //计算合同总价及预付款、尾款
        float preCost = total  * 0.3F;
        float lastCost = total - preCost;
        rentMapper.updateCost(total,preCost,lastCost,workRent.getId());

        //3. 保存文件
        List<WorkRentDto.FileArrayBean> docBeanList = workRentDto.getFileArray();
        List<WorkRentDoc> rentDocList = Lists.newArrayList();
        for(WorkRentDto.FileArrayBean doc : docBeanList) {
            WorkRentDoc rentDoc = new WorkRentDoc();
            rentDoc.setRentid(workRent.getId());
            rentDoc.setFilename(doc.getNewFileName());
            rentDoc.setSourcename(doc.getSourceName());

            rentDocList.add(rentDoc);
        }
        if(!rentDocList.isEmpty()) {
            docMapper.batchSave(rentDocList);
        }


        return workRent.getSerialnumber();
    }

    @Override
    public WorkRent findDeviceRentBySerialNumber(String serialNumber) {
        return rentMapper.findDeviceRentBySerialNumber(serialNumber);
    }

    @Override
    public List<WorkRentDetail> findDeviceRentDetailListByRentId(Integer id) {
        return detailMapper.findDeviceRentDetailListByRentId(id);
    }

    @Override
    public List<WorkRentDoc> findDeviceRentDocListByRentId(Integer id) {
        return docMapper.findDeviceRentDocListByRentId(id);
    }

    @Override
    public InputStream downloadFile(Integer id) throws FileNotFoundException {
        WorkRentDoc doc = docMapper.findById(id);
        if(doc == null) {
            return null;
        } else {
            File file = new File(new File(uploadPath),doc.getSourcename());
            if(file.exists()) {
                return new FileInputStream(file);
            } else {
                return null;
            }
        }
    }

    @Override
    public WorkRentDoc findDeviceRentDocById(Integer id) {
        return docMapper.findById(id);
    }

    @Override
    public WorkRent findDeviceRentById(Integer id) {
        return rentMapper.findById(id);
    }

    @Override
    public void downloadZipFile(WorkRent rent, ZipOutputStream zipOutputStream) throws IOException {
        List<WorkRentDoc> deviceRentDocs = findDeviceRentDocListByRentId(rent.getId());
        for(WorkRentDoc doc : deviceRentDocs) {
            ZipEntry entry = new ZipEntry(doc.getSourcename());
            zipOutputStream.putNextEntry(entry);

            InputStream inputStream = downloadFile(doc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }

        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }


}
