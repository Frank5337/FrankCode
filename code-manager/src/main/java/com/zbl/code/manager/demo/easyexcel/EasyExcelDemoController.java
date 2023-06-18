package com.zbl.code.manager.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;

/**
 * @author: Frank
 * @date: Created in 2023/6/18
 */
@RestController
@RequestMapping("/easy/excel")
@Slf4j
@Api(tags = "01.Demo-03: EasyExcelDemoController")
public class EasyExcelDemoController {

    @Resource
    private HttpServletResponse httpServletResponse;

    @GetMapping("/demo")
    public void demo(){
        try {
            UserExcelData userExcelData = new UserExcelData();
            userExcelData.setUserName("用户姓名");
            userExcelData.setPassword("密码");
            userExcelData.setAddress("联系地址");
            userExcelData.setAge("年龄");
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("EasyExcelDemo", "UTF-8");
            httpServletResponse.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(httpServletResponse.getOutputStream(), UserExcelData.class)
                    .sheet("Attendance Record").doWrite(Collections.singletonList(userExcelData));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
