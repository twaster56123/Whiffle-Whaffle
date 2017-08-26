package aaEngine;

import java.awt.Graphics;

import javax.swing.JPanel;

import drawer.aaDrawHandler;

import aData.general;


public class aaScreen extends JPanel{

	mouseClicked mc = new mouseClicked();
	mouseMotion mm = new mouseMotion();
	keyBoard kb = new keyBoard();
	public aaScreen(){
		this.setDoubleBuffered(true);
		setLayout(null);
		this.addMouseListener(mc);
		this.addMouseMotionListener(mm);
		this.addKeyListener(kb);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		validate();
	}
	
	private static aaDrawHandler aDrawHandler=new aaDrawHandler();
	public void paintComponent(Graphics g){
		if(this.getWidth()>0 && this.getHeight()>0){
			if(general.screen_sizeX==0 || general.screen_sizeY==0){
				general.screen_sizeX=getWidth();
				general.screen_sizeY=getHeight();
			}
			if(getWidth()!=general.csx || getHeight()!=general.csy){
				general.csx=getWidth();
				general.csy=getHeight();
//				System.out.println("Screen: "+general.csx+", "+general.csy);
			}
			aDrawHandler.refresh(g);		
			g.dispose();
		}
	}
	
	public void update(){
		repaint();
		revalidate();
	}
}
