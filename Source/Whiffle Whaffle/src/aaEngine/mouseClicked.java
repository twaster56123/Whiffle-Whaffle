package aaEngine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import aData.mouse;

public class mouseClicked implements MouseListener {
	
	public void mouseClicked(MouseEvent m){
		if(m.getButton()==1){
			mouse.leftClicked=true;	
		}
		if(m.getButton()==3){
			mouse.rightClicked=true;	
		}
	}
	
	public void mousePressed(MouseEvent m){
		mouse.mouseDown=true;
	}
	
	public void mouseReleased(MouseEvent m){
		mouse.mouseDown=false;
	}
	
	public void mouseEntered(MouseEvent m){
		mouse.mouseIn=true;
	}
	
	public void mouseExited(MouseEvent m){
		mouse.mouseIn=false;
	}
	
}
