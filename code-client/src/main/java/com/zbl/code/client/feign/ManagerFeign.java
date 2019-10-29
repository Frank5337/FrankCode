package com.zbl.code.client.feign;

import com.zbl.code.common.pojo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: zbl
 * @Date: Created in 20:41 2019/10/29
 * @Description: 调manager
 * @Version: $
 */
@FeignClient(value = "code-manager", url = "localhost:8101/manager-api")
public interface ManagerFeign {

    /**
     *  测试Druid sql监控
     * @return phone
     */
    @GetMapping("/Hide")
    JsonResult<String> testDruid();
}
