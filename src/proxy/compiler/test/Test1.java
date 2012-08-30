package proxy.compiler.test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import proxy.simple.Moveable;
import proxy.simple.Tank;

public class Test1 {
	public static void main(String[] args) throws Exception {
		String rt = "\r\n";
		String src =
		"package proxy;" + rt +
		"import proxy.simple.Moveable;" + rt +
		"public class TankTimeProxy implements Moveable{" + rt +

			"Moveable t;" + rt +
			"public TankTimeProxy(Moveable t){" + rt +
			"	 super();" + rt +
			"	 this.t = t;" + rt +
			"}" + rt +
			"@Override" + rt +
			"public void move() {" + rt +
			"	 long start = System.currentTimeMillis();" + rt +
			"	 System.out.println(\"Start Time: \" + start + \"ms\");" + rt +
			"	 t.move();" + rt +
			"	 long end = System.currentTimeMillis();" + rt +
			"	 System.out.println(\"time: \" + (end - start) + \"ms\");" + rt +
			"}" + rt +

		"}" ; 
		
		String fileName = System.getProperty("user.dir") + "/src/proxy/TankTimeProxy.java";
		
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		
		//编译文件代码（compiler）
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  //获取当前java编译器，并赋给java编译器对象compiler
		System.out.println(compiler); //need jdk1.6  这里需要jdk，非jre
		
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		Iterable units =  fileMgr.getJavaFileObjects(fileName);
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		t.call();
		fileMgr.close();
		
		//load into memory and 
		URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/src/")};  //class 路径地址
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("proxy.TankTimeProxy");  //加载
		System.out.println(c);
		
		//create an instance
		Constructor ct = c.getConstructor(Moveable.class);
		Moveable m = (Moveable) ct.newInstance(new Tank());
		m.move();
		
	}
}
