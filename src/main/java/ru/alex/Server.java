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
        final int namePosition = 2;

        try(ServerSocket serverSocket = new ServerSocket(PORT)){

            while (true){
                try(Socket clientSocket = serverSocket.accept();
                    PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                )
                {
                    String dataFromClient = clientReader.readLine();
                    String nameClient = dataFromClient.split(" ")[namePosition];
                    printWriter.println("Привет " + nameClient + "! Твой порт " + clientSocket.getPort());
                    System.out.println(dataFromClient);
                }
            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}