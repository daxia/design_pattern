package proxy;

import java.lang.reflect.Method;

/*
 * 方法处理器：处理需要
 */
public interface InvocationHandler {
	//调用别人指定需要的东西
	//比喻：苹果代理商，就是调用和苹果手机一起搭配出售的那些配件
	//@param: Method :真实的方法（苹果手机），
	//这个方法就是对Method进行一些处理，（例如加一个苹果外壳，加一个吊坠，加一个移动sim卡）
	public void invoke(Object o, Method m);
}
