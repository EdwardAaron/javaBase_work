package com.example.proxy.jdk;

import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * created by dfk
 * 2022/4/27
 */
public class JDKProxyFactory {
    public static <T> T getProxy(T object) {
        Class<?>[] interfaces = object.getClass().getInterfaces();
        Object proxyObj = Proxy.newProxyInstance(object.getClass().getClassLoader(),
                interfaces, (proxy, method, args) -> {
                    System.out.println("class:" + object.getClass().getName());
                    System.out.println("method:" + method.getName());
                    System.out.println("args:" + Arrays.toString(args));
                    //执行被代理对象的方法
                    Object res = method.invoke(object, args);
                    System.out.println("执行完成");
                    return res;
                });
        return (T) proxyObj;
    }

    public static void main(String[] args) {
        UserDao userDao = JDKProxyFactory.getProxy(new UserDaoImpl());
        System.out.println(userDao.add(1, +2));
        System.out.println(userDao.update("11"));
    }
}
