package com.lz.service;

import com.lz.pojo.Device;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    List<Device> findAll();
    void addNewDevice(Device device);

    List<Device> findDeviceByParam(Map<String, Object> searchParam);

    Long count();

    Long filterCount(Map<String, Object> searchParam);

    Device findDeviceById(Integer id);
}
