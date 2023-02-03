package com.zbl.demo.jdbc;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SuppressWarnings("all")
public class MysqlTestIPV6 {
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String ip = "2001::192.168.239.61";
        Integer port = 3306;
        String dataBase = "zabbix";
        String jdbcUrl = "jdbc:mysql://address=(protocol=tcp)(host=" + ip + ")(port=" + port + ")/" + dataBase;
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "bbbbbb");
        Connection conn = DriverManager.getConnection(jdbcUrl, properties);
        PreparedStatement pstmt = conn.prepareStatement("select * from `config`;");
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("configid"));
        }

    }

    @Test
    public void test01() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        List<List<String>> partition = Lists.partition(list, 2);
        partition.parallelStream().forEach(l -> {
            System.out.println(l + " " + Thread.currentThread().getName());
        });
    }





}
