package aaEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyBoard implements KeyListener{
	
	public void keyPressed(KeyEvent key){
		aData.keys.keysDown[key.getKeyCode()]=true;
	}
	
	public void keyReleased(KeyEvent key){
		aData.keys.keysDown[key.getKeyCode()]=false;
	}
	
	public void keyTyped(KeyEvent key){
		aData.keys.keyTyped=key.getKeyChar();
	}

	private static class keys{
		public static boolean isKeyDown(int keyCode){
			boolean down=false;
			if(aData.keys.keysDown[keyCode]){down=true;}
			return down;
		}
	}
	
}
