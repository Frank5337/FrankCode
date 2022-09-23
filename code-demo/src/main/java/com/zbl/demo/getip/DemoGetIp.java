package com.zbl.demo.getip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zbl
 * @Date: Created in 10:24 2020/2/15
 * @Description:
 * @Version: $
 */
public class DemoGetIp {
    public static void main(String[] args) {

        String sohu = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(sohu);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Pattern p = Pattern.compile("\\d+.\\d+.\\d+.\\d+");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipStr = m.group(0);
            System.out.println("本地外网ip: " + ipStr);
        }

    }

}
