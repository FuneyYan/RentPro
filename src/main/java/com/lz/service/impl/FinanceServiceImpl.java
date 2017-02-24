package com.lz.service.impl;

import com.lz.exception.NotFoundException;
import com.lz.mapper.FinanceMapper;
import com.lz.pojo.Finance;
import com.lz.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public void save(Finance finance) {
        financeMapper.save(finance);
    }

    @Override
    public List<Finance> findFinanceByParam(Map<String, Object> queryParam) {
        return financeMapper.findFinanceByParam(queryParam);
    }

    @Override
    public Long count() {
        return financeMapper.count();
    }

    @Override
    public Long filterCount(Map<String, Object> queryParam) {
        return financeMapper.filterCount(queryParam);
    }

    @Override
    public void updateState(Integer id) {

        Finance finance=financeMapper.findFinanceById(id);
        if(finance==null){
            throw new NotFoundException();
        }else{
            finance.setState(Finance.PAY_YET);
            financeMapper.update(finance);
        }
    }

    @Override
    public List<Finance> findByCreateDate(String date) {
        return financeMapper.findByCreateDate(date);
    }
}
