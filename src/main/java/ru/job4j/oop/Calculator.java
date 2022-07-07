package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public float divide(int a) {
        return a / x;
    }

    public float sumAllOperations(int a) {
        return sum(a) + this.multiply(a) + minus(a) + this.divide(a);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println(rsl);
        System.out.println(calculator.sumAllOperations(10));
        System.out.println(calculator.divide(5));
        System.out.println(minus(20));
    }
}
