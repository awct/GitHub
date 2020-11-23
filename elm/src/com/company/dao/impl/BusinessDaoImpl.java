package com.company.dao.impl;

import com.company.dao.BusinessDao;
import com.company.domain.Admin;
import com.company.domain.Business;
import com.company.utils.JDBCUtils;
import com.mysql.jdbc.Statement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
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
                String password=rs.getString(2);
                String businessName = rs.getString(3);
                String address = rs.getString(4);
                String explain = rs.getString(5);
                BigDecimal startPrice = rs.getBigDecimal(6);
                BigDecimal deliveryPrice = rs.getBigDecimal(7);
                Business business=new Business();
                business.setBusinessId(id);
                business.setPassword(password);
                business.setBusinessName(businessName);
                business.setBusinessAddress(address);
                business.setBusinessExplain(explain);
                business.setStarPrice(startPrice);
                business.setDeliveryPrice(deliveryPrice);
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
            conn = JDBCUtils.getConnection();
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
    public int delete(Integer id) {
        String sql="delete from business where businessId=?";
        int result=1;
        PreparedStatement preparedStatement=null;

        try {
            conn = JDBCUtils.getConnection();
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
        String sql="update business set businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
        int row=0;
        PreparedStatement preparedStatement=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,business.getBusinessName());
            preparedStatement.setString(2,business.getBusinessAddress());
            preparedStatement.setString(3,business.getBusinessExplain());
            preparedStatement.setBigDecimal(4,business.getStarPrice());
            preparedStatement.setBigDecimal(5,business.getDeliveryPrice());
            preparedStatement.setInt(6,business.getBusinessId());
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
    public Business selectById(Integer id) {
        String sql="select * from business where businessId=?";
        Business business=null;
        PreparedStatement preparedStatement=null;
        try {
            conn = JDBCUtils.getConnection();
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

    @Override
    public List<Business> selectByNameAndAddress(String name, String address) {
        List<Business> list=new ArrayList<>();
        StringBuilder sql=new StringBuilder("select*from business where 1=1 ");
        PreparedStatement preparedStatement=null;
        if(name!=null&&!name.equals("")){
            sql.append("and businessName like '%" ).append(name).append("%'");
        }
        if(address!=null&&!address.equals("")){
            sql.append("and businessAddress like '%" ).append(address).append("%'");
        }

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(String.valueOf(sql));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String password=rs.getString(2);
                String businessName = rs.getString(3);
                String businessAddress = rs.getString(4);
                String explain = rs.getString(5);
                BigDecimal startPrice = rs.getBigDecimal(6);
                BigDecimal deliveryPrice = rs.getBigDecimal(7);
                Business business=new Business();
                business.setBusinessId(id);
                business.setPassword(password);
                business.setBusinessName(businessName);
                business.setBusinessAddress(businessAddress);
                business.setBusinessExplain(explain);
                business.setStarPrice(startPrice);
                business.setDeliveryPrice(deliveryPrice);
                list.add(business);
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

        return list;
    }

    @Override
    public Business getByIdAndPassword(Integer id, String password) {
        String sql="select * from business where businessId=? and password=?";
        PreparedStatement preparedStatement=null;
        Business business=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
//                int id = rs.getInt(1);
//                String password=rs.getString(2);
                String businessName = rs.getString(3);
                String address = rs.getString(4);
                String explain = rs.getString(5);
                BigDecimal startPrice = rs.getBigDecimal(6);
                BigDecimal deliveryPrice = rs.getBigDecimal(7);
                business=new Business();
                business.setBusinessId(id);
                business.setPassword(password);
                business.setBusinessName(businessName);
                business.setBusinessAddress(address);
                business.setBusinessExplain(explain);
                business.setStarPrice(startPrice);
                business.setDeliveryPrice(deliveryPrice);
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
        return business;
    }

    @Override
    public int updatePassword(Integer businessId, String password) {
        String sql="update business set password=? where businessId=?";
        int row=0;
        PreparedStatement preparedStatement=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,password);
            preparedStatement.setInt(2,businessId);
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

}
