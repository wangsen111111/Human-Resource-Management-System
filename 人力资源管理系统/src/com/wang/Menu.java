package com.wang;

/**
 * Introduce:   这里面全是静态的方法，把构造方法私有化，只能通过类名调用，不能创造对象
 */
public class Menu {
    private Menu(){
    }
    //界面
    public static void startMenu(){
        System.out.println("############################################");
        System.out.println("############欢迎来到人力资源管理系统##########");
        System.out.println("############      请选择功能      ###########");
        System.out.println("############      1.添加员工      ###########");
        System.out.println("############      2.删除员工      ###########");
        System.out.println("############      3.查询员工      ###########");
        System.out.println("############      4.修改员工信息  ###########");
        System.out.println("############      5.显示所有员工信息##########");
        System.out.println("############      0.退出系统      ###########");
        System.out.println("############################################");
    }
}
