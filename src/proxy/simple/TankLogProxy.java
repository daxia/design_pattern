package proxy.simple;

public class TankLogProxy implements Moveable{
	
	Moveable t;
	public TankLogProxy(Moveable t){
		super();
		this.t = t;
	}
	@Override
	public void move() {
		System.out.println("Log: tank Launch....");
		t.move();
		System.out.println("Log: tank stop....");
	}
	
}
