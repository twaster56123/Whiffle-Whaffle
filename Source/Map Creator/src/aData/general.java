package aData;

import java.awt.image.BufferedImage;

public class general {

	//Game Build
	public static String build="Private";
	public static String name="Map Editor";
	public static boolean resize=false;
	
	//
	public static String mode="menu";
	
	public static BufferedImage test;
	
	public static int idRow=0;
	public static int selected=0;
	public static int placeWidth=1;
	public static int placeHeight=1;
	public static boolean saved=false;
	
	public static double screen_sizeX=0.0, screen_sizeY=0.0, csx=0.0, csy=0.0;

	
	public static double getScaleRatioX(){
		return(csx/screen_sizeX);
	}
	
	public static double getScaleRatioY(){
		return(csy/screen_sizeY);
	}
	
}
