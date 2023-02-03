package com.zbl.demo.jdbc;

import org.apache.dubbo.common.utils.CollectionUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MysqlTest02 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.230.136:3333/11?useSSL=false", "root", "zbl5337");
        Statement pstmt = conn.createStatement();
//        PreparedStatement pstmt = conn.prepareStatement("select * from ;");

//        pstmt.execute();
        AtomicLong timesA = new AtomicLong(0L);
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    Thread.sleep(new Random().nextInt(10));
                    pstmt.execute("select " + i);
                    System.out.println(i);
                } catch (Exception throwables) {
                    throwables.printStackTrace();
//                    throwables.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "times: " +timesA.addAndGet(1L));
                }
            }
        }).start();

        AtomicLong timesB = new AtomicLong(0L);
        new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                try {
                    Thread.sleep(new Random().nextInt(10 * 1000));
                    pstmt.execute("select " + i);
                } catch (Exception throwables) {
//                    throwables.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + "times: " +timesB.addAndGet(1L));
                }
            }
        }).start();

    }

    @Test
    public void test01() throws Exception {
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list.size());
        List list1 = list.subList(0, 3);
        List list2 = list.subList(3, list.size());

        System.out.println(list1);
        System.out.println(list2);
    }
    
    @Test
    public void test0111() throws Exception {
//        boolean iPv6LiteralAddress = IPAddressUtil.isIPv6LiteralAddress("2001::192.168.51.222");
//        System.out.println(iPv6LiteralAddress);
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.HOUR, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.getTime();
        Date time = ca.getTime();
        System.out.println(time);
        System.out.println(time.getTime());
        System.out.println(ca.getTimeInMillis());
    }


    @Test
    public void test03333() throws Exception {
        //put
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1,1);
        map.put(2,2);

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(3,3);
        }).start();
        //put

        //get
        List<Integer> res = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            res.add(map.get(entry.getKey()));
            map.remove(entry.getKey());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(res);
//        map.clear();
        //get

    }

    @Test
    public void test01111111() throws Exception {
        List<User> warnDTOS = Arrays.asList(new User(100L), new User(200L), new User(50L), new User(500L));
        if (CollectionUtils.isNotEmpty(warnDTOS)) {
            System.out.println(warnDTOS);
            warnDTOS.sort((o1, o2) -> o2.getcTime().compareTo(o1.getcTime()));
            System.out.println(warnDTOS);
        }
    }

    class User {
        Long cTime;

        public User(Long cTime) {
            this.cTime = cTime;
        }

        public Long getcTime() {
            return cTime;
        }

        public void setcTime(Long cTime) {
            this.cTime = cTime;
        }

        @Override
        public String toString() {
            return "User{" +
                    "cTime=" + cTime +
                    '}';
        }
    }

    @Test
    public void test0221() throws Exception {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        System.out.println(ca.getTime());
        System.out.println(ca.getTime().getTime());
        System.out.println(ca.getTimeInMillis());

        List l = new ArrayList();
        System.out.println(l.size());
        l.add(1L);
        System.out.println(l.size());
        l.add(2L);
        System.out.println(l.size());
        System.out.println(l.get(1));
        System.out.println(l.get(l.size() -1));
        Iterator iterator = l.iterator();
        System.out.println(l);
        while (iterator.hasNext()) {
            if (iterator.next().equals(2L)) {
                iterator.remove();;
            }
        }
        System.out.println(l);
    }

    @Test
    public void test0133() throws Exception {
        String str = "张a";
        System.out.println(str.toUpperCase());

    }

    @Test
    public void test02221() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        for (Integer integer : integers) {
            if (integer == 2) {
                continue;
            }
            System.out.println(integer);
        }

    }




}
