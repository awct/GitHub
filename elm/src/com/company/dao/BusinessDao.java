package com.company.dao;

import com.company.domain.Business;

import java.util.List;

public interface BusinessDao {
    List<Business> selectByAll();
    int insert(Business business);
    int delete(Integer id);
    int update(Business business);
    Business selectById(Integer id);
    List<Business> selectByNameAndAddress(String name,String address);
    Business getByIdAndPassword(Integer id,String password);

    int updatePassword(Integer businessId, String password);
}
