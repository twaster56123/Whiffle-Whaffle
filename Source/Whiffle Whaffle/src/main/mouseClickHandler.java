package main;

import aData.LoginServerData;
import aData.general;
import aData.mouse;
import aMenu.Login;
import aMenu.Register;


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
			//Register
			if(mx>=520 && mx<=684 && my>=468 && my<=511){
				if(!LoginServerData.registerOpen){
					new Register();
				}
			}
			//Login
			if(mx>=520 && mx<=684 && my>=365 && my<=424){
				if(!LoginServerData.loginOpen){
					new Login();
				}
			}
		}
	}
	
	public void leftEdit(){
		if(general.mode=="edit"){
			
		}
		
	}
	
}
