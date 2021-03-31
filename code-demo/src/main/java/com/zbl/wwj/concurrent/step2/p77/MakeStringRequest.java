package com.zbl.wwj.concurrent.step2.p77;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/31 15:01
 * @Description:
 * @Version: $
 */
//把每一个方法转成一个对象

/**
 * {@link ActiveObject#makeString(int, char)}
 */
public class MakeStringRequest extends MethodRequest {

    private final int count;

    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
       super(servant, futureResult);
       this.count = count;
       this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);
    }
}
