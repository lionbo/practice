package org.lionbo.practice.cglib;

public class CglibTest {

    public static void main(String[] args) {
        ProxyTarget target = new ProxyTarget();
        CglibProxy proxy = new CglibProxy();
        ProxyTarget proxyTarget = (ProxyTarget) proxy.createProxy(target);
        proxyTarget.sayHi();
    }

}
