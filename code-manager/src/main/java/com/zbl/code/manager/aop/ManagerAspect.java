package com.zbl.code.manager.aop;

import com.zbl.code.common.auth.RequireAdmin;
import com.zbl.code.common.base.BaseAspect;
import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.pojo.JsonResult;
import com.zbl.code.common.util.RequestUtil;
import com.zbl.code.system.service.OperateLogService;
import com.zbl.code.system.service.RolePermissionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 9:31 2019/8/22
 * @Description: 后台管理切面, 包括了权限判断和日志记录
 * @Version: $
 */
@Aspect
@Component
public class ManagerAspect extends BaseAspect {

    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private OperateLogService operateLogService;

    @Pointcut("execution(public * com.zbl.code.manager..*.*(..))")
    private void managerPointCut() {

    }

    /**
     * 权限判断
     */
    @Before("managerPointCut()")
    public void doBefore(JoinPoint joinPoint) throws BaseException {
        //如果不需要管理员登录 或者权限忽略 或者是超管
        if (!require(joinPoint, RequireAdmin.class) || require(joinPoint, IgnorePermission.class)
                || getAdminRoleId() == 0L) {
            return;
        }

        rolePermissionService.check(getAdminRoleId(), getCtrl(joinPoint), getMethod(joinPoint));
    }

    /**
     * 日志记录
     */
    @AfterReturning(pointcut = "managerPointCut()", returning = "rthVal")
    public void afterReturning(JoinPoint joinPoint, Object rthVal) {
        if (require(joinPoint, DropOperateLog.class)) {
            return;
        }

        if (!require(joinPoint, RequireAdmin.class) && !require(joinPoint, SaveOperateLog.class)) {
            return;
        }

        JsonResult result = null;
        if (rthVal instanceof JsonResult) {
            result = (JsonResult) rthVal;
        }

        operateLogService.save(getAdminId(), RequestUtil.getRemoteHost(req),
                getCtrl(joinPoint), getMethod(joinPoint), RequestUtil.getUrl(req), RequestUtil.getParams(req),
                result == null ? null : result.getCode(), result == null ? null : result.getMsg());
    }
}
