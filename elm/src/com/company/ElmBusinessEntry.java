package com.company;

import com.company.domain.Admin;
import com.company.domain.Business;
import com.company.domain.Food;
import com.company.view.AdminView;
import com.company.view.BusinessView;
import com.company.view.FoodView;
import com.company.view.impl.AdminViewImpl;
import com.company.view.impl.BusinessViewImpl;
import com.company.view.impl.FoodViewImpl;

import java.util.List;
import java.util.Scanner;

public class ElmBusinessEntry {
    public static void main(String[] args) {
       run();
    }
    public static void run(){
        Scanner sc=new Scanner(System.in);
        BusinessView businessView=new BusinessViewImpl();
        System.out.println("------------------------------------------------------");
        System.out.println("-----------------------饿了么商家管理------------------");
        System.out.println("------------------------------------------------------");
        Business business = businessView.login();
        int menu=0;
        if(business!=null){
            System.out.println("商家登录成功");
            while (menu!=5){
                System.out.println("1.查看商家信息\n2.修改商家信息\n3.修改密码\n4.所属商品管理\n5.退出");
                System.out.println("请输入序号：");
                menu=sc.nextInt();
                switch (menu){
                    case 1:
                        System.out.println(business.toString());
                        break;
                    case 2:
                        int row = businessView.updateById(business.getBusinessId());
                        System.out.println(row>0?"修改成功":"修改失败");
                        break;
                    case 3:
                        row = businessView.updatePassword(business.getBusinessId());
                        System.out.println(row>0?"修改成功":"修改失败");
                        break;
                    case 4:
                        foodManager(business.getBusinessId());
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


    //修改、删除都必须是对本商户有关的食品进行操作，对于其他的不能
    public static void foodManager(int businessId){
        Scanner sc=new Scanner(System.in);
        FoodView foodView=new FoodViewImpl();
        int menu=0;
        while (menu!=5){
            System.out.println(">>>>二级菜单\n1.查看食品信息\n2.修改食品信息\n3.增加食品信息\n4.删除食品信息\n5.返回上一级菜单");
            System.out.println("请输入序号：");
            menu=sc.nextInt();
            switch (menu){
                case 1:
                    List<Food> foods = foodView.selectByBusinessId(businessId);
                    foods.forEach(food -> System.out.println(food.toString()));
                    if(foods.size()==0){
                        System.out.println("您的商户里没有食品");
                    }
                    break;
                case 2:
                    int row = foodView.update(businessId);
                    System.out.println(row>0?"修改成功":"修改失败");
                    break;
                case 3:
                    row = foodView.insert(businessId);
                    System.out.println(row>0?"新增成功":"新增失败");
                    break;
                case 4:
                    row = foodView.delete(businessId);
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
    }
}
