package org.jahom.demo;

import org.jahom.demo.service.UserService;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClientProxry clientProxry = new ClientProxry();
        UserService serviceProxy = clientProxry.createProxy(UserService.class, "localhost", 8080);
        Object res = serviceProxy.getUserCount(2);
        Object res1 = serviceProxy.getUserById(1);
        System.out.println(res);
        System.out.println(res1);
    }
}
