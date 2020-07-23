package com.zbl.mysql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class MysqlTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "123456");
        PreparedStatement pstmt = conn.prepareStatement("insert into psn2 values(?,?)");
        for (int i = 0; i < 20000; i++) {
            pstmt.setInt(1, i);
            pstmt.setString(2, i + "");
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        conn.close();
    }

    @Test
    public void test01() throws Exception{
        List test = new ArrayList<>();
        test.add("test");
        test.add(null);
        test.addAll(null);
        System.out.println(test);
        System.out.println(test.size());
    }
}
