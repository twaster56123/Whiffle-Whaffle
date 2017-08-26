package aData;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class saveFile {

	//File Basics
	public static String path = "res/maps/";
	public static String ext  = ".PSBMap";
	public static String key  = "Eric Purvis";
	
	//Data Basics
	public static String name=""; // Files name
	public static int _static[][];
	public static int _objects[];
	public static String _objects_name[];
	public static int    _objects_id[];
	
	//Window
	private static int width=1200;
	private static int height=700;
	
	
//	static reference
	public static BufferedImage blocks[];
//Object reference
	public static BufferedImage objectImages[];
//Object Display
	public static BufferedImage objectDisplayImages[];
//Object Names
	public static String objectNames[];
	
	
	
	public static void _create(int width, int height, int objects){
		name=JOptionPane.showInputDialog("File Name?: ");
		 _static= new int[width][height];
		 _objects_name=new String[objects];
		 _objects_id  =new int[objects];
		 _objects= new int[objects*2];
	}
	
	public static void _export(){
		File file = new File(path+name+ext);
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			
			PrintWriter save = new PrintWriter(file);
			
			//Print Authority Key
			save.println(key);
			save.println(view.scrollX);
			save.println(view.scrollY);
			//print static data
			save.println("static start");
			save.println(_static.length);
			save.println(_static[0].length);
			for(int y=0; y<_static[0].length; y++){
				for(int x=0; x<_static.length; x++){
					if(_static[x][y]!=0){
						save.println(x);
						save.println(y);
						save.println(_static[x][y]);
					}
				}
			}
			save.println("static end");
			
			//print object data
			save.println("objects start");
			save.println(_objects_name.length);
			for(int i=0; i<_objects_name.length; i++){
				if(_objects_name[i]!=null){
					save.println(i);
					save.println(_objects_name[i]);
					save.println(_objects_id[i]);
					save.println(_objects[i*2]);
					save.println(_objects[i*2+1]);
				}
			}
			save.println("objects end");
			
			System.out.println("Save Successful!");
			
			//Close writter
			save.flush();
			save.close();
		}catch(IOException ex){
			System.out.println(ex.getLocalizedMessage());
		}
		
	}
	
	public static void _import(String loadFile){
		name=loadFile;
		File file = new File(path+loadFile+ext);
		try{
			Scanner load = new Scanner(file);
			String input="";
			
			//Work if key is found
			if(load.nextLine().trim().equals(key)){
				
				view.scrollX=load.nextDouble();
				view.scrollY=load.nextDouble();
				
				//Get static blocks data
				String test=load.nextLine();
				test=load.nextLine();
				if(test.equals("static start")){
					_static= new int[load.nextInt()][load.nextInt()];
					input=load.nextLine();
					input=load.nextLine();
					while(!(input.equals("static end"))){
						int x=Integer.parseInt(input);
						int y=load.nextInt();
						_static[x][y]=load.nextInt();
						input=load.nextLine().trim();
						input=load.nextLine().trim();
					}
				}
				
				//Get objects data
				if(load.nextLine().equals("objects start")){
					int length = load.nextInt();
					_objects_name = new String[length];
					_objects = new int[length*2];
					_objects_id = new int[length];
					input=load.nextLine().trim();
					input=load.nextLine().trim();
					while(!(input.equals("objects end"))){
						int i=Integer.parseInt(input);
						input=load.nextLine();
						_objects_name[i]=input;
						_objects_id[i]  =load.nextInt();
						_objects[i*2]=load.nextInt();
						_objects[i*2+1]=load.nextInt();
						input= load.nextLine().trim();
						input= load.nextLine().trim();
					}
				}
				
			}
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
		}
	}

	public static void _drawStatic(Graphics g){
		
		for(int y=(int)(view.scrollY/33); y<(int)(height/33+view.scrollY/33)+1; y++){
			for(int x=(int)(view.scrollX/33); x<(int)(width/33+view.scrollX/33)+1; x++){
				try{
					g.drawImage(blocks[_static[x][y]], (int)(x*33-view.scrollX), (int)(y*33-view.scrollY), null);
					if(view.grid){
						g.setColor(Color.black);
						g.drawRect((int)(x*33-view.scrollX), (int)(y*33-view.scrollY), 33, 33);
					}
				}catch(Exception ex){}
			}
		}
		
	}
	
	public static void _drawObjects(Graphics g){
		
		//Yikes ... Draws Objects (faster with smaller lists)
		for(int i=0; i<_objects_name.length; i++){
			if(_objects_name[i]!=null){
				
				int leeway=100;
				int screenX=(int)Math.round(view.scrollX);
				int screenY=(int)Math.round(view.scrollY);
				
				if( (_objects[i*2]-screenY)>=(0-leeway) || (_objects[i*2]-screenX)<=(width+leeway) || (_objects[i*2+1]-screenY)>=(0-leeway) || (_objects[i*2+1]-screenY)<=(height+leeway) ){
					try{
						g.drawImage(objectImages[_objects_id[i]], _objects[i*2]-screenX, _objects[i*2+1]-screenY, null);
					}catch(Exception ex){}
				}
				
			}
		}
		
	}
	
	
	
	
}
