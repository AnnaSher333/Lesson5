package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HelloWorld {
    private final String name;

    public HelloWorld(String name) {
        this.name = name;
    }

    private void sayHello() {
        System.out.println("Hello, " + name);
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        new HelloWorld("World").sayHello();
        Class hw = HelloWorld.class;
        Method[] methods1 = hw.getMethods();//все свои public + родительские
        Method[] methods2 = hw.getDeclaredMethods();//все свои + свои private кроме родительских

        ArrayList<String> methods = new ArrayList<>();
        for (Method m : methods1) {
            methods.add(m.getName());
        }
        for (Method m : methods2) {
            //проверяем на наличие в листе, чтобы не дублироваь public методы
            if (!methods.contains(m.getName())) {
                methods.add(m.getName());
            }
        }
        //задача 2
        System.out.println("Имена всех методов класса HelloWorld:");
        methods.stream().forEach(System.out::println);
        //задача 3
        System.out.println("Имена всех геттеров:");
        methods.stream().filter(el -> el.matches("get.*")).forEach(System.out::println);

        //задача 4
        Class mfd = ManyFieldDto.class;
        Field[] fields = mfd.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().equals(String.class)
                    && java.lang.reflect.Modifier.isStatic(f.getModifiers())
                    && java.lang.reflect.Modifier.isFinal(f.getModifiers())) {
                String value = (String) f.get(null); // Получаем значение константы
                String name = f.getName(); // Получаем имя константы
                if (value.equals(name)) {
                    System.out.println("Значение константы " + name + " совпадает с ее именем: " + value);
                }
            }
        }
    }
}


