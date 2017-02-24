package com.lz.service.impl;

import com.google.common.collect.Lists;
import com.lz.dto.DeviceRentDto;
import com.lz.exception.NotFoundException;
import com.lz.mapper.DeviceMapper;
import com.lz.mapper.DeviceRentDetailMapper;
import com.lz.mapper.DeviceRentDocMapper;
import com.lz.mapper.DeviceRentMapper;
import com.lz.pojo.*;
import com.lz.service.DeviceService;
import com.lz.service.FinanceService;
import com.lz.util.SerialNumberUtil;
import com.lz.util.ShiroUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceRentMapper rentMapper;
    @Autowired
    private DeviceRentDetailMapper detailMapper;
    @Autowired
    private DeviceRentDocMapper docMapper;
    @Autowired
    private FinanceService financeService;

    @Value("${upload.path}")
    private String filePath;

    @Override
    public List<Device> findAll() {
        List<Device> list= deviceMapper.findAll();
        return list;
    }

    @Override
    public void addNewDevice(Device device) {
        device.setCurrentnum(device.getTotalnum());
        deviceMapper.addNewDevice(device);
    }

    @Override
    public List<Device> findDeviceByParam(Map<String, Object> searchParam) {
        return deviceMapper.findDeviceBySearchParam(searchParam);
    }

    @Override
    public Long count() {
        return deviceMapper.count();
    }

    @Override
    public Long filterCount(Map<String, Object> searchParam) {
        return deviceMapper.filterCount(searchParam);
    }

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findByDeviceId(id);
    }

    @Override
    @Transactional
    public String saveRent(DeviceRentDto deviceRentDto) {
        DeviceRent deviceRent=new DeviceRent();
        deviceRent.setCompanyname(deviceRentDto.getCompanyname());
        deviceRent.setLinkman(deviceRentDto.getLinkman());
        deviceRent.setCardnum(deviceRentDto.getCardnum());
        deviceRent.setTel(deviceRentDto.getTel());
        deviceRent.setAddress(deviceRentDto.getAddress());
        deviceRent.setFax(deviceRentDto.getFax());
        deviceRent.setRentdate(deviceRentDto.getRentdate());
        deviceRent.setBackdate(deviceRentDto.getBackdate());
        deviceRent.setTotalday(deviceRentDto.getTotaldate());
        deviceRent.setTotalprice(0F);
        deviceRent.setPrecost(0F);
        deviceRent.setLastcost(0F);
        deviceRent.setCreateuser(ShiroUtil.getCurrentUserName());
        deviceRent.setSerialnumber(SerialNumberUtil.getSerialNumber());
        rentMapper.save(deviceRent);

//        保存合同详情
        List<DeviceRentDto.DeviceArrayBean> deviceArray=deviceRentDto.getDeviceArray();
        List<DeviceRentDetail> detailList= Lists.newArrayList();
        float total=0F;
        for(DeviceRentDto.DeviceArrayBean bean:deviceArray){
            Device device=deviceMapper.findByDeviceId(bean.getId());
            if(device.getCurrentnum()<bean.getNum()){
                throw new RuntimeException(device.getName()+"库存不足");
            }else{
                device.setCurrentnum(device.getCurrentnum()-bean.getNum());
                deviceMapper.updateCurrentNum(device);
            }
            DeviceRentDetail rentDetail=new DeviceRentDetail();
            rentDetail.setId(bean.getId());
            rentDetail.setTotalprice(bean.getTotal());
            rentDetail.setDevicename(bean.getDevicename());
            rentDetail.setDeviceprice(bean.getPrice());
            rentDetail.setDeviceunit(bean.getUnit());
            rentDetail.setNum(bean.getNum());
            rentDetail.setRentid(deviceRent.getId());

            total+=bean.getPrice()*bean.getNum();
            detailList.add(rentDetail);
        }
        if(!detailList.isEmpty()){
//            批量插入
            detailMapper.batchSave(detailList);
        }

        total=total* deviceRent.getTotalday().intValue();
        float precost=total*0.3F;
        float lastcost=total*0.7F;
        rentMapper.update(total,precost,lastcost,deviceRent.getId());

//        保存文件
        List<DeviceRentDto.FileArrayBean> beanList=deviceRentDto.getFileArray();
        List<DeviceRentDoc> rentDocList=Lists.newArrayList();
        for(DeviceRentDto.FileArrayBean bean:beanList){
            DeviceRentDoc rentDoc=new DeviceRentDoc();
            rentDoc.setFilename(bean.getNewFileName());
            rentDoc.setSourcename(bean.getSourceName());
            rentDoc.setRentid(deviceRent.getId());
            rentDocList.add(rentDoc);
        }
        if(!rentDocList.isEmpty()){
            docMapper.batchSave(rentDocList);
        }

//        写入财务报表
        Finance finance=new Finance();
        finance.setSerialnumber(SerialNumberUtil.getSerialNumber());
        finance.setType(Finance.INCOME);//是收入还是支出
        finance.setMoney(precost);//预付款或者尾款
        finance.setState(Finance.PAY_NOT);//是否缴纳
        finance.setModule(Finance.DEVICERENT_MODULE);//哪个模块(部门)
        finance.setCreateuser(ShiroUtil.getCurrentUserName());
        finance.setCreatedate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setConfirmuser(ShiroUtil.getCurrentUserName());
        finance.setConfirmdate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setRemark(Finance.PRECOST);//备注,合同类型
        finance.setRentserialnumber(deviceRent.getSerialnumber());
        financeService.save(finance);


        return deviceRent.getSerialnumber();
    }

    @Override
    public DeviceRent findDeviceRentBySerialNumber(String serialNumber) {
        return rentMapper.findDeviceRentBySerial(serialNumber);
    }

    @Override
    public List<DeviceRentDetail> findDeviceDetailListByRentId(Integer id) {
        return detailMapper.findDeviceDetailListByRentId(id);
    }

    @Override
    public List<DeviceRentDoc> findDeviceDocListByRentId(Integer id) {
        return docMapper.findDeviceDocListByRentId(id);
    }

    @Override
    public InputStream downloadFile(Integer docId) throws IOException {
        DeviceRentDoc rentDoc=docMapper.findById(docId);
        if(rentDoc==null){
            return null;
        }else{
            File file=new File(filePath+"/"+rentDoc.getFilename());
            if(file.exists()){
                return new FileInputStream(file);
            }
            return null;
        }
    }

    @Override
    public DeviceRentDoc findDeviceDocById(Integer id) {
        return docMapper.findById(id);
    }

    @Override
    public DeviceRent findDeviceRentById(Integer rentId) {
        return rentMapper.findDeviceById(rentId);
    }

    @Override
    public void downloadZipFile(DeviceRent rent, ZipOutputStream zipOutputStream) throws IOException {
        List<DeviceRentDoc> rentDocList=findDeviceDocListByRentId(rent.getId());
        for(DeviceRentDoc rentDoc:rentDocList){
            ZipEntry entry=new ZipEntry(rentDoc.getSourcename());
            zipOutputStream.putNextEntry(entry);

            InputStream inputStream=downloadFile(rentDoc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    @Override
    public List<DeviceRent> findDeviceRentByParam(Map<String, Object> map) {
        List<DeviceRent> deviceRentList=rentMapper.findDevicerentByParam(map);
        return deviceRentList;
    }

    @Override
    public Long deviceRentCount() {
        return rentMapper.count();
    }

    @Override
    public void rentChangeState(Integer id) {
        DeviceRent rent=findDeviceRentById(id);

        rent.setState("已完成");
        rentMapper.updateState(rent);
    }
}
