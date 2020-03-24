package org.jahom.demo;


import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 *@author jahom
 *@date 2020/3/24 12:29
 */
public class RemoteInvocationHander implements InvocationHandler {
    private String host;
    private int port;


    public RemoteInvocationHander(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ClientRequst clientRequst = new ClientRequst();
        clientRequst.setClassName(method.getDeclaringClass().getName());
        clientRequst.setMethodName(method.getName());
        clientRequst.setParams(args);
        clientRequst.setParamsType(method.getParameterTypes());

        ClientTransfer clientTransfer = new ClientTransfer(host, port);
        return clientTransfer.Send(clientRequst);
    }


}
