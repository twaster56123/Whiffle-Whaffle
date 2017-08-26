package main;
import javax.swing.JOptionPane;

import zClient.GameClient;
import zClient.LogInClient;
import zServer.GameServer;
import zServer.LoginServer;
import aData.GameClientData;
import aData.GameServerData;
import aData.LoginServerData;
import aData.general;
import aaEngine.fps;
import aaEngine.loader;
import aaEngine.window;
import entities.Player;
import entities.aYourPlayer;

public class startFile {

	public static window win = new window();
	public static void main(String args[]){
		//Create Window
		win.make(general.name+" (Build: "+general.build+")", 1200, 700, general.resize);
		//Loader
		load.test();
		
		boolean serverOn=false;
		//Host Server?
		try{
			if(JOptionPane.showInputDialog("Run Server? y/n").equals("y")){
				serverOn=true;
				GameServerData.getRouterIP();
				LoginServer.bind();
				//Log Server
				for(int i=0; i<general.maxUsers; i++){
					final int x = i;
					new Thread(new Runnable(){
						public void run(){
							final LoginServer temp = new LoginServer();
							temp.id=x;
							while(true){
								try{
									Thread.sleep(1);
								}catch(Exception ex){}
								temp.run();
							}
						}
					}).start();
				}
			}
		}catch(Exception ex){System.err.println("start: "+ex.getLocalizedMessage());}
		
		//Run Client!
		try{
			
			//Log Client
			new Thread(new Runnable(){
				public void run(){
					aYourPlayer.character=Integer.parseInt(JOptionPane.showInputDialog("Character? 0-"+(aData.entityImages.playerWalkAnim[0].length-1)));
					GameServerData.ip=JOptionPane.showInputDialog("Server:?");
					LoginServerData.logClient=new LogInClient(GameServerData.ip);
				}
			}).start();
			
		}catch(Exception ex){
			System.err.println("start: "+ex.getLocalizedMessage());
		}
		
		GameServerData.gameServer = new GameServer();
		final boolean isServerOn = serverOn;
		if(isServerOn){
			GameServerData.gameServer.run();
		}
		GameClientData.gameClient = new GameClient();
		GameClientData.gameClient.run();
		
		//Main game loop
		new Thread(new Runnable(){public void run(){
			while(true){
				gameLoop.run();
			}
		}}).start();
		
	}
	
	private static mouseClickHandler mch=new mouseClickHandler();
	private static mouseHoverHandler mhh=new mouseHoverHandler();
	private static keysHandler kh=new keysHandler();
	//Games main loop
	private static class gameLoop{
		static double time1=0.0, time2=0.0;
		static double delta=0;
		static double fps=0;
		private static void run(){
			time1=System.nanoTime();
			delta=(time1-time2)/1000000000.0;
			fps=1/delta;
			if(fps<=60){
				general.fps=fps;
				
				//Everything else
				mch.input();
				mhh.hover();
				kh.input(delta);

				//graphics
				screen.refresh();
				
				time2=time1;
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
		private static loadHandler.game game = new loadHandler.game();
		private static loadHandler.player player = new loadHandler.player();
		private static void test(){
			menu.load();
			game.load();
			player.load();
		}
		
	}
}
