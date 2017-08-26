package entities;

import java.awt.Graphics;

import aData.entityImages;
import aaMapEngine.view;

public class Player {

	public String username="failed";
	public int character;
	public int health;
	public int maxHealth;
	public int speed;
	public double anim;
	
	public String map="pending";
	
	public float x;
	public float y;
	
	public int id;
	
	public void draw(Graphics g){
		try{
			if(map.equals(aYourPlayer.map)){
				g.drawImage(entityImages.playerWalkAnim[(int)Math.round(anim)][character], (int)Math.round(x-view.scrollX), (int)Math.round(y-view.scrollY), null);
		
				g.drawString( ("("+id+") "+username+" ("+health+"/"+maxHealth+")"), (int)Math.round(x-view.scrollX-50), (int)Math.round(y-view.scrollY));
			}
		}catch(Exception ex){}
	}
	
}
