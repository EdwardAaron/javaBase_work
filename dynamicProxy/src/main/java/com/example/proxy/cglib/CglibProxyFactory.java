package com.example.proxy.cglib;

import com.example.dao.UserDaoImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * created by dfk
 * 2022/4/27
 */
public class CglibProxyFactory implements MethodInterceptor {
    public static <T> T getProxy(T object) {
        Enhancer enhancer = new Enhancer();
        //代理类需要继承目标类，（使用的是extend实现代理）
        enhancer.setSuperclass(object.getClass());
        //加强类设置代理类，当拦截到目标类的方法调用，用这个代理的intercept方法对原方法加强
        enhancer.setCallback(new CglibProxyFactory());
        //创建代理对象（这里的helloWorld才叫做代理对象）
        return (T)enhancer.create();
    }
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("class:" + object.getClass().getName());
        System.out.println("method:" + method.getName());
        System.out.println("args:" + Arrays.toString(args));

        Object res=methodProxy.invokeSuper(object, args);

        System.out.println("执行完成");

        return res;
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = CglibProxyFactory.getProxy(new UserDaoImpl());
        System.out.println(userDao.add(1, 1));
        System.out.println(userDao.update("11"));
    }
}
