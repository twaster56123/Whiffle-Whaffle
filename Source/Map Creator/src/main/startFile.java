package main;
import aData.general;
import aaEngine.fps;
import aaEngine.loader;
import aaEngine.window;

public class startFile {

	public static window win = new window();
	public static void main(String args[]){
		//Create Window
		win.make(general.name+" (Build: "+general.build+")", 1200, 700, general.resize);
		//Loader
		load.test();
		//Main game loop
		while(true){
			gameLoop.run();
		}
		
	}
	
	private static mouseClickHandler mch=new mouseClickHandler();
	private static mouseHoverHandler mhh=new mouseHoverHandler();
	private static keysHandler kh=new keysHandler();
	//Games main loop
	private static class gameLoop extends fps{
		private static void run(){
			setTime1();
			double delta=getDelta();
			double fps=getFps();
			if(isFpsInRange()){
				//Everything else
				mch.input();
				mhh.hover();
				kh.input(delta);
				//graphics
				screen.refresh();
				setTime2();
			}
			
		}
	}
	
	//Handles drawing the game window
	private static class screen{	
		private static void refresh(){
			win.sc.update();
		}
	}
	
	//Handles Loading stuff
	private static class load extends loader{
		
		private static loadHandler.menu menu = new loadHandler.menu();
		private static loadHandler.edit edit = new loadHandler.edit();
		private static void test(){
			menu.load();
			edit.load();
		}
		
	}
}
