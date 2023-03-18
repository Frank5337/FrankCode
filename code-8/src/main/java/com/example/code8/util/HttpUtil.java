package com.example.code8.util;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ck created in 2021/9/13 14:00
 */
public class HttpUtil {
    public static String doGet(String url) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        OkHttpClient client = new OkHttpClient.Builder()
                // 忽略掉https 证书的校验
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                // 设置超时时间
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().get().url(url).header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                .build();
        String httpCode;

        List<ConnectionSpec> connectionSpecs = client.connectionSpecs();
        connectionSpecs.get(0).tlsVersions();

        try (Response response = client.newCall(request).execute()) {
            httpCode = String.valueOf(response.code());
        } catch (IOException e) {
            return e.getMessage();
        }
        return httpCode;
    }

    public static void main(String[] args) {
        System.out.println(HttpUtil.doGet("https://www.baidu.com"));
    }

}
