package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 14:33
 * @Description: 对应ActiveObject的每一个方法
 * @Version: $
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();

}
