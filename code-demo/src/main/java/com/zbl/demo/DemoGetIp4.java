package com.zbl.demo;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zbl
 * @Date: Created in 11:10 2020/2/15
 * @Description:
 * @Version: $
 */
public class DemoGetIp4 {
    public static void main(String[] args) {
        try {
            System.out.println(getV4IP());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("err");
        }
    }
    /**
     * 获取本机的公网ip地址
     * @return
     */
    public static String getV4IP(){
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
        //System.out.println(inputLine.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            urlConnection.disconnect();
        }
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipStr = m.group(1);
            ip = ipStr;
            System.out.println("公网ip: " + ipStr);
        }
        return ip;
    }

    @Test
    public void test01() throws Exception{
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

}
