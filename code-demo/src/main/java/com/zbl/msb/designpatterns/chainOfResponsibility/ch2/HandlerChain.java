package com.zbl.msb.designpatterns.chainOfResponsibility.ch2;

/**
 * @Author: zbl
 * @Date: Created in 2022/8/30
 * @Description:
 * @Version: $
 */
public interface HandlerChain {

    void process(Request request, Response response);

}
