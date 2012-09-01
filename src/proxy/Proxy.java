package proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import proxy.simple.Moveable;
import proxy.simple.Tank;

public class Proxy {
	public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception{
		String rt = "\r\n";
		String methodStr = "";
		String src = "";
		
		Method[] methods = infce.getMethods();
		/*
		for(Method m : methods){
			methodStr += "@Override" + rt + 
						 "public void " + m.getName() + "() {" + rt +
						 "	 long start = System.currentTimeMillis();" + rt +
						 "	 System.out.println(\"Start Time: \" + start + \"ms\");" + rt +
						 "	 t." + m.getName() +"();" + rt +
						 "	 long end = System.currentTimeMillis();" + rt +
						 "	 System.out.println(\"time: \" + (end - start) + \"ms\");" + rt +
						 "}";
		}*/
		//循环动态生成代理方法
		for(Method m : methods){
			methodStr += "@Override" + rt + 
						 "public void " + m.getName() + "() {" + rt +
						 "    try{" + rt +
						 "    Method md = " + infce.getName() +".class.getMethod(\"" +m.getName() +"\");"+  rt +
						 "    h.invoke(this, md);" + rt +
						 "    }catch(Exception e){e.printStackTrace();}" + rt +
						 "}";
		 src =
		"package proxy;" + rt +
		"import proxy.simple.Moveable;" + rt +
		"import java.lang.reflect.Method;" + rt +
		"public class TankTimeProxy implements "+ infce.getName() +"{" + rt +

		"	proxy.InvocationHandler h;" + rt +
		"	public TankTimeProxy(InvocationHandler h){" + rt +
		"		 super();" + rt +
		"		 this.h = h;" + rt +
		"	}" + rt +
		//方法体
		methodStr + rt +
		"}"; 
		}
		String fileName = "d:/src/proxy/TankTimeProxy.java";
		
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		
		//编译文件代码（compiler）
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  //获取当前java编译器，并赋给java编译器对象compiler
		//System.out.println(compiler); //need jdk1.6  这里需要jdk，非jre
		
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		Iterable units =  fileMgr.getJavaFileObjects(fileName);
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		t.call();
		fileMgr.close();
		
		//load into memory and 
		URL[] urls = new URL[]{new URL("file:/" +"d:/src/")};  //class 路径地址
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("proxy.TankTimeProxy");  //加载
		//System.out.println(c);
		
		//create an instance
		Constructor ct = c.getConstructor(InvocationHandler.class);
		Object o = ct.newInstance(h);
		//System.out.println(o.getClass().getName());
		return o;
	}
}
