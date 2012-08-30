package proxy.simple;

public class Client {
	public static void main(String[] args){
		//代理
		//Tank,TankTimeProxy 和TankLogProxy都实现了Moveable接口
		Moveable tank = new Tank();
		TankTimeProxy ttp = new TankTimeProxy(tank);
		TankLogProxy tlp = new TankLogProxy(ttp);
		
		tlp.move();
	}
}
