package proxy.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import proxy.InvocationHandler;

public class TransactionHandler implements InvocationHandler{

	private Object target;
	
	//参数：被代理对象
	public TransactionHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public void invoke(Object o, Method m) {
		System.out.println("Transaction Start...");
		try {
			m.invoke(target);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("Transaction Commit...");
	}

}
