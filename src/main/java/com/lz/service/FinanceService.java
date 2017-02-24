package com.lz.service;

import com.lz.pojo.Finance;

import java.util.List;
import java.util.Map;

public interface FinanceService {

    void save(Finance finance);

    List<Finance> findFinanceByParam(Map<String, Object> queryParam);

    Long count();

    Long filterCount(Map<String, Object> queryParam);

    void updateState(Integer id);

    List<Finance> findByCreateDate(String date);
}
