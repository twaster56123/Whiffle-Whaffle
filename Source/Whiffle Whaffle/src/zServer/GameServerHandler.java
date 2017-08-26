package zServer;

import java.net.DatagramPacket;
import java.net.InetAddress;

import aData.GameServerData;
import aData.general;
import entities.Player;

public class GameServerHandler extends GameServerData{

	private volatile int optimize=general.optimize;
	
	public void process(){
		
		//Expel Output Thread
		new Thread(new Runnable(){public void run(){
			double time1=0;
			double time2=0;
			double delta=0;
			double up=0;
			while(true){
				try{
					
					time1=System.nanoTime();
					delta=(time1-time2)/1000000000.0;
					up=1/delta;
					
					if(up<=optimize){
						update();
						time2=time1;
					}				
					
				}catch(Exception ex){
//					System.err.println("GSH Update "+ex.getLocalizedMessage());
				}
			}
		}}).start();
		
		//Receive Input Thread
		new Thread(new Runnable(){public void run(){
			while(true){
				try{
					
					read();
					
				}catch(Exception ex){
//					System.err.println("GSH Update "+ex.getLocalizedMessage());
				}
			}
		}}).start();
	
	}
	
	public void read(){	
		DatagramPacket inc = GameServer.getInput();
		byte[] data = inc.getData();
		String in = new String(data, 0, inc.getLength());
		String list[] = in.split(":");
		
		//latency?
		if(list[0].equals("latencyCheck")){
			int id = Integer.parseInt(list[1]);
			if(out[id]!=null){
				gameServer.send("latencyCheck", out[id], port[id]);
			}
		}
		
		//Its possible right??!?!
		if(list[0].equals("Username")){
			int id = Integer.parseInt(list[1]);
			newUser(id, inc.getAddress(), inc.getPort());
			players[id].username=list[2];
		}
		
		if(list[0].equals("Map")){
			int id = Integer.parseInt(list[1]);
			players[id].map=list[2];
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
	
	public void update(){
		
		//Send Player Info
		for(int xx=0; xx<GameServer.out.length; xx++){
			if(out[xx]!=null){
				final int x=xx;
				new Thread(new Runnable(){public void run(){
					for(int i=0; i<GameServerData.players.length; i++){
						if(players[i]!=null){
							
							if(players[i].username.equals("//toBeRemoved//")){
								gameServer.send("Username:"+players[i].id+":"+players[i].username, out[x], port[x]);
							//	players[i]=null;
							//	out[i]=null;
							//	port[i]=0;
							}else{
							
								if(!players[i].username.equals("failed")){
									
									//If username changes update
									if(!players[i].username.equals(lastUsername[i][x])){
										gameServer.send("Username:"+players[i].id+":"+players[i].username, out[x], port[x]);
										lastUsername[i][x]=players[i].username;
									}
									
									if(!players[i].map.equals(lastMap[i][x])){
										gameServer.send("Map:"+players[i].id+":"+players[i].map, out[x], port[x]);
										lastMap[i][x]=players[i].map;
									}
									
									if(players[i].character!=(lastCharacter[i][x])){
										gameServer.send("Character:"+players[i].id+":"+players[i].character, out[x], port[x]);
										lastCharacter[i][x]=players[i].character;
									}
									
									//If Health changes update
									if(players[i].health!=lastHealth[i][x]){
										gameServer.send("Health:"+players[i].id+":"+players[i].health, out[x], port[x]);
										lastHealth[i][x]=players[i].health;
									}
										
									//If MaxHealth changes update
									if(players[i].maxHealth!=lastMaxHealth[i][x]){
										gameServer.send("MaxHealth:"+players[i].id+":"+players[i].maxHealth, out[x], port[x]);
										lastMaxHealth[i][x]=players[i].maxHealth;
									}
										
									//If Pos changes update
									if(players[i].x!=lastX[i][x] || players[i].y!=lastY[i][x]){
										gameServer.send("Position:"+players[i].id+":"+players[i].x+":"+players[i].y, out[x], port[x]);
										lastX[i][x]=players[i].x;
										lastY[i][x]=players[i].y;
									}
										
									//If Anim changes update
									if(players[i].anim!=lastAnim[i][x]){
										gameServer.send("Anim:"+players[i].id+":"+players[i].anim, out[x], port[x]);
										lastAnim[i][x]=players[i].anim;
									}
								}
						}
					}
				}
			  }}).start();
			}
		}
	}
	
	public void newUser(int id, InetAddress address, int port){
		
		if(players[id]==null){
			players[id] = new Player();
			players[id].id = id;
			out[id]=address;
			GameServerData.port[id]=port;
		}
		
	}
}
