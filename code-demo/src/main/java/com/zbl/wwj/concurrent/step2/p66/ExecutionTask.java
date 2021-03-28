package com.zbl.wwj.concurrent.step2.p66;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/27
 * @Description:
 * @Version: $
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
//        final Context context = new Context();
//        queryAction.execute(context);
//        System.out.println("The name is query success");
//        httpAction.execute(context);
//        System.out.println("The cardId is query success");
//        System.out.println("The name is " + context.getName() + "  cardId:" + context.getCardId());

        queryAction.execute();
        System.out.println("The name is query success");
        httpAction.execute();
        System.out.println("The cardId is query success");
        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The name is " + context.getName() + "  cardId:" + context.getCardId());
    }

}
