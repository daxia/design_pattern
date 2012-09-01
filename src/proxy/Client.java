package proxy;

import proxy.simple.Moveable;
import proxy.simple.Tank;

public class Client {
	public static void main (String[] args)  throws Exception{
		//被代理对象
		Tank t = new Tank();
		
		//将被代理对象的方法处理成 想要的方法的  方法处理器
		InvocationHandler h = new TimeHandler(t);
		
		//利用 通用的代理对象生成器Proxy 动态生成  想要的 代理对象 TankTimeProxy
		Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class, h);
		//System.out.println(m.getClass().getName());
		
		m.move();
	}
}

//动态代理好处：
//可以对任意的对象、任意的接口方法，实现任意的代理

