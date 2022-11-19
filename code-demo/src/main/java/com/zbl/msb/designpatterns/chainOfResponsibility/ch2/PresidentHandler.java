package com.zbl.msb.designpatterns.chainOfResponsibility.ch2;

/**
 * @Author: zbl
 * @Date: Created in 2022/8/30
 * @Description:
 * @Version: $
 */
public class PresidentHandler implements Handler {

    @Override
    public void process(Request request, Response response, HandlerChain chain) {
        System.out.println("President perform check");
        chain.process(request,response);
        System.out.println("President give instructions");
    }

}
