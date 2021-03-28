package com.zbl.wwj.concurrent.step2.p66;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/27
 * @Description:
 * @Version: $
 */
public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = ThreadLocal.withInitial(Context::new);

    private static class ContextHolder {
        private static final ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }

    public void clear() {
        threadLocal.remove();
    }

    private ActionContext() {
    }
}
