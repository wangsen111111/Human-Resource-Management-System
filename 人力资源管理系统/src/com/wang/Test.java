package com.wang;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Introduce:测试类
 */
public class Test {
    public static boolean login(){

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入人力资源管理系统账号=>");
        String username = sc.nextLine();
        System.out.println("请输入人力资源管理账号密码=>");
        String password = sc.nextLine();

        //现在从JDBC中用户登录表（login）中获取数据验证
        Connection coon = Connect.getCoon();    //连接数据库
        String sql="select * from user;";  //sql语句，意思查询用户表中的信息
        try{

            //4,执行sql的对象，statement执行sql的对象
            //Statement statement = coon.createStatement();
            Statement sta = (Statement)coon.createStatement();

            //5,执行sql的对象 去 执行sql，可能存在结果，查看返回结果
            //String sql="select * from users";
            //ResultSet resultSet = statement.executeQuery(sql);//返回的结果集,结果集中封装了我们全部的查询出来的结果

            ResultSet rs = (ResultSet)sta.executeQuery(sql);
            while(rs.next()) {
                String name = rs.getString("username");
                String word = rs.getString("password");
                if(name.equals(username)&&word.equals(password)) {
                    return true;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
//insert department values(asdasd,as)
    public static void main(String[] args) {
        //Scanner输入流，如果先读取一个int类型，然后再读取一个String类型，就会出错
        //解决方法：1.nextInt 但是String类型的读取，就能用next不能用nextLine
        //2.造两个输入流，一个输入int，一个录入String
        Scanner scInt = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);
        //Connect.getCoon();
        //测试账号登录
        if(login()){
            System.out.println("登陆成功");
        }else{
            System.out.println("账号或者密码错误");
            System.exit(1);
        }

        boolean flag = true;    //第一层循环控制
        while(flag){
            Menu.startMenu();   //增删改查员工
            System.out.println("请选择=>");
            int chose = scInt.nextInt();
            switch (chose){
                case 1: //添加员工
                    System.out.println("请输入新员工的编号");
                    int employeeNo=scInt.nextInt();
                    System.out.println("请输入新员工的职位");
                    String title = scStr.nextLine();
                    System.out.println("请输入新员工的名字");
                    String employeeName = scStr.nextLine();
                    System.out.println("请输入新员工的家庭地址");
                    String employeeAddress = scStr.nextLine();
                    System.out.println("请输入新员工的性别");
                    String employeeSex = scStr.nextLine();
                    System.out.println("请输入新员工的资薪");
                    int employeeSalary = scInt.nextInt();
                    System.out.println("请输入新员工的部门编号");
                    int departmentNo = scInt.nextInt();
                    JDBC.add(employeeNo,title,employeeName,employeeAddress,employeeSex,employeeSalary,departmentNo);
                    break;
                case 2: //删除员工
                    System.out.println("请输入需要删除的员工的编号");
                    JDBC.delete(scInt.nextInt());
                    break;
                case 3: //查询员工
                    System.out.println("请输入需要查询员工的id");
                    if(!JDBC.search(scInt.nextInt())){
                        System.out.println("没有找到该员工");
                    }
                    break;
                case 4: //修改员工信息
                    System.out.println("请输入需要修改的属性名");
                    String property = scStr.nextLine();
                    System.out.println("请输入需要修改的值");
                    String updateProperty = scStr.nextLine();
                    System.out.println("需要修改的属性名的employeeNo的是多少");
                    int employeeNo1 = scInt.nextInt();
                    JDBC.update(property,updateProperty,employeeNo1);
                    break;
                case 5:
                    JDBC.list();
                    break;
                case 0: //退出系统
                    flag=false;
                    break;
            }
        }
    }

}
