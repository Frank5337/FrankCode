package com.zbl.code.common.base;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zbl
 * @Date: Created in 10:37 2019/8/21
 * @Description: 基础控制器
 * @Version: $
 */
public class BaseController {
    @Resource
    protected HttpServletRequest req;
    @Resource
    protected HttpServletResponse resp;

    /**
     * @InitBinder 用于在@Controller 标注的方法上,表示为当前控制器注册一个属性编辑器,只对当前Controller有效
     * @InitBinder 标注的方法必须有一个参数WebDataBinder , 所谓的属性编辑器可以理解为就是完成参数绑定
     *             ServletRequestDataBinder extends WebDataBinder
     *             转换 Date 的格式
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat(Global.DEF_TIME_FORMAT);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
