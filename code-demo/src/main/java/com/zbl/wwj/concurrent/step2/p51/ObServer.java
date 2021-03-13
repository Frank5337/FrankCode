package com.zbl.wwj.concurrent.step2.p51;


/**
 * @Author: zbl
 * @Date: Created in 2021/3/11 13:50
 * @Description:
 * @Version: $
 */
public abstract class ObServer {

    protected Subject subject;

    public ObServer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    protected abstract void update();
}
