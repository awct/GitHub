package com.company;

import com.company.dao.impl.BusinessDaoImp;
import com.company.domain.Business;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BusinessDaoImp imp=new BusinessDaoImp();
        //查询所有
//        List<Business> businesses = imp.selectByAll();
//        businesses.forEach(business -> System.out.println(business));
        //查询单个
//        Business business = imp.selectById(10001);
//        System.out.println("selectById:"+business);
        //插入
//        Business insertBusiness=new Business("123456","汉堡王","中山路8号","汉堡套餐",new BigDecimal(200),new BigDecimal(2));
//        System.out.println("insert:"+imp.insert(insertBusiness));
//        //删除
        System.out.println("delete:"+imp.delete(10009));
//        //修改
//        Business updateBusiness=new Business(10011,"123456","汉堡王2","中山路2号","汉堡套餐",new BigDecimal(200),new BigDecimal(2));
//        System.out.println("update:"+imp.update(updateBusiness));
    }
}
