package com.zbl.msb.designpatterns.chainOfResponsibility.ch2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2022/8/30
 * @Description:
 * @Version: $
 */
public class CircleHandlerChain implements HandlerChain {
    private static List<Handler> handlers = new ArrayList<>();
    private static Iterator<Handler> handlerIterator = null;

    static {
        //模拟从配置文件加载handler
        handlers.add(new MayorHandler());
        handlers.add(new GovernorHandler());
        handlers.add(new PresidentHandler());
        handlerIterator = handlers.iterator();
    }

    @Override
    public void process(Request request, Response response) {
        if (handlerIterator.hasNext()){
            Handler handler = handlerIterator.next();
            handler.process(request,response,this);
        }
    }
}
