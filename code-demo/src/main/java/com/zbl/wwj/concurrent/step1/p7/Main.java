package com.zbl.wwj.concurrent.step1.p7;

import org.junit.Test;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/23
 * @Description: 策略模式
 * @Version: $ https://www.bilibili.com/video/BV1hJ411D7k2?p=7
 */
public class Main {

    @Test
    public void test01() throws Exception {
//        TaxCalculator calculator = new TaxCalculator(10000d, 2000d) {
//            @Override
//            public double calcTax() {
//                return getSalary() * 0.1 + getBonus() * 0.15;
//            }
//        };
//
//        double tax = calculator.calculate();
//        System.out.println(tax);

    }

    @Test
    public void test02() throws Exception {
//        TaxCalculator calculator = new TaxCalculator(10000d, 2000d);
//        calculator.setCalculatorStrategy(new SimpleCalculatorStrategy());
//        double tax = calculator.calculate();
//        System.out.println(tax);
    }

    @Test
    public void test03() throws Exception {
//        TaxCalculator calculator = new TaxCalculator(10000d, 2000d);
//        calculator.setCalculatorStrategy((s, b) -> s * 0.1 + b * 0.089);
//        double tax = calculator.calculate();
//        System.out.println(tax);
    }

    @Test
    public void test04() throws Exception {
        TaxCalculator calculator = new TaxCalculator(10000d, 2000d, (s, b) -> s * 0.1 + b * 0.089);
        double tax = calculator.calculate();
        System.out.println(tax);
    }
}
