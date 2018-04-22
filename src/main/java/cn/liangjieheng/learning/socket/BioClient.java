package cn.liangjieheng.learning.socket;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {

    public static void main(String[] args)  {
        System.out.println("请输入:");
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 12301);
            OutputStream outputStream = socket.getOutputStream();
            String str = "hello";
            System.out.println(str);
            outputStream.write(str.getBytes(),0,str.getBytes().length);
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            int length = 0;
            byte[] bytes = new byte[20];
            while ((length = bufferedInputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,length));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {

        }
    }
}
