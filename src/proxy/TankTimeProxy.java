package proxy;
import proxy.simple.Moveable;
public class TankTimeProxy implements java.io.Serializable{
Moveable t;
public TankTimeProxy(Moveable t){
	 super();
	 this.t = t;
}
@Override
public void move() {
	 long start = System.currentTimeMillis();
	 System.out.println("Start Time: " + start + "ms");
	 t.move();
	 long end = System.currentTimeMillis();
	 System.out.println("time: " + (end - start) + "ms");
}
}