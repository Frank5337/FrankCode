package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:29
 * @Description:
 * @Version: $
 */
class ActiveObjectProxy implements ActiveObject {

    private final SchedulerThead schedulerThead;

    private final Servant servant;

    public ActiveObjectProxy(SchedulerThead schedulerThead, Servant servant) {
        this.schedulerThead = schedulerThead;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult futureResult = new FutureResult();
        schedulerThead.invoke(new MakeStringRequest(servant, futureResult, count, fillChar));
        return futureResult;
    }

    @Override
    public void displayString(String text) {
        schedulerThead.invoke(new DisplayStringRequest(servant, text));
    }
}
