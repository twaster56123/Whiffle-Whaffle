package aaMapEngine;

import entities.aYourPlayer;

public class Map {

	public static String[] mapNames={"Town", "Plains", "Crash The Game!(Next Map Does not exist yet)"};
	private static float x[] = 		 {1200,    800};
	private static float y[] = 		 {600,     400};
	public static int map=0;
	
	public static void init(){
		Town();
	}
	
	public static void Town(){
		aYourPlayer.x=1200;
		aYourPlayer.y=600;
		aYourPlayer.map="Town";
		aYourPlayer.changeMap();
	}
	
	public static void changeMap(){
		
		if(aYourPlayer.canChangeMapForwards){
			aYourPlayer.canChangeMapForwards=false;
			map++;
			aYourPlayer.x=x[map];
			aYourPlayer.y=y[map];
			aYourPlayer.map=mapNames[map];
			aYourPlayer.changeMap();
		}
		
		if(aYourPlayer.canChangeMapBackwards){
			aYourPlayer.canChangeMapBackwards=false;
			map--;
			aYourPlayer.x=x[map];
			aYourPlayer.y=y[map];
			aYourPlayer.map=mapNames[map];
			aYourPlayer.changeMap();
		}
		
	}
	
	
}
