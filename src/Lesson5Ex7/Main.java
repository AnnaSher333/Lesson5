package Lesson5Ex7;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        From from = new From("Anna","Ivanovna",11, 'w');
        To to = new To("Gleb","Dmitrievich",28, 'm');

       //BeanUtils.assign(to, from);

        Field f1 = to.getClass().getDeclaredField("name");
        f1.setAccessible(true);
        String name = (String) f1.get(to);
        System.out.println(name);

        Field f2 = to.getClass().getDeclaredField("lastname");
        f2.setAccessible(true);
        String lastname = (String) f2.get(to);
        System.out.println(lastname);

        Field f3 = to.getClass().getDeclaredField("birthday");
        f3.setAccessible(true);
        int age = (Integer) f3.get(to);
        System.out.println(age);

        Field f4 = to.getClass().getDeclaredField("pol");
        f4.setAccessible(true);
        char pol = (Character) f4.get(to);
        System.out.println(pol);
    }
}
