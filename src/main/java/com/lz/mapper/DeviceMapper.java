package com.lz.mapper;

import com.lz.dto.DeviceRentDto;
import com.lz.pojo.Device;
import com.lz.pojo.DeviceRent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DeviceMapper {
    List<Device> findAll();
    void addNewDevice(Device device);

    Long count();

    List<Device> findDeviceBySearchParam(Map<String, Object> searchParam);

    Long filterCount(Map<String, Object> searchParam);

    Device findByDeviceId(Integer id);

}
