package ru.alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final Integer PORT = 8081;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    printWriter.println("Тебя приветствует сервер! Скажи, как тебя зовут?");                // Сообщение при подключении
                    String nameFromClient = clientReader.readLine();

                    System.out.println("Получено имя подключившегося клиента. Его имя " + nameFromClient);
                    printWriter.println("Привет, " + nameFromClient + "! Теперь скажи, сколько тебе лет?"); // Сообщение после ввода имени
                    printWriter.println("Тебе больше 18 лет? (yes/no)");

                    String ageFromClient = clientReader.readLine();

                    if (ageFromClient.equals("yes")) {
                        printWriter.println("Привет, " + nameFromClient + "! Добро пожаловать на сайт!");
                    } else {
                        printWriter.println("Тебе нельзя пользоваться сервисом, если тебе нет 18 лет! Выход из программы.");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
