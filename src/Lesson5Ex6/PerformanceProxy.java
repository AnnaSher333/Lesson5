package Lesson5Ex6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceProxy implements InvocationHandler {
    private final Object target;

    public PerformanceProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            long startTime = System.nanoTime();
            Object result = method.invoke(target, args);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println("Время работы метода: " + elapsedTime + " (в наносек)");
            return result;
        }
        return method.invoke(target, args);
    }
    @SuppressWarnings("unchecked")
    public static <T> T create(T target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new PerformanceProxy(target));
    }

}
