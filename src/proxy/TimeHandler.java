package proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import proxy.simple.Tank;

/**
 * 对调用处理器的实现InvocationHandler
 * 
 * 达到目标：对任意的一个方法都能够自定义的处理
 */
public class TimeHandler implements InvocationHandler {

	//被代理的对象
	private Object target;
	
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}


	/*
	 * (non-Javadoc)
	 * @see proxy.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method)
	 * 第一个参数：对哪个对象(动态生成的代理对象TankTimeProxy)
	 * 第二个参数：对那个对象（动态生成的代理对象TankTimeProxy）的哪一个方法
	 */
	@Override
	public void invoke(Object o, Method m) {
			 long start = System.currentTimeMillis();
		 	 System.out.println("Start Time: " + start + "ms");
		 	 try {
		 		 //调用被代理对象的method方法m
				m.invoke(target);
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		 	 long end = System.currentTimeMillis();
		 	 System.out.println("time: " + (end - start) + "ms");
	}
}
