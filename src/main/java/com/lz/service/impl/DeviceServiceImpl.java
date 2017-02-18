package com.lz.service.impl;

import com.google.common.collect.Lists;
import com.lz.dto.DeviceRentDto;
import com.lz.mapper.DeviceMapper;
import com.lz.mapper.DeviceRentDetailMapper;
import com.lz.mapper.DeviceRentDocMapper;
import com.lz.mapper.DeviceRentMapper;
import com.lz.pojo.Device;
import com.lz.pojo.DeviceRent;
import com.lz.pojo.DeviceRentDetail;
import com.lz.pojo.DeviceRentDoc;
import com.lz.service.DeviceService;
import com.lz.util.SerialNumberUtil;
import com.lz.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
            DeviceRentDetail rentDetail=new DeviceRentDetail();
            rentDetail.setId(bean.getId());
            rentDetail.setTotalprice(bean.getTotal());
            rentDetail.setDevicename(bean.getDevicename());
            rentDetail.setDeviceprice(bean.getPrice());
            rentDetail.setDeviceunit(bean.getUnit());
            rentDetail.setNum(bean.getNum());
            rentDetail.setRentid(deviceRent.getId());

            total+=bean.getPrice();
            detailList.add(rentDetail);
        }
        if(!detailList.isEmpty()){
//            批量插入
            detailMapper.batchSave(detailList);
        }

        total=total* deviceRent.getTotalday();
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
}
