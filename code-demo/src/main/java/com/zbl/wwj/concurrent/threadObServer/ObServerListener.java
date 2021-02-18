package com.zbl.wwj.concurrent.threadObServer;

/**
 * Create by : FanXiaoYun
 * Date      : 2019-10-23
 * Desribe   : 这是Observer角色，所有子类都将监听ObSserverRubable
 *
 * @author wyk
 */

public interface ObServerListener {

    public void onEvnt(RunnableEvent event);
}
