package com.company.view.impl;

import com.company.dao.BusinessDao;
import com.company.dao.impl.BusinessDaoImpl;
import com.company.domain.Business;
import com.company.view.BusinessView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    BusinessDao businessDao=new BusinessDaoImpl();
    Scanner sc =new Scanner(System.in);

    @Override
    public List<Business> selectByAll() {
        return businessDao.selectByAll();
    }

    @Override
    public int insert() {
        Business business=new Business();
        System.out.println("请输入商家密码");
        business.setPassword(sc.next());
        System.out.println("请输入商家姓名");
        business.setBusinessName(sc.next());
        System.out.println("请输入商家地址");
        business.setBusinessAddress(sc.next());
        System.out.println("请输入商家介绍");
        business.setBusinessExplain(sc.next());
        System.out.println("请输入商家起送费");
        business.setStarPrice(sc.nextBigDecimal());
        System.out.println("请输入商家配送费");
        business.setDeliveryPrice(sc.nextBigDecimal());
        return businessDao.insert(business);
    }

    @Override
    public int delete() {
        System.out.println("请输入要删除的商家编号:");
        return businessDao.delete(sc.nextInt());
    }

    @Override
    public int updateById(Integer businessId){
        Business business = businessDao.selectById(businessId);
        if(business==null){
            return 0;
        }
        System.out.println("请输入需要修改的信息属性（分隔符请用','逗号进行）例如：1,2\n1.商家名称\t2.商家地址\t3.商家介绍\t4.商家起送费\t5.商家配送费");
        String[] split = sc.next().split(",");


        for (String option : split) {
            switch (option){
                case "1":
                    System.out.println("请输入商家名称");
                    business.setBusinessName(sc.next());
                    break;
                case "2":
                    System.out.println("请输入商家地址");
                    business.setBusinessAddress(sc.next());
                    break;
                case "3":
                    System.out.println("请输入商家介绍");
                    business.setBusinessExplain(sc.next());
                    break;
                case "4":
                    System.out.println("请输入商家起送费");
                    business.setStarPrice(sc.nextBigDecimal());
                    break;
                case "5":
                    System.out.println("请输入商家配送费");
                    business.setDeliveryPrice(sc.nextBigDecimal());
                    break;
            }
        }
        return businessDao.update(business);
    }

    @Override
    public int updatePassword(Integer businessId) {
        Business business = selectById(businessId);
        System.out.println("请输入旧密码");
        String oldPassword = sc.next();
        if(business.getPassword().equals(oldPassword)){
            System.out.println("请输入新密码");
            String password = sc.next();
            System.out.println("请再次输入新密码");
            String password2 = sc.next();
            if(password.equals(password2)){
                return businessDao.updatePassword(businessId,password);
            }else{
                System.out.println("新密码输入不相同");
            }
        }else{
            System.out.println("旧密码输入错误");
        }
        return 0;
    }

    @Override
    public Business selectById(Integer id) {
        return businessDao.selectById(id);
    }

    @Override
    public List<Business> selectByNameAndAddress() {
        System.out.println("是否输入商家名称(y/n)");
        String name="";
        if(sc.next().equals("y")){
            System.out.println("请输入商家名称关键词");
            name=sc.next();
        }
        System.out.println("是否输入商家地址(y/n)");
        String address="";
        if(sc.next().equals("y")){
            System.out.println("请输入商家地址关键词");
            address=sc.next();
        }
        return businessDao.selectByNameAndAddress(name,address);
    }

    @Override
    public Business login() {
        System.out.println("请输入商家编号");
        int businessId = sc.nextInt();
        System.out.println("请输入密码");
        String password=sc.next();
        return businessDao.getByIdAndPassword(businessId,password);
    }
}
