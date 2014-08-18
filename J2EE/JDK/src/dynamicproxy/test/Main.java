package dynamicproxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import dynamicproxy.handler.LogHandler;
import dynamicproxy.service.HelloService;
import dynamicproxy.service.impl.HelloServiceImpl;

public class Main {

	public static void main(String[] args) {
		HelloService target = new HelloServiceImpl();
		
		InvocationHandler handler = new LogHandler(target);
		
		HelloService helloService = (HelloService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
		
		helloService.sayHello();

	}

}
