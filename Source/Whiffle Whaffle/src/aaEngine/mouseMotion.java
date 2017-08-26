package aaEngine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import aData.mouse;

public class mouseMotion implements MouseMotionListener {
	public void mouseMoved(MouseEvent m){
		mouse.mx=m.getX();
		mouse.my=m.getY();
		mouse.mxOnScreen=m.getXOnScreen();
		mouse.myOnScreen=m.getYOnScreen();
	}
	
	public void mouseDragged(MouseEvent m){
		mouse.mx=m.getX();
		mouse.my=m.getY();
		mouse.mxOnScreen=m.getXOnScreen();
		mouse.myOnScreen=m.getYOnScreen();
	}
}
