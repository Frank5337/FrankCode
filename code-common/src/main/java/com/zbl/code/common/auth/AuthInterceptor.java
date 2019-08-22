package com.zbl.code.common.auth;

import com.zbl.code.common.base.Global;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.common.pojo.JsonResult;
import com.zbl.code.common.util.JsonUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * @Author: zbl
 * @Date: Created in 9:40 2019/8/22
 * @Description: 全局拦截器, 拦截未登录的
 * @Version: $
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            // 需要管理员登录
            if (require(method, RequireAdmin.class) && req.getSession().getAttribute(Global.KEY_ADMIN_ID) == null) {
                errorResp(resp);
                return false;
            }

            // 需要C端用户登录
            if (require(method, RequireUser.class) && req.getSession().getAttribute(Global.KEY_USER_ID) == null) {
                errorResp(resp);
                return false;
            }
        }
        return super.preHandle(req, resp, handler);
    }

    //抛个异常, 请先登录
    private void errorResp(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtil.toJson(JsonResult.failure(ErrorCodeEnum.GL_NEED_SIGN_IN)));
    }

    //判断某方法上有无某注解
    private boolean require(HandlerMethod method, Class<? extends Annotation> annotationClass) {
        return method.getMethod().getDeclaringClass().isAnnotationPresent(annotationClass) ||
                method.getMethod().isAnnotationPresent(annotationClass);
    }
}
