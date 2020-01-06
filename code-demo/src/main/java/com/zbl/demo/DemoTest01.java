package com.zbl.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: zbl
 * @Date: Created in 9:58 2020/1/6
 * @Description:
 * @Version: $
 */
public class DemoTest01 {

    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(2019, 11, 29); //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52); 
        Date date=calendar.getTime();//date就是你需要的时间

        // 选择 20191229 这一天
        String date20191229 =  new SimpleDateFormat("yyyyMMdd").format(date);
        System.out.println(date20191229);
        String YYYYMMdd =  new SimpleDateFormat("YYYYMMdd", Locale.CHINA).format(date);//2020
        System.out.println("china " + YYYYMMdd);
                // ISO 8601
               YYYYMMdd =  new SimpleDateFormat("YYYYMMdd", Locale.FRANCE).format(date);//2019
        System.out.println("france" + YYYYMMdd);
               YYYYMMdd =  new SimpleDateFormat("YYYYMMdd").format(date);//2020
        // 将会输出 2020，使用 Common 。当前系统，Locale 默认值为 Locale.CHINA
        //System.out.println(DateFormatUtils.format(date20191229, "YYYY"));
        // 将会输出 2019，使用 ISO 8601
        //System.out.println(DateFormatUtils.format(date20191229, "YYYY", Locale.FRANCE));
    }

}
