package com.zbl.wwj.concurrent.step2.p66;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/27
 * @Description:
 * @Version: $
 */
public class QueryFromHttpAction {

    public void execute(Context context) {
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    public void execute() {
        String name = ActionContext.getActionContext().getContext().getName();
        String cardId = getCardId(name);
        ActionContext.getActionContext().getContext().setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "1426011996-" + Thread.currentThread().getId();
    }
}
