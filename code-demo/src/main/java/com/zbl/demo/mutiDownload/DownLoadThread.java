package com.zbl.mutiDownload;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zbl
 * @Date: Created in 17:01 2020/4/15
 * @Description: 多线程来下载文件
 * @Version: $
 */
public class DownLoadThread extends Thread {

    private String url;

    private String fileName;

    private int threadId;

    private long startIndex;

    private long endIndex;

    private HttpURLConnection connection;

    private RandomAccessFile raf;

    private InputStream inputStream;

    private CountDownLatch latch;

    /**
     * 进度文件
     */
    private RandomAccessFile progressRaf;

    public DownLoadThread(String url, String fileName, int threadId, long startIndex, long endIndex, CountDownLatch latch) {
        super();
        this.url = url;
        this.fileName = fileName;
        this.threadId = threadId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url + "?ts=" + System.currentTimeMillis()).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            //设置请求范围
            connection.setRequestProperty("RANGE", "bytes=" + startIndex + "-" + endIndex);
            //断点续下载
            //判断进度文件是否存在, 不存在则创建, 并且存入startIndex的值
            File progressFile = new File(String.valueOf(threadId));
            if (progressFile.exists()) {
                progressRaf = new RandomAccessFile(progressFile, "rwd");
            } else {
                progressFile.createNewFile();
                progressRaf = new RandomAccessFile(progressFile, "rwd");
                progressRaf.write(String.valueOf(startIndex).getBytes());
            }

            //这时进度文件一定存在, 就读取上次结束为止, 若为第一次下载, 读取的依旧是startIndex的值
            progressRaf.seek(0);
            startIndex = Long.parseLong(progressRaf.readLine());

            //当请求部分数据成功的时候, 返回http状态码为206
            if (connection.getResponseCode() == 206) {
                inputStream = connection.getInputStream();
                byte[] bytes = new byte[1024 * 8];
                int len;
                raf = new RandomAccessFile(fileName, "rwd");
                //把开始写的位置设置为startIndex, 与请求数据的位置一致
                raf.seek(startIndex);

                long total = 0;
                long position = startIndex + total;

                while ((len = inputStream.read(bytes)) != -1) {
                    total += len;
                    raf.write(bytes, 0, len);

                    /**
                     *  实现同步进度, 必须写到raf.write(bs, 0, len);之后
                     *  因为如果先写到进度再去写目标文件的话, 那么当写完进度后被停掉程序,目标文件那部分是没有数据的
                     *  下次续传的时候还是从上次结束哪里开始会话, 数据依旧是丢失的
                     *
                     *  考虑若先同步目标文件 再去同步进度文件, 即使同步目标文件程序被停掉
                     *  那么下次续传顶多就把没有写入进度那部分再重新下载一次, 对整个程序影响不大
                     *
                     */
                    progressRaf.seek(0);
                    progressRaf.write(String.valueOf(position).getBytes());

                }
                //下载完毕就把进度文件删除
                progressFile.delete();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                    connection = null;
                }
                if (raf != null) {
                    raf.close();
                    raf = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
