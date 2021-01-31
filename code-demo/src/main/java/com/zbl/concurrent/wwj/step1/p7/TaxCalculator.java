package com.zbl.concurrent.wwj.step1.p7;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/23
 * @Description:
 * @Version: $
 */
public class TaxCalculator {

    private final double salary;

    private final double bonus;

    private final CalculatorStrategy calculatorStrategy;

//    public TaxCalculator(double salary, double bonus) {
//        this.salary = salary;
//        this.bonus = bonus;
//    }

    public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

//    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
//        this.calculatorStrategy = calculatorStrategy;
//    }
}
