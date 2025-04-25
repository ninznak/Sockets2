package ru.alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        final String name = "Stepan";
        final String serverAdress = "netology.homework";
        final String serverResponse = "Ответ пришел от сервера -> ";

        try (Socket clientS = new Socket(serverAdress, Server.PORT);
             PrintWriter writer = new PrintWriter(clientS.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientS.getInputStream()))
        ) {
            System.out.println(serverResponse + reader.readLine());              // первый ответ от сервера
            writer.println(name);                                                // отправляем имя

            System.out.println(serverResponse + reader.readLine());              // ответ сервера с именем
            String ageAsk = reader.readLine();

            System.out.println(ageAsk);                                         // запрос на возраст
            writer.println("no");

            System.out.println(serverResponse + reader.readLine());             // сообщение о допуске к сервису или нет

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
