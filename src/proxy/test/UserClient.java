package proxy.test;

import proxy.InvocationHandler;
import proxy.Proxy;
import proxy.TimeHandler;

public class UserClient {
	public static void main(String[] args) throws Exception {

		// 被代理对象
		UserMgr mgr = new UserMgrImpl();

		// 方法处理器：处理被代理对象的方法，变成需要的方法
		InvocationHandler h = new TransactionHandler(mgr);
		//InvocationHandler h2 = new TimeHandler(h);
		//生成 代理对象
		UserMgr u = (UserMgr) Proxy.newProxyInstance(UserMgr.class, h);
		
		u.addUser();
	}
}
