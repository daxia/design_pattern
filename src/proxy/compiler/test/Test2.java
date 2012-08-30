package proxy.compiler.test;

import java.lang.reflect.Method;

public class Test2 {
	public static void main(String[] args){
		Method[] methods = proxy.simple.Moveable.class.getMethods();
		for(Method m: methods){
			System.out.println(m);
		}
	}
}
