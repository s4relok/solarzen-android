/**
 * GameView.java 
 * GameView
 *
 * Created by Kolesin Andrey on 24.06.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.andgame;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author s4relok
 *
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mThis = this;
		
		// subscribe on Surface events
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);		
		mContext = context;		
		
		setBackgroundResource(context.getResources().getIdentifier("splash",
				"anim", ActivityExtend.defPackage));
		mSplash = (AnimationDrawable) getBackground();		
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		mSplash.start();
			
		S.init();
			
		timer = new Timer(false);
		long delay = mContext.getResources().getInteger(
				mContext.getResources().getIdentifier("splash_delay", "attr",
						ActivityExtend.defPackage));
		timer.schedule(mFinalAnimation, delay);			
		
		
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		boolean retry = true;
		
		// sound
		mThread.getSoundState().release();
		mThread.setRunning(false);
		mThread.dispose();
		
		while (retry) {
			try {
				// waiting for closing thread
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
				
	}
			
	protected void setGameMode(){
		// stop timer
		
			
		timer.cancel();
		mSplash.stop();
		mSplash = null;

		mThis.setBackgroundDrawable(null);
		//mThread = new GameManager(getHolder(), mContext);
		//mThread.setRunning(true);
		//mThread.start();
	}
	
	private TimerTask mFinalAnimation = new TimerTask(){

		@Override
		public void run() {
			mSplash.scheduleSelf(new AnimationRoutine(), 0);			
		}
		
	};
	
	private class AnimationRoutine extends TimerTask {
		AnimationRoutine() {
		}

		public void run() {
			setGameMode();
		}
	}
	
	@Override
	/*
	 * * Standard override to get key-press events.
	 */
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return mThread.doKeyDown(keyCode, msg);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if ((mSplash != null) && // if exist
				(mSplash.isRunning()) && // is running
				(event.getAction() == MotionEvent.ACTION_DOWN)) { // touch down
			timer.cancel();
			setGameMode();
			return true;
		}

		return mThread.doTouchEvent(event);
	};
	
	private AnimationDrawable mSplash;
	/**
	 * Surface for drawing
	 */
	
	private SurfaceHolder mSurfaceHolder;
	/** Context object */
	static public Context mContext = null;
	/**
	 * Thread that draw on surface
	 */
	static protected GameManager mThread;
	private Timer timer;
	private GameView mThis;

}
