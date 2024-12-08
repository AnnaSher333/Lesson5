package Lesson5Ex5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Proxy {
    public static final int SERVER = 333;
    static HashMap<String, byte[]> cache = new HashMap<>();
}
