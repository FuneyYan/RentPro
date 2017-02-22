package com.lz.mapper;

import com.lz.pojo.WorkType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WorkTypeMapper {

    List<WorkType> findAll();

    WorkType findWorkById(Integer id);

    WorkType findById(String id);

    void updateCurrentNum(WorkType workType);
}
