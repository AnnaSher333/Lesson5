package proxy;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

public class MainDynamic {

    public static void main(String[] args) {
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate));
        run(calculator);
    }

    private static void run(Calculator calculator) {
        System.out.println("START:"+LocalDateTime.now());
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(6));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println("END:"+LocalDateTime.now());

    }
}
