package com.company;

import com.company.domain.Admin;
import com.company.domain.Business;
import com.company.view.AdminView;
import com.company.view.BusinessView;
import com.company.view.impl.AdminViewImpl;
import com.company.view.impl.BusinessViewImpl;

import java.util.List;
import java.util.Scanner;

public class ElmAdminEntry {
    public static void main(String[] args) {
       run();
    }
    public static void run(){
        List<Business> businesses=null;
        AdminView adminView=new AdminViewImpl();
        Admin admin = adminView.login();
        Scanner sc=new Scanner(System.in);
        BusinessView businessView=new BusinessViewImpl();
        System.out.println("------------------------------------------------------");
        System.out.println("-----------------------饿了么后台管理------------------");
        System.out.println("------------------------------------------------------");

        int menu=0;
        if(admin!=null){
            System.out.println("管理员登录成功");
            while (menu!=5){
                System.out.println("1.查看所有商家\n2.搜索商家\n3.新建商家\n4.删除商家\n5.退出");
                System.out.println("请输入序号：");
                menu=sc.nextInt();
                switch (menu){
                    case 1:
                        businesses = businessView.selectByAll();
                        businesses.forEach(business -> System.out.println(business.toString()));
                        break;
                    case 2:
                        businesses = businessView.selectByNameAndAddress();
                        businesses.forEach(business -> System.out.println(business.toString()));
                        break;
                    case 3:
                        System.out.println("新建商家编号:"+businessView.insert());
                        break;
                    case 4:
                        int row = businessView.delete();
                        System.out.println(row>0?"删除成功":"删除失败");
                        break;
                    case 5:
                        System.out.println("退出成功");
                        break;
                    default:
                        System.out.println("不是有效数字");
                        break;
                }
            }
        }else{
            System.out.println("登录失败");
        }
    }
}
