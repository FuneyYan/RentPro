package com.lz.mapper;

import com.lz.pojo.WorkRentDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
@Component
public interface WorkRentDetailMapper {

    void batchSave(List<WorkRentDetail> detailList);

    List<WorkRentDetail> findDeviceRentDetailListByRentId(Integer id);
}
