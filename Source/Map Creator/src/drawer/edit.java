package drawer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import aData.general;
import aData.mouse;
import aData.saveFile;
import aData.view;

public class edit {

	public static BufferedImage overlay;
	
	public void refresh(Graphics g){
		
		if(general.mode=="edit"){
			g.setColor(Color.gray);
			g.fillRect(0,0,1200,700);
			
			
			//Draw static
			saveFile._drawStatic(g);
			
			//Draw Objects
			saveFile._drawObjects(g);
			
			//Display object hover
			if(view.objectMode){
				try{
					g.drawImage(saveFile.objectImages[general.selected], mouse.mx, mouse.my, null);
				}catch(Exception ex){}
			}
			
			//
			if(view.choose){
				g.drawImage(overlay, 0, 0, null);
				//Displays row id
				g.setFont(new Font("arial", 0, 16));
				g.setColor(Color.black);
				g.drawString(""+general.idRow, 200+160, 510);
				if(!view.objectMode){
					
					//Displays Static Mode
					g.setFont(new Font("arial", 0, 16));
					g.setColor(Color.black);
					g.drawString("Static Mode", 200+160, 510+18);
					
					//Formatting display 10 down 13 across
					int across=13, startX=150, width=33;
					int down  =10, startY=77, height=33;
					for(int y=0; y<down; y++){
			 			for(int x=0; x<across; x++){
			 				try{
			 					g.drawImage(saveFile.blocks[(x+across*y+(across*down*general.idRow))], ((x+across*y)+1)*width+startX-(across*width*y), startY+y*height, null);
			 				}catch(Exception ex){}
			 			}
					}
					
				}else{
					
					//Displays Object Mode
					g.setFont(new Font("arial", 0, 16));
					g.setColor(Color.black);
					g.drawString("Object Mode", 200+160, 510+18);
					
					int across=10, startX=150, width=50;
					int down  =9,  startY=77, height=50;
					for(int y=0; y<down; y++){
			 			for(int x=0; x<across; x++){
			 				try{
			 					g.drawImage( saveFile.objectDisplayImages[(x+across*y+(across*down*general.idRow))], ((x+across*y)+1)*width+startX-(across*width*y), startY+y*height, null);
			 				}catch(Exception ex){
			 					System.out.println();
			 				}
			 			}
					}
					
				}
				
				//Next Button
				g.setFont(new Font("arial", 0, 16));
				g.setColor(Color.gray);
				g.fillRect(500, 510, 100, 25);
				g.setColor(Color.black);
				g.drawString("Next", 500+30, 510+18);
				
				//Back Button
				g.setColor(Color.gray);
				g.fillRect(200, 510, 100, 25);
				g.setColor(Color.black);
				g.drawString("Back", 200+30, 510+18);
	 			
			}
			
			//displays saved message
			if(general.saved){
				g.setColor(Color.gray);
				g.fillRect(0, 0, 75, 30);
				g.setFont(new Font("arial", 0, 24));
				g.setColor(Color.black);
				g.drawString("Saved!", 0, 24);
				
			}
			
			g.setFont(new Font("arial", 0, 16));
			g.setColor(Color.black);
			g.drawString("X: "+(int)Math.round(view.scrollX), 0, 540);
			g.drawString("Y: "+(int)Math.round(view.scrollY), 0, 560);
			
			g.drawString("Px: "+general.placeWidth, 0, 500);
			g.drawString("Py: "+general.placeHeight, 0, 520);
			
		}
		
	}
	
}
