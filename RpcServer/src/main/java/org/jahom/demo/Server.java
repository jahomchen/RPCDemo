package org.jahom.demo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/*
 *@author jahom
 *@date 2020/3/20 16:55
 */
public class Server {

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public void publisher(Object service, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                cachedThreadPool.execute(new ProcessHand(service,accept));
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
