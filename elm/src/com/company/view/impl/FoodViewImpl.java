package com.company.view.impl;

import com.company.dao.FoodDao;
import com.company.dao.impl.FoodDaoImpl;
import com.company.domain.Food;
import com.company.view.FoodView;

import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    FoodDao foodDao=new FoodDaoImpl();
    Scanner sc =new Scanner(System.in);

    @Override
    public List<Food> selectByBusinessId(Integer businessId) {
        return foodDao.selectByBusinessId(businessId);
    }

    @Override
    public int insert(Integer businessId) {
        Food food=new Food();
        System.out.println("请输入食品名称");
        food.setFoodName(sc.next());
        System.out.println("请输入食品介绍");
        food.setFoodExplain(sc.next());
        System.out.println("请输入食品价格");
        food.setFoodPrice(sc.nextBigDecimal());
        food.setBusinessId(businessId);
        return foodDao.insert(food);
    }

    @Override
    public int delete(Integer businessId) {
        System.out.println("请输入食品编号");
        return foodDao.delete(sc.nextInt(),businessId);
    }

    @Override
    public int update(Integer businessId) {
        System.out.println("请输入需要修改的食品编号");
        int foodId = sc.nextInt();
        Food food = foodDao.getByIdAndBusinessId(foodId, businessId);
        if(food==null){
            System.out.println("您商户里没有此编号的食品");
            return 0;
        }
        System.out.println("请输入需要修改的信息属性（分隔符请用','逗号进行）例如：1,2\n1.食品名称\t2.食品介绍\t3.食品价格");
        String[] split = sc.next().split(",");
        for (String option : split) {
            switch (option){
                case "1":
                    System.out.println("请输入食品名称");
                    food.setFoodName(sc.next());
                    break;
                case "2":
                    System.out.println("请输入食品介绍");
                    food.setFoodExplain(sc.next());
                    break;
                case "3":
                    System.out.println("请输入价格");
                    food.setFoodPrice(sc.nextBigDecimal());
                    break;
            }
        }

        return foodDao.update(food);
    }


}
