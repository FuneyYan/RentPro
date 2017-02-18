package com.lz.mapper;

import com.lz.pojo.DeviceRentDoc;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceRentDocMapper {

    void batchSave(List<DeviceRentDoc> rentDocList);

    List<DeviceRentDoc> findDeviceDocListByRentId(Integer id);
}
