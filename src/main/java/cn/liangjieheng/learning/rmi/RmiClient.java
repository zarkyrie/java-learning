package cn.liangjieheng.learning.rmi;

import java.rmi.Naming;

public class RmiClient {
    public static void main(String[] args) {
        final String url = "rmi://localhost:12018";
        try {
            // 在RMI服务注册表中查找名称为service02的对象，并调用其上的方法
            IService iService = (IService) Naming.lookup(url + "/service01");
            Class stubClass = iService.getClass();
            System.out.println(iService + " 是 " + stubClass.getName() + " 的实例！");
            // 获得本底存根已实现的接口类型
            Class[] interfaces = stubClass.getInterfaces();
            for (Class c : interfaces) {
                System.out.println("存根类实现了 " + c.getName() + " 接口！");
            }
            System.out.println(iService.service("你好！"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
