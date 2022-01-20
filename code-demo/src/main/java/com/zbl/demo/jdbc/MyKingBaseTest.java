package com.zbl.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/27 16:07
 * @Description:
 * @Version: $
 */
public class MyKingBaseTest {

    public static void main(String[] args) throws Exception {
        Class.forName("com.kingbase.Driver");
        Class.forName("com.kingbase8.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", "kingbase");
        properties.setProperty("password", "kingbase");
        Connection connection = DriverManager.getConnection("jdbc:kingbase8://192.168.202.60:54321/TEST", properties);
        Statement statement = connection.createStatement();
        List<String> list = new ArrayList<>();

        String sql = "select table_schema as OWNER from INFORMATION_SCHEMA.TABLES  where upper(table_schema) NOT in('INFORMATION_SCHEMA','SYS_CATALOG') group by table_schema";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(rs.getString("OWNER"));
        }

        System.out.println(list);

    }
}
