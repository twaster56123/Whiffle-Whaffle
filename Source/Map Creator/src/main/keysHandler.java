package main;

import aData.general;
import aData.keys;
import aData.mouse;
import aData.saveFile;
import aData.view;

public class keysHandler extends keys{

	double chooseToggle=0, characterToggle=0, gridToggle=0;
	double cooldown=0.25;
	
	double placeX=0, placeY=0; 
	double cooldownPlace=0.1;
	
	double save=0;
	double cooldownSave=1;
	
	double delete=0;
	double cooldownDelete=0.25;
	
	public void input(double delta){
		
		if(general.mode=="edit"){
			
			//Cool Down Timers
			if(chooseToggle>0){
				chooseToggle-=delta;
			}
			
			if(characterToggle>0){
				characterToggle-=delta;
			}
			
			if(gridToggle>0){
				gridToggle-=delta;
			}
			
			
			if(placeX>0){
				placeX-=delta;
			}
			
			if(placeY>0){
				placeY-=delta;
			}
			
			
			if(save>=0){
				save-=delta;
			}
			
			if(delete>=0){
				delete-=delta;
			}
			
			//Toggle selections
			if(isKeyDown(f) && chooseToggle<=0){
				view.choose=!view.choose;
				chooseToggle=cooldown;
			}
			
			//Toggle character mode
			if(isKeyDown(c) && characterToggle<=0){
				view.objectMode=!view.objectMode;
				characterToggle=cooldown;
			}
			
			//Grid Toggle
			if(isKeyDown(g) && gridToggle<=0){
				view.grid=!view.grid;
				gridToggle=cooldown;
			}
			
			
			
			//increase placement width
			if(isKeyDown(j) && placeX<=0){
				general.placeWidth++;
				placeX=cooldownPlace;
			}
			//decrease placement width
			if(isKeyDown(m) && placeX<=0){
				if(general.placeWidth>1){
					general.placeWidth--;
					placeX=cooldownPlace;
				}
			}
			//increase placement height
			if(isKeyDown(h) && placeY<=0){
				general.placeHeight++;
				placeY=cooldownPlace;
			}
			//decrease placement height
			if(isKeyDown(n) && placeY<=0){
				if(general.placeHeight>1){
					general.placeHeight--;
					placeY=cooldownPlace;
				}
			}
			
			
			//Save
			if(isKeyDown(p) && save<=0){
				saveFile._export();
				general.saved=true;
				save=cooldownSave;
			}
			
			
			//Escape to menu
			if(isKeyDown(escape)){
				view.scrollX=0;
				view.scrollY=0;
				view.choose=false;
				view.grid=false;
				view.objectMode=false;
				general.idRow=0;
				general.saved=false;
				general.placeWidth=1;
				general.placeHeight=1;
				general.mode="menu";
				return;
			}
			
			//Delete Object
			if(isKeyDown(eight) && delete<=0 && view.objectMode){
				for(int i=0; i<saveFile._objects_name.length; i++){
					if(saveFile._objects_name[i]!=null){
						
						int screenX=(int)Math.round(view.scrollX);
						int screenY=(int)Math.round(view.scrollY);
						int mx=mouse.mx;
						int my=mouse.my;
						int objX=saveFile._objects[i*2];
						int objY=saveFile._objects[i*2+1];
						int width=saveFile.objectImages[saveFile._objects_id[i]].getWidth();
						int height=saveFile.objectImages[saveFile._objects_id[i]].getHeight();
						
						if(mx>=(objX-screenX) && mx<=(objX+width-screenX) && my>=(objY-screenY) && my<=(objY+height-screenY) ){
							try{
								saveFile._objects_name[i]=null;
								break;
							}catch(Exception ex){}
						}
					}
				}
				delete=cooldownDelete;
			}
			
			//Scroll up
			if(isKeyDown(w)){
				view.scrollY-=view.scrollSpeed*delta;
			}
			
			//Scroll Down
			if(isKeyDown(s)){
				view.scrollY+=view.scrollSpeed*delta;
			}
			
			//Scroll Left
			if(isKeyDown(a)){
				view.scrollX-=view.scrollSpeed*delta;
			}
			
			//Scroll Right
			if(isKeyDown(d)){
				view.scrollX+=view.scrollSpeed*delta;
			}
			
		}
		
		
	}
	
}
