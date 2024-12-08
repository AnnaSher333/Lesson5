package proxy;

public interface Calculator {
    @Cache
    int calc(int arg);
}
