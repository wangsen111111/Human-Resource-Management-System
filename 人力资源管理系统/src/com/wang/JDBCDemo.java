package com.wang;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1,加载驱动
        Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
        //2,用户信息和url    useUnicode=true&characterEncoding=utf8&&useSSL=true
        String url ="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&&useSSL=false";
        String username = "root";
        String password = "123456";
        //3,连接成功，数据库对象,connection代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //4,执行sql的对象，statement执行sql的对象
        Statement statement = connection.createStatement();
        //5,执行sql的对象 去 执行sql，可能存在结果，查看返回结果
        String sql="select * from users";
        ResultSet resultSet = statement.executeQuery(sql);//返回的结果集,结果集中封装了我们全部的查询出来的结果
        while(resultSet.next()){
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("NAME="+resultSet.getObject("NAME"));
            System.out.println("PASSWORD="+resultSet.getObject("PASSWORD"));
            System.out.println("email="+resultSet.getObject("email"));
            System.out.println("birthday="+resultSet.getObject("birthday"));
        }
        //6,释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

