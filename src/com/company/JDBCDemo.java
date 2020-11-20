package com.company;

import java.sql.*;

//        1、导入驱动jar包, 建立libs文件夹， 右键 add as library
//        2、 注册驱动
//        3、获取数据库连接对象
//        4、定义sql
//        5、获取数据库连接对象statement
//        6、执行sql
//        7、处理结果
//        8、释放资源
public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jkdslg", "root", "123456");
        Statement statement = conn.createStatement();
        //更新
        String sql = "update account set balance=200 where id='1'";
//        int i = statement.executeUpdate(sql);
//        if(i==1){
//            System.out.println("更新成功");
//        }else{
//            System.out.println("更新失败");
//        }
        //查询
        String sql2 = "select * from account";
        ResultSet resultSet = statement.executeQuery(sql2);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int balance = resultSet.getInt("balance");
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            int balance = resultSet.getInt(3);
            System.out.println("id:" + id + "\tname:" + name + "\tbalance:" + balance);
        }
        //插入
//        String sql3="insert into account(name,balance) values('王五',35555)";
//        int i = statement.executeUpdate(sql3);
//        if(i==1){
//            System.out.println("更新成功");
//        }else{
//            System.out.println("更新失败");
//        }
        //删除
//        String sql4="delete from account where id='1'";
//        int i = statement.executeUpdate(sql4);
//        if(i==1){
//            System.out.println("更新成功");
//        }else{
//            System.out.println("更新失败");
//        }
        statement.close();
        conn.close();
    }
}
