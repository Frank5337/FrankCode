package com.zbl.github;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author: zbl
 * @Date: Created in 2022/5/23
 * @Description:
 * @Version: $
 */
@SuppressWarnings("all")
public class GetGithubImageNameMain {

    static String url = "https://github.com/zbl5337/image/tree/master/images/blog/taylor/";

    public static void main(String[] args) throws Exception {
        parseProjectList(url);
    }

    public static void parseProjectList(String html) throws Exception {
        //创建 Document 对象（相当于把一个html字符串转换成Document对象）
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        HttpResponse result = httpClient.execute(request);
        String xml = EntityUtils.toString(result.getEntity(), "UTF-8");

        //获取标签
        Document document = Jsoup.parseBodyFragment(xml);
        Elements as = document.select("a");
        for (Element a : as) {
            Elements e = a.getElementsByAttributeValueStarting("href","/zbl5337/image/blob/master/images/blog/taylor/");
            for (Element element : e) {
                System.out.println(element.attr("title"));
            }
        }


    }



}
