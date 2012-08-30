package proxy.simple;

public class Client {
	public static void main(String[] args){
		//代理
		//Tank,TankTimeProxy 和TankLogProxy都实现了Moveable接口
		//ttp和tlp顺序颠倒过来，又是一种组合，有点像装饰者模式，但是语义上不同
		//ttp可以代理tlp，tlp可以代理ttp
		Moveable tank = new Tank();
		TankTimeProxy ttp = new TankTimeProxy(tank);
		TankLogProxy tlp = new TankLogProxy(ttp);
		
		tlp.move();
	}
}
