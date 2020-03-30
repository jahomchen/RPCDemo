package org.jahom.demo;


import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


/*
 *@author jahom
 *@date 2020/3/20 16:55
 */
public class Server {

    private static Selector selector;

    public void publisher(Object service, int port) throws Exception {

        // 创建通道管理器(Selector)
        selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            selector.select();
            Iterator iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = (SelectionKey) iter.next();

                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    new ProcessHand(service, channel).run();
                }

                iter.remove();
            }
        }
    }
}
