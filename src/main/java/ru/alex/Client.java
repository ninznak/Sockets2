package ru.alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        final String name = "Stepan";

        try(Socket clientS = new Socket("localhost", Server.PORT);
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