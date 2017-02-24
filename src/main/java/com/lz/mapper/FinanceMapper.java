package com.lz.mapper;


import com.lz.pojo.Finance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface FinanceMapper {

    void save(Finance finance);

    List<Finance> findFinanceByParam(Map<String, Object> queryParam);

    Long count();

    Long filterCount(Map<String, Object> queryParam);

    Finance findFinanceById(Integer id);

    void update(Finance finance);

    List<Finance> findByCreateDate(String date);

}
