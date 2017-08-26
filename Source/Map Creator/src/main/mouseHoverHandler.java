package main;

import aData.general;
import aData.mouse;

public class mouseHoverHandler extends mouse{

	double xRatio=0;
	double yRatio=0;
	
	public void hover(){
		this.xRatio=general.getScaleRatioX();
		this.yRatio=general.getScaleRatioY();

	}

}
