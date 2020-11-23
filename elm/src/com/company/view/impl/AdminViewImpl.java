package com.company.view.impl;

import com.company.dao.impl.AdminDaoImpl;
import com.company.domain.Admin;
import com.company.view.AdminView;

import java.util.Scanner;

public class AdminViewImpl implements AdminView {
    private Scanner sc=new Scanner(System.in);
    @Override
    public Admin login() {
        System.out.println("请输入账号");
        String username = sc.next();
        System.out.println("请输入密码");
        String password=sc.next();
        AdminDaoImpl daoImp=new AdminDaoImpl();
        return daoImp.getAdminByNameAndPassword(username,password);
    }
}
