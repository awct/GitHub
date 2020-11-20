package com.company.dao.impl;

import com.company.dao.BusinessDao;
import com.company.domain.Business;
import com.company.utils.JDBCUtils;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImp implements BusinessDao {
    Connection conn=null;
    @Override
    public List<Business> selectByAll() {
        ArrayList<Business> list=new ArrayList<>();
        String sql="select* from business";
        PreparedStatement preparedStatement=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String businessName = rs.getString(3);
                String address = rs.getString(4);
                String explain = rs.getString(5);
                Business business=new Business();
                business.setBusinessId(id);
                business.setBusinessName(businessName);
                business.setBusinessAddress(address);
                business.setBusinessExplain(explain);
                list.add(business);
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
    public int insert(Business business) {
        String sql="insert into business values(null,?,?,?,?,?,?)";
        int id=-1;
        PreparedStatement preparedStatement=null;
        try {
            Connection conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,business.getPassword());
            preparedStatement.setString(2,business.getBusinessName());
            preparedStatement.setString(3,business.getBusinessAddress());
            preparedStatement.setString(4,business.getBusinessExplain());
            preparedStatement.setBigDecimal(5,business.getStarPrice());
            preparedStatement.setBigDecimal(6,business.getDeliveryPrice());

            preparedStatement.executeUpdate();
            //自增id
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public int delete(int id) {
        String sql="delete from business where businessId=?";
        int result=1;
        PreparedStatement preparedStatement=null;

        try {
            Connection conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                result=0;
                conn.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int update(Business business) {
        String sql="update business set password=?,businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
        int row=0;
        PreparedStatement preparedStatement=null;
        try {
            Connection conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,business.getPassword());
            preparedStatement.setString(2,business.getBusinessName());
            preparedStatement.setString(3,business.getBusinessAddress());
            preparedStatement.setString(4,business.getBusinessExplain());
            preparedStatement.setBigDecimal(5,business.getStarPrice());
            preparedStatement.setBigDecimal(6,business.getDeliveryPrice());
            preparedStatement.setInt(7,business.getBusinessId());
            row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    @Override
    public Business selectById(int id) {
        String sql="select * from business where businessId=?";
        Business business=null;
        PreparedStatement preparedStatement=null;
        try {
            Connection conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                business=new Business();
                business.setBusinessId(resultSet.getInt(1));
                business.setPassword(resultSet.getString(2));
                business.setBusinessName(resultSet.getString(3));
                business.setBusinessAddress(resultSet.getString(4));
                business.setBusinessExplain(resultSet.getString(5));
                business.setStarPrice(resultSet.getBigDecimal(6));
                business.setDeliveryPrice(resultSet.getBigDecimal(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCUtils.close(preparedStatement,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return business;
    }
}
