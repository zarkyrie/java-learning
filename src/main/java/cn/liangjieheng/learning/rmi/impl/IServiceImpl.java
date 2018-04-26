package cn.liangjieheng.learning.rmi.impl;

import cn.liangjieheng.learning.rmi.IService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IServiceImpl extends UnicastRemoteObject implements IService {

    private String name;

    public IServiceImpl(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String service(String content) throws RemoteException {
        return "server >>" + content;
    }


}
