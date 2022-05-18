package com.zbl.lpj.proxy.jdk;

public class JDKProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Calculator proxy = CalculatorProxy.getProxy(new MyCalculator());
        proxy.add(1, 1);
        System.out.println(proxy.getClass());
    }
}
