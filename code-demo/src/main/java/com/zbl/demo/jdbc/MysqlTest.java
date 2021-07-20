package com.zbl.demo.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MysqlTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://8.136.133.137:3306/capaa", "root", "zbl5337");
        PreparedStatement pstmt = conn.prepareStatement("select distinct `PRIV_NAME`,`PRIV_DESC` from mc$priv_database where DB_TYPE = 13");
        ResultSet resultSet = pstmt.executeQuery();

        System.out.println("INSERT INTO `mc$priv_database` (`PRIV_NAME`, `PRIV_DESC`, `DB_TYPE`) VALUES");
        while (resultSet.next()) {
            String privName = resultSet.getString("PRIV_NAME");
            String privDesc = resultSet.getString("PRIV_DESC");
            System.out.println("('" + privName + "','" + privDesc + "'," + 30 + "),");
        }

        conn.close();
    }

    @Test
    public void test01() throws Exception {
        List test = new ArrayList<>();
        test.add("test");
        test.add(null);
        test.addAll(null);
        System.out.println(test);
        System.out.println(test.size());
        List<String> sk = new ArrayList<>();
        sk.add("创建用户");
        sk.add("创建角色");
        sk.add("更改密码");
        sk.add("");
        sk.add("删除用户");
        sk.add("删除角色");
        sk.add("访问敏感对象授权");
        sk.add("访问业务用户对象授权");
        sk.add("访问系统对象授权");
        sk.add("授权所有操作权限");
        sk.add("更新任意数据授权");
        sk.add("查询任意数据授权");
        sk.add("插入任意数据授权");
        sk.add("删除任意数据授权");

        sk.add("创建任意对象授权");

        sk.add("创建表");
        sk.add("修改敏感表格");
        sk.add("清空敏感表数据");
        sk.add("删除敏感表格");
        sk.add("修改业务用户表格");
        sk.add("清空业务用户表数据");
        sk.add("删除业务用户表格");
        sk.add("修改系统表格");
        sk.add("清空系统表数据");
        sk.add("删除系统表格");
        sk.add("删除表空间");
        sk.add("修改表空间");
        sk.add("删除数据库");
        sk.add("修改数据库");
        //过程函数包
        //触发器
        //视图

        sk.add("修改系统表格");
        sk.add("修改系统表格");
        sk.add("修改系统表格");
        sk.add("修改系统表格");
        sk.add("修改系统表格");
        sk.add("修改系统表格");
    }
}
