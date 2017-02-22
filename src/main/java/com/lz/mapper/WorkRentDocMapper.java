package com.lz.mapper;

import com.lz.pojo.WorkRentDoc;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WorkRentDocMapper {

    void batchSave(List<WorkRentDoc> rentDocList);

    List<WorkRentDoc> findDeviceRentDocListByRentId(Integer id);

    WorkRentDoc findById(Integer id);
}
