package com.company.view;

import com.company.domain.Food;

import java.util.List;

public interface FoodView {
    List<Food> selectByBusinessId(Integer businessId);
    int insert(Integer businessId);
    int delete(Integer businessId);
    int update(Integer businessId);
}
