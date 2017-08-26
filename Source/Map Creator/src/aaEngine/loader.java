package aaEngine;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class loader {
	
	public static BufferedImage getImage(String path){
		BufferedImage img=null;
		try{
			File f= new File(path);
			img=ImageIO.read(f);
		}catch(Exception ex){System.out.println("Exists?: "+path);}
		return img;
	}
	
}
