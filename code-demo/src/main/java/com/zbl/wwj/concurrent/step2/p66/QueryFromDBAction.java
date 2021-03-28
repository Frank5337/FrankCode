package com.zbl.wwj.concurrent.step2.p66;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/27
 * @Description:
 * @Version: $
 */
public class QueryFromDBAction {

    public void execute(Context context) {
        try {
            Thread.sleep(1000);
            String name = "Alex " + Thread.currentThread().getName();
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        try {
            Thread.sleep(1000);
            String name = "Alex " + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
