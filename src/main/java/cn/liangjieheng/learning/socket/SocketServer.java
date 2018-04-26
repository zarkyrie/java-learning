package cn.liangjieheng.learning.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private static final int port = 12018;

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                System.out.println("Client connect!");
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {

                    String line = bufferedReader.readLine();
                    System.out.println("Client say : " + line);

                    while (!line.contains("bye")) {
                        printWriter.println("continue! you say : " + line);
                        line = bufferedReader.readLine();
                        System.out.println("Client say : " + line);
                    }
                    printWriter.println("byte Client");
                    System.out.println("Client disconnect!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
