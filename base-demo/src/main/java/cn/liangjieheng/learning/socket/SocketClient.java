package cn.liangjieheng.learning.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) {
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            Socket socket = new Socket("127.0.0.1", 12018);
            socket.setSoTimeout(60000);

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String result = "";

            while (!result.contains("bye")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String to = reader.readLine();
                System.out.println("Client say : " + to);
                printWriter.println(to);
                result = bufferedReader.readLine();
                System.out.println("Server say : " + result);
            }

            printWriter.close();
            bufferedReader.close();
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
