package main;

import javax.swing.JOptionPane;

import aData.general;
import aData.mouse;
import aData.saveFile;
import aData.view;

public class mouseClickHandler extends mouse{

	double xRatio=0;
	double yRatio=0;
	
	public void input(){
		this.xRatio=general.getScaleRatioX();
		this.yRatio=general.getScaleRatioY();
		leftClicked();
	}
	
	public void leftClicked(){
		if(leftClicked){
			leftClicked=false;
			leftEdit();
			leftMenu();
		}
	}
	
	public void leftMenu(){
		if(general.mode=="menu"){
			
			//Create
			if(mx>=631 && mx<=(631+69) && my>=457 && my<=(457+23)){
				saveFile._create(1000, 1000, 2000);
				general.mode="edit";
			}
			
			//Edit
			if(mx>=644 && mx<=(644+42) && my>=498 && my<=(498+22)){
				saveFile._import(JOptionPane.showInputDialog("File Name?: "));
				general.mode="edit";
			}
			
		}
	}
	
	public void leftEdit(){
		if(general.mode=="edit"){
			
			if(view.choose){
				
				//Next Button
				if(mx>=500 && mx<=500+100 && my>=510 && my<=510+25){
					general.idRow++;
				}
				
				//Back Button
				if(mx>=200 && mx<=200+100 && my>=510 && my<=510+25){
					if(general.idRow>0){
						general.idRow--;
					}
				}
				
				if(!view.objectMode){
					
					//Selection of static block
					int across=13, startX=150, width=33;
					int down  =10, startY=77, height=33;
					for(int y=0; y<down; y++){
		 				for(int x=0; x<across; x++){
		 					if(mx>=((x+across*y)+1)*width+startX-(across*width*y) && mx<=((x+across*y)+1)*width+startX-(across*width*y)+width && my>=(startY+y*height) && my<=(startY+y*height)+height){
		 						general.selected=(x+across*y+(down*across*general.idRow));
		 					}
		 				}
		 			}
					
					
				}else{
					//Selection of object 
					int across=10, startX=150, width=50;
					int down  =9,  startY=77, height=50;
					for(int y=0; y<down; y++){
		 				for(int x=0; x<across; x++){
		 					if(mx>=((x+across*y)+1)*width+startX-(across*width*y) && mx<=((x+across*y)+1)*width+startX-(across*width*y)+width && my>=(startY+y*height) && my<=(startY+y*height)+height){
		 						general.selected=(x+across*y+(down*across*general.idRow));
		 					}
		 				}
		 			}
				}
				
			}else{
				if(!view.objectMode){
					//Block placement
					try{
						int x=(int)Math.round(mx+view.scrollX)/33;
						int y=(int)Math.round(my+view.scrollY)/33;
						for(int yy=0; yy<general.placeHeight; yy++){
							for(int xx=0; xx<general.placeWidth; xx++){
								saveFile._static[x+xx][y+yy]=general.selected;
							}
						}
						general.saved=false;
					}catch(Exception ex){}
				}else{
					
					//Object Placement
					int x=(int)Math.round(mx+view.scrollX);
					int y=(int)Math.round(my+view.scrollY);
					//Find Open Space
					for(int i=0; i<saveFile._objects_name.length; i++){
						if(saveFile._objects_name[i]==null){
							saveFile._objects[i*2]=x;
							saveFile._objects[i*2+1]=y;
							saveFile._objects_name[i]=saveFile.objectNames[general.selected];
							saveFile._objects_id[i]=general.selected;
							break;
						}
					}
					
					general.saved=false;
				}
				
			}
		}
		
	}
	
}
