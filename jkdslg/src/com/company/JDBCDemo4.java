package com.company;

import com.company.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDemo4 {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();
        if(login(username,password)){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
    public static boolean login(String username,String password) throws Exception {
        if(username==null||password==null){
            return false;
        }
        Connection connection = JDBCUtils.getConnection();
        String sql="select* from user where username='"+username+"' and password='"+password+"'";
        Statement st=connection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        return resultSet.next();
    }
}
