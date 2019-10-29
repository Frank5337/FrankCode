package com.zbl.code.client.controller;

import com.zbl.code.client.feign.ManagerFeign;
import com.zbl.code.common.pojo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zbl
 * @Date: Created in 20:44 2019/10/29
 * @Description:
 * @Version: $
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    private ManagerFeign managerFeign;

    @RequestMapping("/test")
    public JsonResult<String> test(){
        return managerFeign.testDruid();
    }
}
