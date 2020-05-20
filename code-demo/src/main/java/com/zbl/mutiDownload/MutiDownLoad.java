package com.zbl.mutiDownload;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zbl
 * @Date: Created in 17:02 2020/4/15
 * @Description:
 * @Version: $
 */
public class MutiDownLoad {

    //线程数, 也叫把文件的分片数
    private static final int THREAD_COUNT = 5;
    //下载文件的路径
    private static final String DOWNLOAD_URL = "https://dl.google.com/chrome/mac/stable/GGRO/googlechrome.dmg";
    private static final String FILENAME = "E:/codeDownload/test.dmg";

    public static void main(String[] args) {
        long fileSize;
        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) new URL(DOWNLOAD_URL).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            if (connection.getResponseCode() == 200) {
                fileSize = connection.getContentLength();
                long eachSize = fileSize / THREAD_COUNT;
                //打开方式为rwd(读写) 每当进行写操作, 同步的刷新到磁盘, 刷新内容
                RandomAccessFile raf = new RandomAccessFile(FILENAME, "rwd");
                //先占用一段空间, 防止下载到一半空间不足
                raf.setLength(fileSize);
                raf.close();
                long start = System.currentTimeMillis();
                System.out.println("---start---");
                CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
                for (int i = 0; i <THREAD_COUNT ; i++) {
                    long startIndex = i * eachSize;
                    long endIndex = (i + 1) * eachSize - 1;
                    //如果是最后一个线程的时候, endIndex的值就为文件大小
                    if (i == THREAD_COUNT - 1) {
                        endIndex = fileSize;
                    }
                    new DownLoadThread(DOWNLOAD_URL, FILENAME, i, startIndex, endIndex, latch).start();
                }
                latch.await();
                System.out.println("耗时: " + (System.currentTimeMillis() - start));
            }
        } catch (Exception e){
            e.printStackTrace();
        }











    }

}
