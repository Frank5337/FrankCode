package com.zbl.demo.jdbc;

import java.sql.*;

/**
 * @Author: Frank
 * @Date: Created in 2023/5/28
 * @Description:
 * @Version: $
 */
public class MysqlDiagramForL0X1000 {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://8.136.133.137:3306/l0x1000db", "root", "zbl5337");
//        PreparedStatement pstmt = conn.prepareStatement("SELECT table_name ,table_comment FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'l0x1000db'");
        PreparedStatement pstmt = conn.prepareStatement("SELECT table_name ,table_comment FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'l0x1000db' \n" +
                "AND table_name IN ('t_activity','t_activity_detail','t_activity_user');");
        Statement statement = conn.createStatement();
        ResultSet resultTableList = pstmt.executeQuery();
        while (resultTableList.next()) {
            String tableName = resultTableList.getString(1);
            String tableComment = resultTableList.getString(2);
            ResultSet resultDescTable = statement.executeQuery(String.format("SHOW FULL COLUMNS FROM `%s`", tableName));
            System.out.printf("Table %s [note: '%s'] {%n", tableName, tableComment);
            while (resultDescTable.next()) {
                String column = String.format("  %s %s", resultDescTable.getString("Field"), resultDescTable.getString("Type"));
                if ("id".equals(resultDescTable.getString("Field"))) {
                    column += " [primary key]";
                } else {
                    column += String.format(" [note: '%s']", resultDescTable.getString("Comment").replaceAll("'", " "));
                }
                System.out.println(column);
            }
            System.out.println("}");
            System.out.println();
        }

        /**
         Ref: sys_dict_data.type_id - sys_dict_type.id

         */
    }
}
