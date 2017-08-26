package loadHandler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import aData.saveFile;
import aaEngine.loader;

public class edit extends loader{

	public void load(){
		try{
		//Static image loader
			File folder = new File(saveFile.path+"/staticImages");
			File files[] = folder.listFiles();
			int count=0;
			for(int i=0; i<files.length; i++){
				if(files[i].isFile() && files[i].getName().endsWith(".png")){
					count++;
				}
			}
			saveFile.blocks=new BufferedImage[count];
			count=0;
			for(int i=0; i<files.length; i++){
				if(files[i].isFile() && files[i].getName().endsWith(".png")){
					saveFile.blocks[count]=getImage(saveFile.path+"/staticImages/"+files[i].getName());
					count++;
				}
			}
		//Gui Overlay
			drawer.edit.overlay = getImage("res/img/edit/overlay.png");
		//Object Loader
			folder = new File(saveFile.path+"/objectImages");
			files = folder.listFiles();
			count=0;
			for(int i=0; i<files.length; i++){
				if(files[i].isFile() && files[i].getName().endsWith(".png")){
					count++;
				}
			}
			saveFile.objectImages=new BufferedImage[count];
			saveFile.objectDisplayImages=new BufferedImage[count];
			saveFile.objectNames=new String[count];
			count=0;
			for(int i=0; i<files.length; i++){
				if(files[i].isFile() && files[i].getName().endsWith(".png")){
					saveFile.objectImages[count]=getImage(saveFile.path+"/objectImages/"+files[i].getName());
					
					saveFile.objectNames[count]=files[i].getName().replace(".png", "");
					
					BufferedImage temp = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
					Graphics g = temp.createGraphics();
					g.drawImage(saveFile.objectImages[count], 0,0, 50,50, null);
					g.dispose();			
					saveFile.objectDisplayImages[count]=temp;
					count++;
					
				}
			}
			
		}catch(Exception ex){}
		
	}
	
}
