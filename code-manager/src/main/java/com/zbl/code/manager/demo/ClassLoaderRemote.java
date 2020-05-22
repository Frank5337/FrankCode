package com.zbl.code.manager.demo;

import com.yunfan.yukai.controller.Tmd;
import com.zbl.code.common.component.RedisCache;
import com.zbl.code.common.util.TimeUtil;
import com.zbl.code.manager.demo.classLoader.OwnClassLoader;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zbl
 * @Date: 16:15 2020/4/5
 * @Description:
 */
@RestController
@RequestMapping("/Demo")
@Api(tags = "01.Demo-02: classLoader Remote Demo")
public class ClassLoaderRemote {

    @Resource
    private RedisCache redisCache;

    @GetMapping("classLoaderRemote")
    public Object classLoaderRemote() throws Exception{
        byte[] bytes = (byte[])redisCache.get("objV3");
        if (bytes != null) {
            Object remoteObj = new OwnClassLoader().loadRemoteClass(bytes);
            System.out.println(remoteObj);
            Tmd tmd = (Tmd) remoteObj;
            return tmd.getString();
        }
        return "404 ￣へ￣";

    }

    @PostMapping("classLoaderRemoteSet")
    public void classLoaderRemoteSet() throws Exception{
        //TODO 文件名换成要实现的 类.class
        InputStream is = new FileInputStream(new File(""));
        byte[] b = new byte[8 * 1024];
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            int len = -1;
            while ((len = is.read(b)) != -1 ) {
                bos.write(b, 0, len);
            }
            b = bos.toByteArray();
            redisCache.put("objV3", b, 60 * 60);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }




}

