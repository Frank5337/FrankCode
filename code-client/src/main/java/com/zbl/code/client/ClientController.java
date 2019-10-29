package com.zbl.code.client;


import com.zbl.code.common.base.BaseController;
import com.zbl.code.common.base.Global;

/**
 * @author zbl
 * @date 2018/5/15
 * <p>
 * 管理端通用控制器
 */
public class ClientController extends BaseController {

    protected Long getUserId() {
        return (Long) req.getSession().getAttribute(Global.KEY_USER_ID);
    }
}
