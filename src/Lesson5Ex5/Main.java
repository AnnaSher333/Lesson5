package Lesson5Ex5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Proxy.SERVER);
        System.out.println("Proxy запущен " + Proxy.SERVER);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ProxyRunning(clientSocket)).start();
        }
    }
}
