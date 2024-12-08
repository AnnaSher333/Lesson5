package Lesson5Ex6;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = PerformanceProxy.create(new CalculatorImpl());
        System.out.println(calculator.calc(3));
    }
}
