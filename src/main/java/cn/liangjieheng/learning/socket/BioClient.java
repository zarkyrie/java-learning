package cn.liangjieheng.learning.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {

    public static void main(String[] args)  {
        System.out.println("请输入:");
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 2018);
            socket.setSoTimeout(60000);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String result = "";


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {

        }
    }
}
