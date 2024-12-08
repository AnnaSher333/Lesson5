package Lesson5Ex6;

import ru.sber.school.reflection.proxy.Cache;

public interface Calculator {
    @Metric
    int calc(int num);
}
