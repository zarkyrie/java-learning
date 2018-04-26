package cn.liangjieheng.learning.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {

    /**
     * 声明服务端必须提供的服务
     */
    String service(String content) throws RemoteException;

}
