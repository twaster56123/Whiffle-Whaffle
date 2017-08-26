package loadHandler;

import java.awt.image.BufferedImage;
import java.io.File;

import aData.entityImages;
import aaEngine.loader;

public class player extends loader{

	public void load(){
		
		String folder[] = new File("res/img/player").list();
		
		for(int i=0; i<folder.length; i++){
			
			BufferedImage img = getImage("res/img/player/"+folder[i]+"/walkAnimations.png");
			
			int w = img.getWidth()/3;
			int h = img.getHeight()/4;
				
			for(int y=0; y<4; y++){
				for(int x=0; x<3; x++){
					entityImages.playerWalkAnim[(x+y*3)][i] = img.getSubimage(x*w, y*h, w, h);
				}
			}
			
		}
		
	}
	
}
