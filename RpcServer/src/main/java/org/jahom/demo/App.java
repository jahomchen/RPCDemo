package org.jahom.demo;

import org.jahom.demo.service.UserService;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.publisher(new UserServiceImp(), 8080);

        System.in.read();
    }
}
