/**
 * GameState.java 
 * GameState
 *
 * Created by Kolesin Andrey on 20.06.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.andgame;

import android.graphics.Canvas;
import android.view.KeyEvent;

public abstract class GameState {
	
	public GameState(GameManager gm){
		m = gm;
	}

	abstract public void redrawCanvas(Canvas canvas);

	abstract public boolean doTouchEvent(android.view.MotionEvent event);

	abstract public boolean doKeyDown(int keyCode, KeyEvent msg);
	
	protected GameManager m;
}

