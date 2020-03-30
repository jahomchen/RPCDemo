package org.jahom.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/*
 *@author jahom
 *@date 2020/3/20 18:22
 */
public class ProcessHand {
    SocketChannel channel;
    Object service;

    public ProcessHand(Object service, SocketChannel channel) {
        this.channel = channel;
        this.service = service;
    }


    public void run() {
        System.out.println("ProcessHand run");
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel.read(buffer);
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));

            ClientRequst clientRequst = (ClientRequst) objectInputStream.readObject();
            Object res = invoke(clientRequst);

            buffer.clear();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(res);
            objectOutputStream.flush();
            buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            channel.write(buffer);
            buffer.clear();

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
