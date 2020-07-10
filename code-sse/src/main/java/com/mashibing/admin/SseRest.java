package com.mashibing.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(path = "/sse")
public class SseRest {

    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();

    @GetMapping(path = "/subscribe")
    public SseEmitter subscribe(String id) {
        // 超时时间设置为1小时
        SseEmitter sseEmitter = new SseEmitter(3600000L);
        sseCache.put(id, sseEmitter);
        // 超时回调 触发
        sseEmitter.onTimeout(() -> sseCache.remove(id));
        // 结束之后的回调触发
        sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
        return sseEmitter;
    }

    @GetMapping(path = "/push")
    public String push(String id, String content) throws IOException {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            // 发送消息
            sseEmitter.send(content);
        }
        return "over";
    }

    @GetMapping(path = "over")
    public String over(String id) {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            // 执行完毕，断开连接
            sseEmitter.complete();
            sseCache.remove(id);
        }
        return "over";
    }


    @GetMapping(path = "/push-all")
    public String pushAll(String content) throws IOException {
        for (String s : sseCache.keySet()) {
            SseEmitter sseEmitter = sseCache.get(s);
            if (sseEmitter != null) {
                // 发送消息
                sseEmitter.send(content);
            }
        }

        return "over";
    }
}