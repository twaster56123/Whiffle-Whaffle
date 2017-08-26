package aData;

public class general {

	//Game Build
	public static volatile String build="Alpha 170608";
	public static volatile String name="Whiffle Whaffle";
	public static volatile boolean resize=false;
	
	public static volatile int maxUsers=5;
	public static volatile int optimize=60;
	
	//
	public static volatile String mode="menu";
	
	//Debug stats
	public static volatile boolean showDebug=false;
	public static double fps=0;
	public static volatile double connectionStrength=0;
	
	//Calibration stats
	public static double screen_sizeX=0.0, screen_sizeY=0.0, csx=0.0, csy=0.0;

	
	public static double getScaleRatioX(){
		return(csx/screen_sizeX);
	}
	
	public static double getScaleRatioY(){
		return(csy/screen_sizeY);
	}
	
}
