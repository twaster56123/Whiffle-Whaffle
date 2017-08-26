package aaMapEngine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
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
	public static int _objects[]; // position of objects
	public static String _objects_name[];
	public static int    _objects_id[];
	public static boolean _objects_shown[];
	
	public static BufferedImage blocks[];
	public static BufferedImage objectImages[];
	public static BufferedImage objectDisplayImages[];
	public static String objectNames[];

	private static int width=1233;
	private static int height=733;
	
	public static void _clear(){
		name="";
		_static=null;
		_objects=null;
		_objects_name=null;
		_objects_id=null;
		_objects_shown=null;
	}
	
	public static void _import(String loadFile){
		try{
			name=loadFile;
			File file = new File(path+loadFile+ext);
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
					_objects_shown = new boolean[length];
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
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
	}

	public static void _drawStatic(Graphics g){
		
		for(int y=(int)(view.scrollY/33); y<(int)(height/33+view.scrollY/33)+1; y++){
			for(int x=(int)(view.scrollX/33); x<(int)(width/33+view.scrollX/33)+1; x++){
				try{
					g.drawImage(blocks[_static[x][y]], (int)(x*33-view.scrollX), (int)(y*33-view.scrollY), null);
				}catch(Exception ex){}
			}
		}
		
	}
	
	public static void _drawObjects(Graphics g){
		
		//Yikes ... Draws Objects (faster with smaller lists)
		for(int i=0; i<_objects_name.length; i++){
			if(_objects_name[i]!=null && !_objects_shown[i]){
				
				int leeway=50;
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
