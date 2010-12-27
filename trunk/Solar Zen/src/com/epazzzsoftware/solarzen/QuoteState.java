/**
 * QuoteState.java 
 * QuoteState
 *
 * Created by Kolesin Andrey on 25.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.GameView;

/**
 * @author s4relok
 *
 */
public class QuoteState extends GameState {

	public QuoteState(MyGameManager gm) {
		super(gm);
		
		mTextPaint = new Paint();
		mTextPaint.setColor(Color.WHITE);
		
		//mTextPaint.setTypeface(Typeface.SANS_SERIF);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Align.CENTER);
		mTextPaint.setTypeface(Typeface.createFromAsset(GameView.mContext.getAssets(), "fonts/times.ttf"));
		mTextPaint.setTextSize(30);
		
		//mTextPaint.setMaskFilter(MaskFilterSpan.wrap()
		
		mTextStrings = G.QUOTES[m.getLevel()].split("#");
		
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
			m.nextLevel();
			if(MyGameManager.getUserLevel()<m.getLevel())
				MyGameManager.setUserLevel(m.getLevel());
			//m.setGameState(new QuoteState((MyGameManager) m));
			m.setGameState(new NewGameState((MyGameManager) m, new MenuState((MyGameManager) m)));
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
		canvas.drawBitmap(G.bmp.background, 0, 0, null);
				
		for (int j = 0; j < mTextStrings.length; j++) {
			
			String s = mTextStrings[j];
			canvas.drawText(s, G.X2, 60+j*32, mTextPaint);
			
		}
		

	}
	
	private Paint mTextPaint;
	private String[] mTextStrings;

}
