package cn.liangjieheng.learning.url;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class UrlFailConnectTest {

    public static void main(String[] args) {
        PrintWriter out = null;
        long start = System.currentTimeMillis();
        //设置一个不存在的地址测试连接超时时间
        String urlStr = "http://localhost:8080/test";
        try {
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            //发送POST请求
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
//            设置连接超时时间
            urlConnection.setConnectTimeout(10000);
            // 获取URLConnection对象对应的输出流
            System.out.println(urlConnection.getConnectTimeout());
            out = new PrintWriter(urlConnection.getOutputStream());
            // 发送请求参数
            out.print("a=1&b=2");
            // flush输出流的缓冲
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            统计不设置连接超时时间，多久才报连接超时错误
            System.out.println("用时：" + (System.currentTimeMillis() - start));
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
