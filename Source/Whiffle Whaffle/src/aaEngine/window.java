package aaEngine;

import javax.swing.JFrame;

public class window extends JFrame{

	public static aaScreen sc = new aaScreen();
	public void make(String title, int width, int height, boolean resizable){
		this.add(sc);
		this.setTitle(title);
		this.setSize(width, height);
		this.setResizable(resizable);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		sc.setVisible(true);
		this.validate();
		sc.update();
	}
	
}
