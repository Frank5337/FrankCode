package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 14:54
 * @Description:
 * @Version: $
 */
public class RealResult implements Result {

    private final Object resultValue;

    public RealResult(final Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }

}
