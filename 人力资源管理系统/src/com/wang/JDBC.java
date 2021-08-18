package com.wang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Introduce:对数据库进行操作
 */
public class JDBC {
    private JDBC(){}

    /*
    create table employee(
    employeeNo int primary key,    //员工编号    员工编号从0开始，不能是负数，不能重复和不能非空
    title varchar(20),   //职称
    employeeName varchar(20),    //姓名
    employeeAddress varchar(100),    //地址
    employeeSex char(2),   //性别
    employeeSalary int,    //薪水
    departmentNo int not null    //部门编号
);
     */
    //插入员工信息表中的员工
    public static int add(int employeeNo,String title,String employeeName,String employeeAddress,String employeeSex,int employeeSalary,int departmentNo){
        if(search(employeeNo,false)){ //查询员工的编号是否在mysql已经存在，因为是主键，不能重复，不然会报错
            System.out.println(employeeNo+"员工编号已存在");
            return -1;
        }
        int i=0;
        String sql = "insert into employee(employeeNo,title,employeeName,employeeAddress,employeeSex,employeeSalary,departmentNo) values(?,?,?,?,?,?,?)";
        Connection coon = Connect.getCoon();

        try{
            PreparedStatement pst = coon.prepareStatement(sql);
            pst.setInt(1,employeeNo);
            pst.setString(2,title);
            pst.setString(3,employeeName);
            pst.setString(4,employeeAddress);
            pst.setString(5,employeeSex);
            pst.setInt(6,employeeSalary);
            pst.setInt(7,departmentNo);

            pst.executeUpdate();    //执行sql语句
            pst.close();
            coon.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("添加员工成功");
        return i;
    }


    /**
     * 删除员工信息表中的员工
     * @param employeeNo    根据员工编号
     */
    public static void delete(int employeeNo){  //根据员工的编号删除员工
        if(!search(employeeNo,false)){
            System.out.println("没有找到编号为："+employeeNo+"的员工");
            return;
        }
        String sql="delete from employee where employeeNo = "+employeeNo;
        Connection coon = Connect.getCoon();
        try {
            PreparedStatement pst = coon.prepareStatement(sql);
            pst.execute();  //执行sql语句
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除编号为"+employeeNo+"的员工成功");
    }

    /**
     * 根据员工编号查找信息，然后打印,功能查询
     * @param employeeNo    玩家传入的需要查找的员工编号
     * @return  找到返回true，没有找到返回false
     */
    public static boolean search(int employeeNo){
        Connection coon = Connect.getCoon();
        String sql="select * from employee";
        try {
            PreparedStatement pst = coon.prepareStatement(sql); //让数据库执行sql语句，然后形成一个对象
            ResultSet rs = pst.executeQuery();  //将数据放在数据集中
            while (rs.next()){
                if(employeeNo==rs.getInt("employeeNo")){
                    System.out.println("员工编号："+rs.getInt("employeeNo"));
                    System.out.println("职称："+rs.getString("title"));
                    System.out.println("姓名："+rs.getString("employeeName"));
                    System.out.println("家庭地址："+rs.getString("employeeAddress"));
                    System.out.println("性别："+rs.getString("employeeSex"));
                    System.out.println("工资："+rs.getInt("employeeSalary"));
                    System.out.println("部门编号："+rs.getString("departmentNo"));
                    return true;
                }
            }
            //释放资源
            rs.close();
            pst.close();
            Connect.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否存在
     *     //查询员工信息,根据编号查找，如果没有找到，直接打出一句话
     * @param employeeNo    待查询员工贷信息
     * @param flag  查询到员工，是否需要打印
     * @return 找到返回true，没有找到返回false
     */
    public static boolean search(int employeeNo,Boolean flag){
        boolean flag1 = false;  //表示是否找到
        //String sql="select"
        Connection coon = Connect.getCoon();    //连接数据库
        String sql = "select * from employee";
        try {
            PreparedStatement pst = coon.prepareStatement(sql); //读取这个sql语句
            ResultSet rs = pst.executeQuery();  //将数据放在数据集中
            //处理结果
            while(rs.next()){
                if(employeeNo==rs.getInt("employeeNo")){
                    flag1=true;
                }
                if(flag){
                    System.out.println("员工编号："+rs.getInt("employeeNo"));
                    System.out.println("职称："+rs.getString("title"));
                    System.out.println("姓名："+rs.getString("employeeName"));
                    System.out.println("家庭地址："+rs.getString("employeeAddress"));
                    System.out.println("性别："+rs.getString("employeeSex"));
                    System.out.println("工资："+rs.getInt("employeeSalary"));
                    System.out.println("部门编号："+rs.getString("departmentNo"));
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag1;
    }

    /**
     * 根据员工id修改属性
     * @param property  需要修改的属性名
     * @param updateProperty    修改的值
     * @param employeeNo    需要修改的属性名的employeeNo的哪一个
     */
    public static void update(String property,String updateProperty,int employeeNo){
        if(!search(employeeNo,false)){
            System.out.println("没有找到编号为"+employeeNo+"的员工");
            return;
        }

        Connection coon = Connect.getCoon();
        String sql = "update employee set "+property+"=\'"+updateProperty+"\' where employeeNo="+employeeNo;
        try {
            PreparedStatement pst = coon.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("修改成功");
    }

    public static void list(){
        Connection coon = Connect.getCoon();
        String sql = "select * from employee;";

        try {
            PreparedStatement pst = coon.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                System.out.println("员工编号："+rs.getInt("employeeNo"));
                System.out.println("职称："+rs.getString("title"));
                System.out.println("姓名："+rs.getString("employeeName"));
                System.out.println("家庭地址："+rs.getString("employeeAddress"));
                System.out.println("性别："+rs.getString("employeeSex"));
                System.out.println("工资："+rs.getInt("employeeSalary"));
                System.out.println("部门编号："+rs.getString("departmentNo"));
                System.out.println("-----------------------------------------------------");
            }
            rs.close();
            pst.close();
            coon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
