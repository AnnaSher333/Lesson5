package Lesson5Ex6;

public class CalculatorImpl implements Calculator{

    @Override
    public int calc(int num) {
        if (num == 0){
            return 1;
        }
        return num * calc(num-1);
    }
}
