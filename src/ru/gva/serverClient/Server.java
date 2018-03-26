package ru.gva.serverClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server ждет клиента");

        try (Socket clientSoclet = serverSocket.accept()){
            InputStream inputStream = clientSoclet.getInputStream();
            OutputStream outputStream = clientSoclet.getOutputStream();
            System.out.println("Новок соединение: "
            + clientSoclet.getInetAddress().toString());
            int request;
            while ((request = inputStream.read()) != -1){
                System.out.println("Прислан клиент: " + request);
                outputStream.write(++request);
                System.out.println("оправляю клиенту: " + request);
                outputStream.flush();
                Thread.sleep(2000);
            }
            System.out.println("клиент отключисля");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
