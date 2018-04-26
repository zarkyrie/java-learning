package cn.liangjieheng.learning.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private static final int port = 12018;

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    System.out.println("accept!");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                    String line = bufferedReader.readLine();

                    while (!line.contains("byte")) {
                        printWriter.print("continue!");
                        line = bufferedReader.readLine();
                        System.out.println("Client say : " + line);
                    }
                    printWriter.print("byte Client");

                    System.out.println("Client exit");

                    bufferedReader.close();
                    printWriter.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
