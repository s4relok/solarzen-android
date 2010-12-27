package com.epazzzsoftware.solarzen;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.S;
import com.epazzzsoftware.andgame.TouchButton;

public class MenuState extends GameState {

	public MenuState(MyGameManager m) {
		super(m);
		
		mAboutState = new AboutState(m, this);
	}

	@Override
	public boolean doKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(mContinueBtn.isTouch(x, y)) 
				m.setGameState(new GameplayState((MyGameManager) m));
			
			else if(mNewgameBtn.isTouch(x, y)) 
				m.setGameState(new NewGameState((MyGameManager) m, this));
			
			else if(mHowtoplayBtn.isTouch(x, y)) 
				m.setGameState(new HowtoplayState(m, this));
			
			else if(mAboutBtn.isTouch(x, y)) 
				m.setGameState(mAboutState);
			
			return true;
			
		default:
			return false;
		}
	}

	@Override
	public void redrawCanvas(Canvas canvas) {
		
		canvas.drawBitmap(G.bmp.menu_background, 0, 0, null);
		
		if (MyGameManager.getUserLevel() > S.level.lvl1) {
			canvas.drawBitmap(G.bmp.menu_text_out, menu_text_x, menu_text_y, null);
			canvas.drawBitmap(G.bmp.text_continue, menu_text_x, menu_text_y, null);
		} 		
		else
			canvas.drawBitmap(G.bmp.menu_text, menu_text_x, menu_text_y, null);

	}
	
	private static int menu_text_x = S.getX("menu_text");
	private static int menu_text_y = S.getY("menu_text");
	
	private GameState mAboutState;
	//private GameState mHowtoplayState;
	//private GameState mNewState;
	
	private TouchButton mContinueBtn = new TouchButton("btn_continue");
	private TouchButton mNewgameBtn = new TouchButton("btn_newgame");
	private TouchButton mHowtoplayBtn = new TouchButton("btn_howtoplay");
	private TouchButton mAboutBtn = new TouchButton("btn_about");

}
