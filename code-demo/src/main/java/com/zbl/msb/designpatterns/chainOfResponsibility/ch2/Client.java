package com.zbl.msb.designpatterns.chainOfResponsibility.ch2;

/**
 * @Author: zbl
 * @Date: Created in 2022/8/30
 * @Description:
 * @Version: $
 */
public class Client {
    public static void main(String[] args) {
        HandlerChain chain = new CircleHandlerChain();
        chain.process(new Request(), new Response());
    }
}