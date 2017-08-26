package entities;

import aaMapEngine.saveFile;

public class aYourPlayer {
	
	//Etc
	public static volatile int width=47;
	public static volatile int height=112;
	
	//Implemented
	public static volatile String username="failed";
	public static volatile int character=-1;
	public static volatile int id=-1;
	public static volatile int health=100;
	public static volatile int maxHealth=100;
	public static volatile int speed=150;
	public static volatile float anim=0.0f;
	public static volatile float x;
	public static volatile float y;
	
	public static volatile boolean canChangeMapForwards=false;
	public static volatile boolean canChangeMapBackwards=false;
	public static volatile String map;
	
	//Inventory
	//Attack
	//Gather
	//Plant
	
	
	//Set Map Load Server data 
	public static void changeMap(){
		saveFile._clear();
		saveFile._import(map);
	}
	
	public static void outterBounds(double delta){
		
		if(saveFile._static!=null){

			//top Boundary
			if(y<0){
				y+=delta*speed;
			}

			//bottom Boundary
			if((y+height)>saveFile._static[0].length*33){
				y-=delta*speed;
			}
			
			//right Boundary
			if((x+width)>saveFile._static[0].length*33){
				x-=delta*speed;
			}
			
			//left Boundary
			if(x<0){
				x+=delta*speed;
			}
		}
		
	}
	
	public static void nonTouchableStaticBounds(double delta){
		
		if(saveFile._static!=null){
			int objWidth=33;
			int objHeight=33;
			//Above head
			try{
				int xx=(int)Math.round(x/33);
				int yy=(int)Math.round((y)/33);
				if(saveFile._static[xx][yy]==0){
						y+=speed*delta;
				}
			}catch(Exception ex){}
			//Below Feet
			try{
				int xx=(int)Math.round(x/33);
				int yy=(int)Math.round((y+height)/33);
				if(saveFile._static[xx][yy]==0){
						y-=speed*delta;
				}
			}catch(Exception ex){}
			//Left Side
			try{
				for(int i=0; i<3; i++){
					int xx=(int)Math.round(x)/33;
					int yy=(int)Math.round((y+objHeight+i*33)/33);
					if(saveFile._static[xx][yy]==0){
						x+=speed*delta;
						break;
					}
				}
			}catch(Exception ex){}
			//Right Side
			try{
				for(int i=0; i<3; i++){
					int xx=(int)Math.round((x+width)/33);
					int yy=(int)Math.round((y+objHeight+i*33)/33);
					if(saveFile._static[xx][yy]==0){
						x-=speed*delta;
						break;
					}
				}
			}catch(Exception ex){}
		}
		
	}

	public static void objectBounds(double delta){
		
		double avoid = delta*speed;
		
		if(saveFile._objects!=null){
			//objects to run into
			for(int i=0; i<saveFile._objects.length/2; i++){
				if(!saveFile._objects_shown[i]){
					int objX      = saveFile._objects[i*2];
					int objY      = saveFile._objects[i*2+1];
					int objWidth  = (saveFile.objectImages[saveFile._objects_id[i]]).getWidth();
					int objHeight = (saveFile.objectImages[saveFile._objects_id[i]]).getHeight();
					
					//Sub Collision Hit Boxes
					float ratio = 0.01f;
					boolean simpleCollision = !( (x>objX+objWidth) || (x+width<objX) || (y>objY+objHeight) || (y+height<objY) );
					boolean leftSideCollision = !( (x>objX+objWidth*ratio) || (x+width<objX) || (y>objY+objHeight-avoid) || (y+height-avoid<objY) );
					boolean rightSideCollision = !( (x>objX+objWidth) || (x+width*ratio<objX) || (y>objY+objHeight-avoid) || (y+height-avoid<objY) );
					boolean topSideCollision = !( (x>objX+objWidth-avoid) || (x+width-avoid<objX) || (y>objY+objHeight*ratio) || (y+height<objY) );
					boolean bottomSideCollision = !( (x>objX+objWidth-avoid) || (x+width-avoid<objX) || (y>objY+objHeight) || (y+height*ratio<objY) );
					
					if(saveFile._objects_name[i]!=null){
						if( !saveFile._objects_name[i].equals("portal1") && !saveFile._objects_name[i].equals("portal2") ){
							
							//Pending
							if(leftSideCollision){
								x-=avoid;
							}
							//Pending
							if(rightSideCollision){
								x+=avoid;
							}
							//Pending
							if(topSideCollision){
								y-=avoid;
							}
							//Pending
							if(bottomSideCollision){
								y+=avoid;
							}
							
						}else{
							
							if(saveFile._objects_name[i].equals("portal1")){
								if(simpleCollision){
									canChangeMapForwards=true;
								}else{
									canChangeMapForwards=false;
								}
							}
							
							if(saveFile._objects_name[i].equals("portal2")){
								if(simpleCollision){
									canChangeMapBackwards=true;
								}else{
									canChangeMapBackwards=false;
								}
							}
							
						}
					}
					
					
				}
			}
		}
		
	}
	
}
