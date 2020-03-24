package org.jahom.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/*
 *@author jahom
 *@date 2020/3/20 18:22
 */
public class ProcessHand implements Runnable {
    Socket socket;
    Object service;

    public ProcessHand(Object service, Socket socket) {
        this.socket = socket;
        this.service = service;
    }


    @Override
    public void run() {
        System.out.println("ProcessHand run");
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            ClientRequst clientRequst = (ClientRequst) objectInputStream.readObject();
            Object res = invoke(clientRequst);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(res);
            objectOutputStream.flush();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object invoke(ClientRequst clientRequst) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = clientRequst.getMethodName();
        Class<?>[] paramsType = clientRequst.getParamsType();
        Object[] params = clientRequst.getParams();
        Method method = service.getClass().getMethod(methodName, paramsType);
        return method.invoke(service, params);

    }
}
