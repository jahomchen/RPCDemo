package org.jahom.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 *@author jahom
 *@date 2020/3/24 12:34
 */
public class ClientTransfer {
    private String host;
    private int port;
    private Socket socket;

    public ClientTransfer(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(host, port);
    }



    public Object Send(ClientRequst clientRequst) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(clientRequst);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object result = objectInputStream.readObject();

        objectInputStream.close();
        objectOutputStream.close();

        return result;
    }
}
