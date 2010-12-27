package com.epazzzsoftware.solarzen;

import android.content.Context;
import android.util.AttributeSet;

import com.epazzzsoftware.andgame.GameView;

public class MyGameView extends GameView {

	public MyGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void setGameMode() {
		super.setGameMode();

		mThread = new MyGameManager(getHolder(), mContext);
		mThread.setRunning(true);
		mThread.start();
		
	}
	
	
}
