package Lesson5Ex7;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class BeanUtils {

public static void assign(Object to, Object from) throws NoSuchFieldException, IllegalAccessException {
    Class fr = from.getClass();
    Method[] frm = fr.getDeclaredMethods();

    Class t = to.getClass();
    Method[] tm = t.getDeclaredMethods();

    for (Method methodFrom: frm){ //зашли в методы from
        if (methodFrom.getName().matches("get.*")){ //если метод начинается на get
            String fromGetterName = methodFrom.getName().substring(3);//запоминаем подстроку без get
            for (Method methodTo: tm){//заходим в методы to
                if (methodTo.getName().matches("set.*")){//если метод начинается на set
                    String fromSetterName = methodTo.getName().substring(3);//запоминаем подстроку без set
                    if (fromSetterName.equals(fromSetterName)){//если эти строки равны (имена полей равны)
                        Field fromField = fr.getDeclaredField(fromGetterName.toLowerCase());//запоминаем поле из from
                        fromField.setAccessible(true);//открываем доступ к полю
                        Field toField = t.getDeclaredField(fromSetterName.toLowerCase());//запоминаем поле из to
                        toField.setAccessible(true);//открываем доступ к полю
                        if (fromField.getType().equals(toField.getType())){//если типы полей равны
                            Object valueFrom = fromField.get(from);//запоминаем значение поля from
                            toField.set(to, valueFrom);//присваиваем значение полю из set
                        }
                    }

                }
            }
        }
    }
}

}
