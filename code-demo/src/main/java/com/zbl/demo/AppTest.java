package com.zbl.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Set;

/**
 * @Author: zbl
 * @Date: Created in 2021/11/12 18:35
 * @Description:
 * @Version: $
 */
public class AppTest {
    private static final String ADDR = "127.0.0.1";

    private static final int PORT = 6379;

    private static JedisPool jedisPool = new JedisPool(ADDR, PORT);

    public static Jedis getJedis() {

        return jedisPool.getResource();

    }

    //生产者,生成5个订单放进去

    public void productionDelayMessage() {

        for (int i = 0; i < 5; i++) {

            //延迟3秒

            Calendar cal1 = Calendar.getInstance();

            //3秒后
            cal1.add(Calendar.SECOND, 3);

            int second3later = (int) (cal1.getTimeInMillis() / 1000);

            AppTest.getJedis().zadd("OrderId", second3later, "OID0000001" + i);

            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID0000001" + i);

        }

    }

    //消费者，取订单

    public void consumerDelayMessage() {

        Jedis jedis = AppTest.getJedis();

        int i = 1;
        while (true) {

            Set<Tuple> items = jedis.zrangeWithScores("OrderId", 0, 1);

            if (items == null || items.isEmpty()) {

                System.out.println("当前没有等待的任务");

                try {

                    Thread.sleep(500 * i);
                    i++;
                } catch (InterruptedException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                }

                if (i >= 10) {
                    i = 10;
                }
                continue;

            }

            int score = (int) ((Tuple) items.toArray()[0]).getScore();

            Calendar cal = Calendar.getInstance();

            int nowSecond = (int) (cal.getTimeInMillis() / 1000);

            if (nowSecond >= score) {

                String orderId = ((Tuple) items.toArray()[0]).getElement();

                Long zrem = jedis.zrem("OrderId", orderId);

                if(zrem != null && zrem > 0) {
                    System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
                }

            }

            i = 1;

        }

    }

    public static void main(String[] args) {

        AppTest appTest = new AppTest();

        //生产者,生成5个订单放进去
        appTest.productionDelayMessage();
        //消费者，取订单
        appTest.consumerDelayMessage();

    }

}
