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

        try(Socket clientS = new Socket(serverAdress, Server.PORT);
            PrintWriter writer = new PrintWriter(clientS.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientS.getInputStream()))
        ){
            writer.println("Hello from " + name);
            System.out.println("Ответ пришел от сервера -> " + reader.readLine());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}