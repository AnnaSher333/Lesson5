package Lesson5Ex5;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import static ru.sber.school.reflection.Lesson5Ex5.Proxy.cache;

public class ProxyRunning implements Runnable {
    private Socket client;
    public ProxyRunning(Socket client) {this.client = client;}

    @Override
    public void run() {
        try {
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String str = reader.readLine();

            if (str == null || str.isEmpty()) {
                client.close();
                return;
            }
            String[] requestParts = str.split(" ");
            String method = requestParts[0];
            String url = requestParts[1];

            // Извлекаем хост и порт (если задан)
            URL urlObj = new URL(url);
            String host = urlObj.getHost();
            int port = urlObj.getPort() == -1 ? urlObj.getDefaultPort() : urlObj.getPort();

            // Формируем адрес для кэширования
            String cacheKey = url;
            if (cache.containsKey(cacheKey)) {
                // Если есть кэш, забираем из кэша
                System.out.println("Cache hit for: " + url);
                byte[] cachedResponse = cache.get(cacheKey);
                output.write(cachedResponse);
            } else {
                // Если кэша нет, пересылаем запрос на реальный хост
                System.out.println("Cache miss for: " + url);
                Socket serverSocket = new Socket(host, port);
                OutputStream serverOutput = serverSocket.getOutputStream();
                InputStream serverInput = serverSocket.getInputStream();
                // Пересылаем запрос на сервер
                serverOutput.write((str + "\r\n").getBytes());
                String headerLine;
                while (!(headerLine = reader.readLine()).isEmpty()) {
                    serverOutput.write((headerLine + "\r\n").getBytes());
                }
                serverOutput.write("\r\n".getBytes());
                serverOutput.flush();

                // Читаем ответ от сервера
                ByteArrayOutputStream responseBuffer = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = serverInput.read(buffer)) != -1) {
                    responseBuffer.write(buffer, 0, bytesRead);
                }
                byte[] response = responseBuffer.toByteArray();
                // Сохраним ответ в кэше
                cache.put(cacheKey, response);
                // Отправляем ответ клиенту
                output.write(response);
                serverSocket.close();
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
