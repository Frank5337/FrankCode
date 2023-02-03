package com.zbl.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/26 13:59
 * @Description:
 * @Version: $
 */
public class PrestoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 常规jdbc
        Class.forName("com.facebook.presto.jdbc.PrestoDriver");
        Properties properties = new Properties();
        properties.setProperty("SSL", "true");
//        properties.setProperty("SSLKeyStorePath", "E:\\software\\qiyewxFiles\\WXWork\\1688851943938006\\Cache\\File\\2021-04\\www.com.cn.jks");
//        properties.setProperty("SSLKeyStorePassword", "123456");
        properties.setProperty("SSLTrustStorePath", "C:\\Users\\ThinkPad\\Desktop\\126p.truststore");
        properties.setProperty("SSLTrustStorePassword", "123456");
        properties.setProperty("user", "ldapdev");
        properties.setProperty("password", "plaintext");

//        properties.setProperty("password", assetSql.getDbPassword());
//        Connection con = DriverManager.getConnection("jdbc:presto://www.11.com.cn:8443/hive", properties);
        Connection con = DriverManager.getConnection("jdbc:presto://192.168.51.126:8334/hive", properties);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("show schemas from hive");
        while (rs.next()) {
            System.out.println("hive" + "." + rs.getString("Schema"));
        }

    }
}
