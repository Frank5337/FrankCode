//package com.example.code17.util;
//
//import okhttp3.OkHttpClient;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocketFactory;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author: Frank
// * @Date: Created in 2023/3/2
// * @Description:
// * @Version: $
// */
//public class Main {
//
//    private void initHttpsClient() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder() .connectTimeout(30000L, TimeUnit.MILLISECONDS)
//                .readTimeout(30000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("OkHttpClient"))
//                .hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                });
//        if(AppParams.isBypassAuthen){
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//        }else{
//            SSLContext sslContext = null;
//            try {
//                sslContext = SSLContext.getInstance("TLS");
//                try {
//                    sslContext.init(null, null, null);
//                } catch (KeyManagementException e) {
//                    e.printStackTrace();
//                }
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//
//            SSLSocketFactory socketFactory = new Tls12SocketFactory(sslContext.getSocketFactory());
//            builder.sslSocketFactory(socketFactory,new HttpsUtils.UnSafeTrustManager());
//        }
//        OkHttpClient okHttpClient = builder
//                .build();
//        OkHttpUtils.initClient(okHttpClient);
//    }
//}
