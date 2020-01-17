package com.zbl.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 9:37 2020/1/16
 * @Description: 责任链模式 3.0
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("<script>, 996");
        FilterChain f1 = new FilterChain();
        f1.add(new HTMLFilter()).add(new SensitiveFilter()).add((m) -> {m.setMsg(m.getMsg() + "---honey"); return true;});
        f1.doFilter(msg);
        System.out.println(msg.getMsg());
        FilterChain f2 = new FilterChain();
        f2.add(new zblFilter());
        f1.add(f2);
        f1.doFilter(msg);
        System.out.println(msg.getMsg());
    }

}

class Msg {

    private String msg;

    String getMsg() {
        return msg;
    }

    void setMsg(String msg) {
        this.msg = msg;
    }
}

interface Filter {
    boolean doFilter(Msg m);
}

class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public boolean doFilter (Msg msg){
        for (Filter f : filters) {
            if (!f.doFilter(msg)){
                return false;
            }
        }
        return true;
    }
}

class HTMLFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        m.setMsg(m.getMsg().replace("<" ,"[").replace(">" ,"]"));
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        m.setMsg(m.getMsg().replace("996" ,"955"));
        return true;
    }
}

class zblFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        m.setMsg(m.getMsg().replace("honey" ,"loving"));
        return true;
    }
}
