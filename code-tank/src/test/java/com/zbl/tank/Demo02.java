package com.zbl.tank;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 16:06 2020/7/15
 * @Description:
 * @Version: $
 */
public class Demo02 {

    public static void main(String[] args) {
        System.out.println(test01(1).hashCode());
        System.out.println(test02(1).hashCode());
        System.out.println(1);
        String l = test01(1);
        String k = test02(1);
        System.out.println(l == k);
        System.out.println(test01(1) == test01(1));
    }


    public static String test01(int id) {
        String sql ="select * from table1 where id= "+id;
        String sql1 ="select * from table1 where id= "+id;
        System.out.println("----------" + sql == sql1);
        return sql;
    }



    public static String test02(int id) {
        StringBuilder sql= new StringBuilder();
        sql.append("select * from table1 where id= ");
        sql.append(id);
        return sql.toString();
    }

    @Test
    public void test11() throws Exception{
        String a = "a";
        String b = "a";
        System.out.println(a == b);
    }
}
