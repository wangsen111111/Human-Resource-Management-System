package com.wang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Introduce:   连接数据库
 */
public class Connect {



    private Connect(){
    }
    public static Connection getCoon(){
        Connection coon=null;
        try {
            //1,加载驱动
            Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
            //2,用户信息和url    useUnicode=true&characterEncoding=utf8&&useSSL=true
            String url ="jdbc:mysql://localhost:3306/hrdatabase?useUnicode=true&characterEncoding=utf8&&useSSL=false";
            String user = "root";
            String password = "123456";

            //3,连接成功，数据库对象,connection代表数据库
            coon = DriverManager.getConnection(url, user, password);

            //System.out.println("连接成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return coon;
    }

    public static void close(){
        try {
            getCoon().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
