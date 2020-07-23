package com.zbl.test;

import java.util.ArrayList;

/**
 * @Author: zbl
 * @Date: Created in 16:07 2020/6/28
 * @Description:
 * @Version: $
 */
public class FkTest {


    public static void main(String[] args) {
        double a1 = 10;
        System.out.println("before: " + a1);//10.0
        change1(a1);
        System.out.println("after: " + a1);//10.0

        Employee employee = new Employee("foreign", 10000);
        System.out.println("before : " + employee.getSalary());//10000.0
        change2(employee);
        System.out.println("after : " + employee.getSalary()); //10200.0

        Employee employee1 = new Employee("bob", 1000);
        Employee employee2 = new Employee("alice", 2000);
        System.out.println("before : " + employee1.getName());//bob
        System.out.println("before : " + employee2.getName());//alice
        swap(employee1.name, employee2.name);
        System.out.println("after : " + employee1.getName());//alice
        System.out.println("after : " + employee2.getName());//bob
        ArrayList list1 = new ArrayList();
        list1.add("1");
        ArrayList list2 = new ArrayList();
        System.out.println(list1.size() + "/" + list2.size());// 1/0
        add(list1, list2);
        System.out.println(list1.size() + "/" + list2.size());// 1/0
    }

    private static void add(ArrayList list1, ArrayList list2) {
        ArrayList temp = list1;
        list1 = list2;
        list2 = temp;
        System.out.println("add()::" + list1.size() + "/" + list2.size());// 0/1
    }

    public static void change1(double x1) {
        x1 = 3 * x1;
        System.out.println("x1: " + x1);
    }

    public static void change2(Employee employee) {
        employee.raiseSalary(200);
    }

    public static void swap(String x, String y) {
        String temp = x;
        x = y;
        y = temp;
        System.out.println("swap");
        System.out.println("x: " + x);
        System.out.println("y: " + y);
    }

    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public void raiseSalary(int i) {
            salary += i;
        }
    }

}
