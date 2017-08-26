package drawer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aData.general;

public class menu{
	
	public static BufferedImage background=null;
	
	public void refresh(Graphics g){
		
		if(general.mode=="menu"){
			g.drawImage(background, 0, 0, null);
			
		}
		
	}
	
}
