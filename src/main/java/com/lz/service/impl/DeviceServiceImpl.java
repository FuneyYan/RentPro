package com.lz.service.impl;

import com.lz.mapper.DeviceMapper;
import com.lz.pojo.Device;
import com.lz.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

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
}
