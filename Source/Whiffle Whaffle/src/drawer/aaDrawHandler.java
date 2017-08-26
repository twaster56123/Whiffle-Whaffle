package drawer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import aData.general;

public class aaDrawHandler {
	
	double xRatio=0;
	double yRatio=0;
	
	private menu menu = new menu();
	private game game = new game();
	public void refresh(Graphics g){
		
		//Regular stuff
		menu.refresh(g);
		game.refresh(g);
		
		//Debugger display
		if(general.showDebug){
			g.setColor(Color.red);
			g.setFont(new Font("arial", 0, 14));
			g.drawString("FPS: "+Math.round(general.fps), 0, 14);
			double rounded=Math.round(general.connectionStrength/general.optimize*100);
			g.drawString("Connection Strength: "+rounded+"%", 0, 28);
		}
		
	}
	
}
