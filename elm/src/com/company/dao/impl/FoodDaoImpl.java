package com.company.dao.impl;

import com.company.dao.FoodDao;
import com.company.domain.Food;
import com.company.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    Connection conn=null;

    @Override
    public List<Food> selectByBusinessId(int businessId) {
        ArrayList<Food> list=new ArrayList<>();
        String sql="select* from food where businessId=?";
        PreparedStatement preparedStatement=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,businessId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String foodName = rs.getString(2);
                String explain = rs.getString(3);
                BigDecimal price = rs.getBigDecimal(4);

                Food food=new Food();
                food.setFoodId(id);
                food.setFoodName(foodName);
                food.setFoodExplain(explain);
                food.setFoodPrice(price);
                food.setBusinessId(businessId);
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int insert(Food food) {
        String sql="insert into food(foodName,foodExplain,foodPrice,businessId) values(?,?,?,?)";
        PreparedStatement preparedStatement=null;
        int result=0;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,food.getFoodName());
            preparedStatement.setString(2,food.getFoodExplain());
            preparedStatement.setBigDecimal(3,food.getFoodPrice());
            preparedStatement.setInt(4,food.getBusinessId());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int update(Food food) {
        String sql="update food set foodName=?,foodExplain=?,foodPrice=? where foodId=?";
        PreparedStatement preparedStatement=null;
        int result=0;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,food.getFoodName());
            preparedStatement.setString(2,food.getFoodExplain());
            preparedStatement.setBigDecimal(3,food.getFoodPrice());
            preparedStatement.setInt(4,food.getFoodId());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int delete(Integer foodId,Integer businessId) {
        //使得商户不能直接利用编号删除到其他商家的食品编号
        String sql="delete from food where foodId=? and businessId=?";
        PreparedStatement preparedStatement=null;
        int result=0;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,foodId);
            preparedStatement.setInt(2,businessId);
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Food getByIdAndBusinessId(Integer foodId,Integer businessId) {
        //使得商户不能直接利用编号查询到其他商家的食品编号
        String sql="select* from food where foodId=? and businessId=?";
        PreparedStatement preparedStatement=null;
        Food food=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,foodId);
            preparedStatement.setInt(2,businessId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String explain = resultSet.getString(3);
                BigDecimal price = resultSet.getBigDecimal(4);
                food=new Food();
                food.setFoodId(id);
                food.setFoodName(name);
                food.setFoodExplain(explain);
                food.setFoodPrice(price);
                food.setBusinessId(businessId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return food;
    }
}
