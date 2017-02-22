package com.lz.mapper;

import com.lz.pojo.WorkRent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface WorkRentMapper {

    void save(WorkRent workRent);

    void updateCost(@Param("total") float total, @Param("preCost") float preCost,
                    @Param("lastCost") float lastCost, @Param("id") Integer id);

    WorkRent findDeviceRentBySerialNumber(String serialNumber);

    WorkRent findById(Integer id);
}
