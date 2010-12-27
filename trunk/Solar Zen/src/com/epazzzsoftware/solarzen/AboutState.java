/**
 * AboutState.java 
 * AboutState
 *
 * Created by Kolesin Andrey on 09.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.S;

/**
 * @author s4relok
 *
 */
public class AboutState extends GameState {

	public AboutState(MyGameManager gm, GameState prevState) {
		super(gm);
		
		mPrevState = prevState;
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameState#doKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean doKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameState#doTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean doTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			m.setGameState(mPrevState);
			return true;

		default:
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameState#redrawCanvas(android.graphics.Canvas)
	 */
	@Override
	public void redrawCanvas(Canvas canvas) {
		
		canvas.drawBitmap(G.bmp.menu_background, 0, 0, null);
		canvas.drawBitmap(G.bmp.menu_text_abt, menu_text_abt_x, menu_text_abt_y, null);

	}
	
	private static int menu_text_abt_x = S.getX("menu_text_abt");
	private static int menu_text_abt_y = S.getY("menu_text_abt");
	
	private GameState mPrevState;

}
