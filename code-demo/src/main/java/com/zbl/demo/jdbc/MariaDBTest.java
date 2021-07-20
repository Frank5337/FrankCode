package com.zbl.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/22 15:01
 * @Description:
 * @Version: $
 */

public class MariaDBTest {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.239.141:3306/?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "mariadb");
        PreparedStatement pstmt = conn.prepareStatement("show variables like '%version%'");
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("Value"));
        }
    }
}
