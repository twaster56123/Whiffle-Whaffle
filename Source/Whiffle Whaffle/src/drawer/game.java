package drawer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aData.GameClientData;
import aData.general;
import aaMapEngine.Map;
import aaMapEngine.saveFile;
import aaMapEngine.view;
import entities.aYourPlayer;

public class game {

	public static BufferedImage overlay;
	
	public void refresh(Graphics g){
		
		if(general.mode=="game"){
			
			//Default Background
			g.setColor(Color.black);
			g.fillRect(0,0,1200,700);
			
			//Draw static
			saveFile._drawStatic(g);
			
			//Draw Objects
			saveFile._drawObjects(g);
			
			//Player
			for(int i=0; i<GameClientData.players.length; i++){
				if(GameClientData.players[i]!=null){
					if(i!=aYourPlayer.id){
						GameClientData.players[i].id=i;
						GameClientData.players[i].draw(g);
					}
				}
			}
			//Overlay your player on top for your screen
			if(GameClientData.players[aYourPlayer.id]!=null){
				try{
					view.scrollX=GameClientData.players[aYourPlayer.id].x-1200/2+47/2;
					view.scrollY=GameClientData.players[aYourPlayer.id].y-700/2+37/2;
					GameClientData.players[aYourPlayer.id].id=aYourPlayer.id;
					GameClientData.players[aYourPlayer.id].draw(g);
				}catch(Exception ex){}
			}
			
			
			//Move to next map or go back a map
			try{
				
				String test="";
				if(aYourPlayer.canChangeMapForwards){
					test="Press 'e' for "+Map.mapNames[Map.map+1];
				}
				if(aYourPlayer.canChangeMapBackwards){
					test="Press 'e' for "+Map.mapNames[Map.map-1];
				}
				g.setFont(new Font("arial", 0, 20));
				g.drawString(test, 500, 20);
				
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
			}
			
		}
		
		
	}
	
}
