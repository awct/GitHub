package com.company.dao;

import com.company.domain.Food;

import java.util.List;

public interface FoodDao {
    List<Food> selectByBusinessId(int businessId);
    int insert(Food food);
    int update(Food food);
    int delete(Integer foodId,Integer businessId);
    //属于当前商家下的食品编号
    //使得商户不能直接利用编号查询到其他商家的食品编号
    Food getByIdAndBusinessId(Integer foodId,Integer businessId);

}
