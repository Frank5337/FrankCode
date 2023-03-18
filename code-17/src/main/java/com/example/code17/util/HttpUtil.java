package com.example.code17.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.Duration;

/**
 * @author ck created in 2021/9/13 14:00
 */
public class HttpUtil {

    public static String doGet(String url) {

//        OkHttpClient client = new OkHttpClient.Builder()
//                // 忽略掉https 证书的校验
//                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
//                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//                // 设置超时时间
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .callTimeout(Duration.ofSeconds(10))
                .build();// 设置总超时时间30秒

        Request request = new Request.Builder().get().url(url)
//                .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                .build();
        String httpCode;

        try (Response response = client.newCall(request).execute()) {
            httpCode = String.valueOf(response.code());
        } catch (IOException e) {
            return e.getMessage();
        }
        return httpCode;
    }


    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        System.out.println(HttpUtil.doGet("https://www.baidu.com"));
        System.out.printf("use %s%n", System.currentTimeMillis() - s);
    }

}
