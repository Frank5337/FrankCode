package com.zbl.localDateDemo;

import org.joda.time.*;
import org.junit.Test;

import java.time.ZoneId;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 14:17 2020/6/5
 * @Description:
 * @Version: $
 */
public class Main {

    /**
     * @Author: zbl
     * @Date: Created in 15:10 2020/6/5
     * @Description:
     * @Version: $
     */
    public static void main(String[] args) {
        // Joda-time 各种操作.....
        LocalDateTime localDateTime=LocalDateTime.now();
        localDateTime = localDateTime.plusDays(1) // 增加天
                .plusYears(1)// 增加年
                .plusMonths(1)// 增加月
                .plusWeeks(1)// 增加星期
                .minusMillis(1)// 减分钟
                .minusHours(1)// 减小时
                .minusSeconds(1);// 减秒数

        System.out.println(localDateTime);
        // 计算完转换成jdk 对象
        Date date2 = localDateTime.toDate();
        System.out.println(date2);
        //Calendar calendar2 = localDateTime.toCalendar(Locale.CHINA);

        //获取本月指定日期 本月20号
        LocalDate localDate  = LocalDate.now().withDayOfMonth(20);
        System.out.println(localDate);

        //获取本月最后一天
        LocalDate lastLocalDate  = LocalDate.now().dayOfMonth().withMaximumValue();
        System.out.println(lastLocalDate);

        //获取本月第一天
        LocalDate minLocalDate  = LocalDate.now().dayOfMonth().withMinimumValue();
        System.out.println(minLocalDate);

        // joda-time 计算两个时间的相差时间天数
        LocalDate start=new LocalDate(2018, 1,18);
        LocalDate end=new LocalDate(2018, 1, 20);
        int days = Days.daysBetween(start, end).getDays();
        System.out.println(days);

        // joda-time 计算两个时间的相差时间月数
        LocalDate startMonth=new LocalDate(2018, 1,1);
        LocalDate endMonth=new LocalDate(2020, 1, 1);
        int months = Months.monthsBetween(startMonth,endMonth).getMonths();
        System.out.println(months);

        //Joda-time
        DateTime dateTime=new DateTime(2018, 1, 8, 18, 23,55);

        //把一个JDK date转换成joda date
        LocalDate.fromDateFields(new Date());
        //LocalDate.fromCalendarFields()
        LocalDate.parse("2018-11-11");

        // 获取10位时间戳
        Long timestamp = Instant.now().getMillis() / 1000 ;
        System.out.println(timestamp);
        //# TmallGenieServiceApiTest - 10 timestamp: 1578640376



    }

    /**
     * userBo.setLastSignInTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        //ZonedDateTime zdt = localDateTime.(zoneId);

        //Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDateTime = " + localDateTime);
        //System.out.println("Date = " + date);

    }

    @Test
    public void test02() throws Exception{
        Date date = LocalDateTime.now().toDate();
        System.out.println(date);
    }

}
