package drawer;

import java.awt.Graphics;

public class aaDrawHandler {
	
	double xRatio=0;
	double yRatio=0;
	
	private menu menu = new menu();
	private edit edit = new edit();
	public void refresh(Graphics g){
		menu.refresh(g);
		edit.refresh(g);
	}
	
}
