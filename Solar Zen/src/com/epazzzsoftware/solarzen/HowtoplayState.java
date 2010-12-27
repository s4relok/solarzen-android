package com.epazzzsoftware.solarzen;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.epazzzsoftware.andgame.GameManager;
import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.TouchButton;

public class HowtoplayState extends GameState {

	public HowtoplayState(GameManager gm, GameState prev) {
		super(gm);		
		_prev = prev;
	}
	
	public HowtoplayState(GameManager gm,GameState prev, int page) {
		this(gm, prev);
		_page = page;
	}

	@Override
	public boolean doKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doTouchEvent(MotionEvent event) {
		int x = new Float(event.getX()).intValue();
		int y = new Float(event.getY()).intValue();

		
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:	
			
			if(_btnPrev.isTouch(x, y) && (_page!=1)){
				
				m.setGameState(new HowtoplayState(m, _prev, _page-1));				
			
			}
			
			else if(_btnNext.isTouch(x, y) && (_page!=5)){
				
				m.setGameState(new HowtoplayState(m, _prev, _page+1));				
			} 
			
			else {
				
				m.setGameState(_prev);
				
			}
			
		}
		return false;
	}

	@Override
	public void redrawCanvas(Canvas canvas) {
		
		canvas.drawBitmap(G.bmp.help_pages[_page-1], 0, 0, null);
		
		if(_page!=1) canvas.drawBitmap(G.bmp.text_previous, 0, G.SCREEN_HEIGTH-50, null);
		if(_page!=5) canvas.drawBitmap(G.bmp.text_next, G.SCREEN_WIDTH-100, G.SCREEN_HEIGTH-50, null);

	}
	
	private GameState _prev;
	
	private int _page = 1;
	private TouchButton _btnPrev = new TouchButton(0,
			G.SCREEN_HEIGTH-100,
			G.X2,
			100);
	
	private TouchButton _btnNext = new TouchButton(G.X2,
			G.SCREEN_HEIGTH-100,
			G.X2,
			100);

}
