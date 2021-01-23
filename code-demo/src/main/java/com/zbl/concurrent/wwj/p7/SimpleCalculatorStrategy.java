package com.zbl.concurrent.wwj.p7;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/23
 * @Description:
 * @Version: $
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {

    private static final double SALARY_RATE = 0.1;
    private static final double BONDS_RATE = 0.1;

    @Override
    public double calculate(double salary, double bonds) {
        return salary * SALARY_RATE + bonds * BONDS_RATE;
    }
}
