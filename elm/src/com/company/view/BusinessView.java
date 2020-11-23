package com.company.view;

import com.company.domain.Business;

import java.util.List;

public interface BusinessView {
    List<Business> selectByAll();
    int insert();
    int delete();
    int updateById(Integer businessId);
    int updatePassword(Integer businessId);
    Business selectById(Integer id);
    List<Business> selectByNameAndAddress();
    Business login();
}
