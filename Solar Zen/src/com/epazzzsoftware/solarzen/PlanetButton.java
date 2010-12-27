/**
 * PlanetButton.java 
 * PlanetButton
 *
 * Created by Kolesin Andrey on 22.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.epazzzsoftware.andgame.GameObject;

/**
 * @author s4relok
 *
 */
public class PlanetButton extends Point implements GameObject {

	public PlanetButton(int x, int y, Bitmap b, int levelNumber) {
		super(x,y);
		
		_levelNumber = levelNumber;
		_bmp = b;
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameObject#doDraw(android.graphics.Canvas)
	 */
	@Override
	public void doDraw(Canvas c) {
		
		c.drawBitmap(_bmp, x-G.rPlanet, y-G.rPlanet, null);

	}
	
	public boolean contains(int xc, int yc){
		
		return new Rect(x-G.rPlanet, y-G.rPlanet, x+G.rPlanet, y+G.rPlanet).contains(xc, yc);
		
	}
	

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameObject#doTouchDown(int, int)
	 */
	@Override
	public boolean doTouchDown(int xc, int yc) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameObject#doTouchMove(int, int)
	 */
	@Override
	public boolean doTouchMove(int xc, int yc) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameObject#doTouchUp(int, int)
	 */
	@Override
	public boolean doTouchUp(int xc, int yc) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameObject#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public int getLevelNumber() {
		return _levelNumber;
	}

	protected Bitmap _bmp;
	private int _levelNumber;
}

class NullPlanetButton extends PlanetButton{

	public NullPlanetButton(int x, int y, Bitmap b, int levelNumber) {
		super(x, y, null, levelNumber);
		
		_bmp = G.bmp.planets_disabled[levelNumber];
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameObject#doDraw(android.graphics.Canvas)
	 */
	@Override
	public void doDraw(Canvas c) {
		
		c.drawBitmap(_bmp, x-G.rPlanet, y-G.rPlanet, null);
	
	}
	
	public boolean contains(int xc, int yc){
		
		return false;
		
	}

	
}
