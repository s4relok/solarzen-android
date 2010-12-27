package com.epazzzsoftware.solarzen;

import com.epazzzsoftware.andgame.GameObject;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Target extends Point implements GameObject {
	
	public Target(int x, int y){
		super(x,y);
		
		mRect = new Rect(x-G.rTarget, y-G.rTarget,
				x+G.rTarget, y+G.rTarget);
	}
	
	public boolean isCross(Planet p){
		
		if(isCrossed)
			return false;
		
		isCrossed = p.isCross(mRect);
		return isCrossed;
	}
	
	
	
	@Override
	public void doDraw(Canvas c) {
		c.drawBitmap(G.bmp.target, x-G.SUN_R, y-G.SUN_R, null);		
	}

	@Override
	public boolean doTouchDown(int xc, int yc) {
		return false;
	}

	@Override
	public boolean doTouchMove(int xc, int yc) {
		return false;
	}

	@Override
	public boolean doTouchUp(int xc, int yc) {
		return false;
	}

	@Override
	public void update() {		
	}

	public void setCrossed(boolean isCrossed) {
		this.isCrossed = isCrossed;
	}

	private boolean isCrossed = false;
	protected Rect mRect;

}
