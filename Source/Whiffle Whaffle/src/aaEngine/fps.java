package aaEngine;

public class fps {
	
	//Defaults
	private static double time1=0.0;
	private static double time2=0.0;
	public static double delta=0.0;
	public static double fps=0.0;
	private static double maxFps=60.0;
	
	public static void setMaxFps(double fpsRate){
		maxFps=fpsRate;
	}
	
	public static void setTime1(){
		time1=System.nanoTime();
	}
	
	public static void setTime2(){
		time2=time1;
	}
	
	public static double getDelta(){
		delta=(time1-time2)/1000000000.0;
		return delta;
	}
	
	public static double getFps(){
		fps=1/delta;
		return fps;
	}
	
	
	public static boolean isFpsInRange(){
		boolean is=false;
		if(fps<=maxFps){is=true;}
		return is;
	}
	
}
