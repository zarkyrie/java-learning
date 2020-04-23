package cn.liangjieheng.learning.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 继承Remote接口可以标识为可以被远程调用的对象
 */
public interface IService extends Remote {

    /**
     * 声明服务端必须提供的服务
     */
    String service(String content) throws RemoteException;

}
