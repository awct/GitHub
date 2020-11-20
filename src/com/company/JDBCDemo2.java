package com.company;

import com.company.domain.Emp;
import com.company.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo2 {
    public static void main(String[] args) throws Exception {

        Connection conn = JDBCUtils.getConnection();
        Statement statement = conn.createStatement();
        String sql="select * from emp";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int eno = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String job = resultSet.getString(3);
            int mgr = resultSet.getInt(4);
            Date hiredate = resultSet.getDate(5);
            int sal = resultSet.getInt(6);
            int comm = resultSet.getInt(7);
            int depno = resultSet.getInt(8);
            Emp emp=new Emp(eno,name,job,mgr,hiredate,sal,comm,depno);
            System.out.println(emp.toString());
        }
    }
}
