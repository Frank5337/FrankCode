package com.zbl.msb.designpatterns.chainOfResponsibility.ch2;

/**
 * @Author: zbl
 * @Date: Created in 2022/8/30
 * @Description:
 * @Version: $
 */
public class MayorHandler implements Handler {
    @Override
    public void process(Request request, Response response, HandlerChain chain) {
        System.out.println("MayorHandler perform check");
        chain.process(request,response);
        System.out.println("MayorHandler give instructions");
    }
}
