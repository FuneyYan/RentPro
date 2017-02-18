package com.lz.mapper;

import com.lz.pojo.DeviceRent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DeviceRentMapper {

    void save(DeviceRent deviceRent);

    void update(@Param("total") float total,
                @Param("precost") float precost,
                @Param("lastcost") float lastcost,
                @Param("id") Integer id);

    DeviceRent findDeviceRentBySerial(String serialNumber);
}
