package com.zbl.code.manager;

import com.zbl.code.common.pojo.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: zbl
 * @Date: Created in 11:15 2019/8/21
 * @Description:
 * @Version: $
 */
@RestController
@RequestMapping("/Hide")
@Api(tags = "00.系统管理-01: 非菜单接口, 如登录")
public class HideController {

    @Resource
    private DataSource dataSource;

    /**
     * 测试Druid sql监控
     *
     * @return phone
     */
    @GetMapping
    public JsonResult<String> testDruid() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql = "select phone from uc_user limit 1";
        ResultSet res = statement.executeQuery(sql);
        String phone = "";
        while (res.next()) {
            phone = res.getString("phone");
        }
        return JsonResult.ok(phone);
    }

    @ResponseBody
    @GetMapping(value = {
            "/gateway/conf/module/dbmodules/accessctrl/{fileName}",
            "/gateway/conf/common/{fileName}",
            "/gateway/conf/module/dbframework/{fileName}",
            "/gateway/conf/module/{x}/{y}/{fileName}"
    })
    public String test(HttpServletRequest req){
        return req.getRequestURI();
    }

    @Resource
    private RedisTemplate redisTemplate;


}
