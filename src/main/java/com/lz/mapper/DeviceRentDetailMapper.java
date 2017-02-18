package com.lz.mapper;

import com.lz.pojo.DeviceRentDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceRentDetailMapper {

    void batchSave(List<DeviceRentDetail> detailList);
}
