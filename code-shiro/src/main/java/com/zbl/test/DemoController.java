package com.zbl.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zbl
 * @Date: Created in 2022/1/12 10:16
 * @Description:
 * @Version: $
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private Map<String, DefaultAdapter> map;

    @RequestMapping("/test01")
    public void test01() {
        Set<Map.Entry<String, DefaultAdapter>> entries = map.entrySet();
        for (Map.Entry<String, DefaultAdapter> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
