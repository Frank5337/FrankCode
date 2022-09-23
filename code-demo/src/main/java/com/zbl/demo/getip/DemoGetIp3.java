package com.zbl.demo.getip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zbl
 * @Date: Created in 10:24 2020/2/15
 * @Description:
 * @Version: $
 */
public class DemoGetIp3 {

    // 获取网页源码
    static String httpGet(String url) {

        StringBuffer buffer = new StringBuffer();

        try {

            URLConnection conn = new URL(url).openConnection();

            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");

            try (InputStream inputStream = conn.getInputStream();
                 InputStreamReader streamReader = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(streamReader);) {

                String line = null;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append(System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    public static void main(String[] args) throws Exception{

        String html = httpGet("https://www.baidu.com/s?wd=ip");

        // 提出IP

        Pattern pattern = Pattern.compile("<span\\sclass=\"c-gap-right\">本机IP:&nbsp;([^<]+)</span>");

        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {

            String ip = matcher.group(1);

            System.out.println(ip);
        }
        //System.out.println(getIpAddress());
        //main1();

    }

    private static String getIpAddress() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        try {
            url = new URL("https://"+dateFormat.format(new Date())+".ip138.com/ic.asp");//爬取的网站是百度搜索ip时排名第一的那个
            urlconn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            String buf = null;
            String get= null;
            while ((buf = br.readLine()) != null) {
                get+=buf;
            }
            int where,end;
            for(where=0;where<get.length()&&get.charAt(where)!='[';where++);
            for(end=where;end<get.length()&&get.charAt(end)!=']';end++);
            get=get.substring(where+1,end);
            return get;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param  the command line arguments
     */
    public static void main1() throws Exception{
        URL url = new URL("http://1111.ip138.com/ic.asp");
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        byte buf[] = new byte[40];
        int len;
        String ret = "";
        while((len=is.read(buf))!=-1){
            ret += new String(buf,"GB2312");
        }
        is.close();
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ret);

        if(m.find()){

            System.out.println(m.group(0));
        }

    }
}
