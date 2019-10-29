package com.zbl.code.manager;


import com.github.pagehelper.ISelect;
import com.zbl.code.common.base.BaseController;
import com.zbl.code.common.base.Global;
import com.zbl.code.common.pojo.ExtraPageData;
import com.zbl.code.common.pojo.JsonResult;
import com.zbl.code.common.pojo.ManagerData;
import com.zbl.code.common.util.RequestUtil;
import com.zbl.code.system.service.ConfigService;
import com.zbl.code.system.service.RolePermissionService;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;

/**
 * @author hook
 * @date 2018/5/15
 * <p>
 * 管理端通用控制器
 */
public class ManagerController extends BaseController {
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private ConfigService configService;

    protected Long getAdminId() {
        return (Long) req.getSession().getAttribute(Global.KEY_ADMIN_ID);
    }

    protected String getAdminName() {
        return (String) req.getSession().getAttribute(Global.KEY_ADMIN_NAME);
    }

    protected Long getRoleId() {
        return (Long) req.getSession().getAttribute(Global.KEY_ADMIN_ROLE_ID);
    }

    protected Integer getLevel() {
        return (Integer) req.getSession().getAttribute(Global.KEY_ADMIN_LEVEL);
    }

    protected Long getShopId() {
        return (Long) req.getSession().getAttribute(Global.KEY_ADMIN_HEAD_OFFICE_ID);
    }

    protected Long getShopId(Long shopId) {
        return getShopId() == null ? shopId : getShopId();
    }

    protected <T> JsonResult<ExtraPageData<T, ManagerData>> successListByPermissions(
            Integer pageNum, Integer pageSize, ISelect iSelect) {
        ManagerData extra = new ManagerData();
        extra.setOperates(getOperates());
        return JsonResult.ok(pageNum, pageSize, iSelect, extra);
    }

    protected <T, V> JsonResult<ExtraPageData<T, ManagerData<V>>> successListByPermissions(
            Integer pageNum, Integer pageSize, ISelect iSelect, V extraExtra) {
        ManagerData<V> extra = new ManagerData<>();
        extra.setOperates(getOperates());
        extra.setExtra(extraExtra);
        return JsonResult.ok(pageNum, pageSize, iSelect, extra);
    }

    protected <T, V> JsonResult<ExtraPageData<V, ManagerData>> successListByPermissions(
            Integer pageNum, Integer pageSize, ISelect iSelect, Function<T, V> function) {
        ManagerData extra = new ManagerData();
        extra.setOperates(getOperates());
        return JsonResult.ok(pageNum, pageSize, iSelect, function, extra);
    }

    protected <T, V, W> JsonResult<ExtraPageData<V, ManagerData<W>>> successListByPermissions(
            Integer pageNum, Integer pageSize, ISelect iSelect, Function<T, V> function, W extraExtra) {
        ManagerData<W> extra = new ManagerData<>();
        extra.setExtra(extraExtra);
        extra.setOperates(getOperates());
        return JsonResult.ok(pageNum, pageSize, iSelect, function, extra);
    }

    private List<String> getOperates() {
        String[] invoke = RequestUtil.getClassAndMethod("(com\\.zbl\\.code\\.manager\\.).*");
        return rolePermissionService.listOperate(getRoleId(), invoke[0], invoke[1]);
    }
}
