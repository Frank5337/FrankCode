package com.zbl.wwj.concurrent.step2.p72;

import java.util.stream.IntStream;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 10:44
 * @Description:
 * @Version: $
 */
public class PreThreadClient {

    public static void main(String[] args) {
        final MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10).forEach(
                i -> handler.request(new Message("Msg" + i))
        );
        handler.shutdown();
    }

}
