package com.company;

import com.company.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo3 {
    public static void main(String[] args) throws Exception {
        List<Emp> list = findAll();
        list.forEach(emp->System.out.println(emp.toString()));
    }
    public static List<Emp> findAll() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jkdslg", "root", "123456");
        Statement statement = conn.createStatement();
        String sql="select * from emp";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Emp> list=new ArrayList<>();
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
            list.add(emp);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return list;
    }
}
