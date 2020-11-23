package com.company.dao.impl;

import com.company.dao.AdminDao;
import com.company.domain.Admin;
import com.company.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    Connection conn;
    @Override
    public Admin getAdminByNameAndPassword(String username, String password) {
        String sql="select * from admin where adminName=? and password=?";
        PreparedStatement preparedStatement=null;
        Admin admin=null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                admin=new Admin();
                admin.setAdminId(resultSet.getInt(1));
                admin.setAdminName(resultSet.getString(2));
                admin.setPassword(resultSet.getString(3));
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
        return admin;
    }
}
