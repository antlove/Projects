package dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {

	private Object target = null;
	
	public LogHandler(Object target) {
		this.target = target;
	}

	private void doBefore(){
		System.out.println("do before ... ");
	}
	
	private void doAfter(){
		System.out.println("do after ... ");
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
	
		doBefore();
		
		Object res = method.invoke(target, args);
		
		doAfter();
		
		return res;
	}

}
