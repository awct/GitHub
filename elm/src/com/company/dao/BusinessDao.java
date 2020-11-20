package com.company.dao;

import com.company.domain.Business;

import java.util.List;

public interface BusinessDao {
    List<Business> selectByAll();
    int insert(Business business);
    int delete(int id);
    int update(Business business);
    Business selectById(int id);
}
