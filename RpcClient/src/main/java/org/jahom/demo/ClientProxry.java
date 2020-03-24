package org.jahom.demo;

import java.io.IOException;
import java.lang.reflect.Proxy;

/*
 *@author jahom
 *@date 2020/3/24 12:25
 */
public class ClientProxry {
    public <T> T createProxy(Class<T> interfaceCla, String host, int port) throws IOException {
        return (T) Proxy.newProxyInstance(interfaceCla.getClassLoader(),
                new Class<?>[]{interfaceCla},
                new RemoteInvocationHander(host, port));
    }
}
