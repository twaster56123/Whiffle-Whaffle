package zClient;

import entities.Player;
import entities.aYourPlayer;
import aData.GameClientData;
import aData.GameServerData;
import aData.general;

public class GameClientHandler extends GameClientData{
	
	private volatile int optimize=general.optimize;
	private volatile double pingTime1=0, pingTime2=0, pingDelta=0, pingFps=0;
	
	public void process(){
		
		new Thread(new Runnable(){public void run(){
			double time1=0;
			double time2=0;
			double delta=0;
			double up=0;
			while(true){
				time1=System.nanoTime();
				delta=(time1-time2)/1000000000.0;
				up=1/delta;
				try{
					if(general.mode=="game" && aYourPlayer.id!=(-1)){
						if(up<=optimize){
							update(delta);
							time2=time1;
						}
					}else{
						Thread.sleep(1);
					}
					
				}catch(Exception ex){
//					System.err.println("GCH UPDATE: "+ex.getLocalizedMessage());
				}
			}
		}}).start();
		
		new Thread(new Runnable(){public void run(){
			while(true){
				try{

					if(general.mode=="game"){
						read();
					}else{
						Thread.sleep(1);
					}
					
				}catch(Exception ex){
//					System.err.println("GCH READ: "+ex.getLocalizedMessage());
				}
			}
		}}).start();
		
	}
	
	public void read(){
		
		String in = gameClient.getInput();
		final String[] list = in.split(":");
		
		//Latency test
		if(list[0].equals("latencyCheck")){
			pingTime2=pingTime1;
		}
		
		if(list[0].equals("Map")){
			int id = Integer.parseInt(list[1]);
			players[id].map=list[2];
		}
		
		//Its possible right??!?!
		if(list[0].equals("Username")){
			int id = Integer.parseInt(list[1]);
			if(players[id]==null){
				players[id] = new Player();
			}
			players[id].username=list[2];
			if(players[id].username.equals("//toBeRemoved//")){
				players[id]=null;
			}
		}
			
		if(list[0].equals("Character")){
			int id = Integer.parseInt(list[1]);
			players[id].character=Integer.parseInt(list[2]);
		}
		
		if(list[0].equals("Health")){
			int id = Integer.parseInt(list[1]);
			players[id].health=Integer.parseInt(list[2]);
		}
				
		if(list[0].equals("MaxHealth")){
			int id = Integer.parseInt(list[1]);
			players[id].maxHealth=Integer.parseInt(list[2]);
		}
				
		if(list[0].equals("Position")){
			int id = Integer.parseInt(list[1]);
			players[id].x=Float.parseFloat(list[2]);
			players[id].y=Float.parseFloat(list[3]);
		}
							
		if(list[0].equals("Anim")){
			int id = Integer.parseInt(list[1]);
			players[id].anim=Float.parseFloat(list[2]);
		}
		
	}
	
	private String lastUsername;
	private String lastMap;
	private int lastCharacter;
	private int lastHealth;
	private int lastMaxHealth;
	private double lastX;
	private double lastY;
	private double lastAnim;
	
	public void update(double delta){
		
		//Latency check
		pingTime1=System.nanoTime();
		gameClient.send("latencyCheck:"+aYourPlayer.id);
		pingDelta=(pingTime1-pingTime2)/1000000000.0;
		pingFps=1/pingDelta;
		general.connectionStrength=pingFps;
		
		//Username change (unlikely but go with the flow)
		if(!aYourPlayer.username.equals(lastUsername)){
			gameClient.send("Username:"+aYourPlayer.id+":"+aYourPlayer.username);
			lastUsername=aYourPlayer.username;
		}
		
		//Character Change
		if(aYourPlayer.character!=lastCharacter){
			gameClient.send("Character:"+aYourPlayer.id+":"+aYourPlayer.character);
			lastCharacter=aYourPlayer.character;
		}
		
		//Map change
		if(!aYourPlayer.map.equals(lastMap)){
			gameClient.send("Map:"+aYourPlayer.id+":"+aYourPlayer.map);
			lastMap=aYourPlayer.map;
		}
		
		//Health Change
		if(aYourPlayer.health!=lastHealth){
			gameClient.send("Health:"+aYourPlayer.id+":"+aYourPlayer.health);
			lastHealth=aYourPlayer.health;
		}
			
		//Max Health Change
		if(aYourPlayer.maxHealth!=lastMaxHealth){
			gameClient.send("MaxHealth:"+aYourPlayer.id+":"+aYourPlayer.maxHealth);
			lastMaxHealth=aYourPlayer.maxHealth;
		}
			
		//Position Change
		if(aYourPlayer.x!=lastX || aYourPlayer.y!=lastY){
			gameClient.send("Position:"+aYourPlayer.id+":"+aYourPlayer.x+":"+aYourPlayer.y);
			lastX=aYourPlayer.x;
			lastY=aYourPlayer.y;
		}
		
		//Anim Change
		if(aYourPlayer.anim!=lastAnim){
			gameClient.send("Anim:"+aYourPlayer.id+":"+aYourPlayer.anim);
			lastAnim=aYourPlayer.anim;
		}
		
	}
	
}
