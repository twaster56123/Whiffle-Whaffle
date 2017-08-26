package main;

import entities.aYourPlayer;
import aData.GameClientData;
import aData.general;
import aData.keys;
import aaMapEngine.Map;

public class keysHandler extends keys{
	
	//anim thing
	int last=0;
	
	//key cooldowns
	double debugCoolDown=0;
	final double debugWaitTime=0.5;//in seconds
	double mapChangeCoolDown=0;
	final double mapChangeWaitTime=1;//in seconds
	
	public void input(double delta){
		
			if(general.mode=="game"){

				
				//Portal map changer
				if(isKeyDown(e) && mapChangeCoolDown<=0 && (aYourPlayer.canChangeMapForwards || aYourPlayer.canChangeMapBackwards) ){
					mapChangeCoolDown=mapChangeWaitTime;
					Map.changeMap();
				}else{
					mapChangeCoolDown-=delta;
				}
				
				
				final double oneSpeed=aYourPlayer.speed*delta;
				
				int up=0;
				int right=0;
				
				//Scroll up
				if(isKeyDown(w)){
					up-=1;
				}
				
				//Scroll Down
				if(isKeyDown(s)){
					up+=1;
				}

				//Scroll Left
				if(isKeyDown(a)){
					right-=1;
				}
				
				//Scroll Right
				if(isKeyDown(d)){
					right+=1;
				}
				
				aYourPlayer.outterBounds(delta);
				aYourPlayer.nonTouchableStaticBounds(delta);
				aYourPlayer.objectBounds(delta);
				
				aYourPlayer.x+=right*oneSpeed;
				aYourPlayer.y+=up*oneSpeed;
				
				animate(up, right, delta);
				
			}
		
			if(isKeyDown(f12) && debugCoolDown<=0){
				general.showDebug=!general.showDebug;
				debugCoolDown=debugWaitTime;
			}else{
				debugCoolDown-=delta;
			}
			
	}
	
	public void animate(int up, int right, double delta){
		
		double animSpeed = aYourPlayer.speed/50*delta;
		
		if(up!=0){
			
			if(up==-1){
				if(aYourPlayer.anim>=3 && aYourPlayer.anim<5){
					aYourPlayer.anim+=animSpeed;
				}else{
					aYourPlayer.anim=4;
				}
				last=4;
			}
			if(up==1){
				if(aYourPlayer.anim>=9 && aYourPlayer.anim<11){
					aYourPlayer.anim+=animSpeed;
				}else{
					aYourPlayer.anim=9;
				}
				last=9;
			}
			
		}else{
			if(right!=0){
				
				if(right==1){
					if(aYourPlayer.anim>=0 && aYourPlayer.anim<2){
						aYourPlayer.anim+=animSpeed;
					}else{
						aYourPlayer.anim=0;
					}
					last=0;
				}
				if(right==-1){
					if(aYourPlayer.anim>=6 && aYourPlayer.anim<8){
						aYourPlayer.anim+=animSpeed;
					}else{
						aYourPlayer.anim=6;
					}
					last=6;
				}
				
			}else{
				aYourPlayer.anim=last;
			}
		}
	}
	
}
