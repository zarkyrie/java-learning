package cn.liangjieheng.learning.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private static final int port = 12301;

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    byte[] bytes = new byte[20];
                    OutputStream outputStream = socket.getOutputStream();
                    int length = 0;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((length = bufferedInputStream.read(bytes)) != -1) {
                        System.out.println(new String(bytes,0,length));
                        stringBuilder.append(new String(bytes,0,length) + " world");
                    }
                    byte[] bytes1 = stringBuilder.toString().getBytes();
                    outputStream.write(bytes, 0, bytes1.length);
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.shutdownInput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
