package com.epazzzsoftware.andgame;

import android.graphics.Canvas;

public interface GameObject {
	
	public void doDraw(Canvas c);
	public void update();
	
	public boolean doTouchDown(int xc, int yc);
	public boolean doTouchMove(int xc, int yc);
	public boolean doTouchUp(int xc, int yc);
	
}

