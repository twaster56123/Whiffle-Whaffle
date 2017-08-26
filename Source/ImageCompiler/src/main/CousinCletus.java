package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CousinCletus {

	private int width=0;
	private int height=0;
	private int wTimes=0;
	private int hTimes=0;
	private BufferedImage img;
	
	private File list[];
	
	//Gathers image
	public void collect(String path){
		File folder = new File(path);
		list = folder.listFiles();
	}
	
	//Processes Images
	public void process(int width, int height){
		try{
			BufferedImage img = ImageIO.read(list[0]);
			wTimes=img.getWidth();
			hTimes=img.getHeight();
		}catch(Exception ex){System.out.println("Failed in Processing!");}
		this.width=width;
		this.height=height;
	}
	
	//Arranges images
	public void implant(){
		img = new BufferedImage(width*wTimes, height*hTimes, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		int imgCount=0;
		for(int h=0; h<height; h++){
			for(int w=0; w<width; w++){
				try{
					g.drawImage(ImageIO.read(list[imgCount]), w*wTimes, h*hTimes, null);
					imgCount++;
				}catch(Exception ex){System.out.println("Implant failed!");}
			}
		}
		g.dispose();
	}
	
	//Saves created file
	public void save(String path){
		
		try{
			File file = new File(path+"/"+"output.png");
			ImageIO.write(img, "png", file);
			System.out.println("Complete!");
		}catch(IOException ex){System.out.println("Image failed to save!");}
		
	}
	
}
