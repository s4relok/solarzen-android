/**
 * GameManager.java 
 * GameManager
 *
 * Created by Kolesin Andrey on 24.06.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.andgame;


import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;

/**
 * @author s4relok
 *
 */
public class GameManager extends Thread {
	
	public GameManager(SurfaceHolder surfaceHolder, Context context, long delay) {
		mSurfaceHolder = surfaceHolder;
		mDelay = delay;
		

	}
	
	private static boolean isSoundOn = true;
	
	public static boolean isSoundOn() {
		return isSoundOn;
	}

	public static void setSoundSettings(boolean isOn){
		isSoundOn = isOn;
	}
	
	public void setSurfaceHolder(SurfaceHolder surfaceHolder){
		mSurfaceHolder = surfaceHolder;
	}
	
	public void dispose(){
		
	}
	
	public void setLevel(int levelNumber){
		mLevel = levelNumber;
	}
	
	@Override
	public void run() {	
		Canvas canvas = null;
		
		
			
		while (isRunning) {
			try {
				canvas = mSurfaceHolder.lockCanvas();
				synchronized (mSurfaceHolder) {
					mGameState.redrawCanvas(canvas);
					sleep(mDelay);
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				if (canvas != null) {
					mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}

	}
	
	public boolean doKeyDown(int keyCode, KeyEvent msg) {
		synchronized (mSurfaceHolder) {

			return mGameState.doKeyDown(keyCode, msg);
		}
	}

	public boolean doTouchEvent(android.view.MotionEvent event) {

		return mGameState.doTouchEvent(event);
	}
	
	/**
	 * @return the isRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}
	
	public void nextLevel(){ 
			mLevel++;
	}
	
	/**
	 * @param isRunning the isRunning to set
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}


	public void setSoundOn(boolean isOn){
		
		setSoundSettings(isOn);
		
		if(isOn){
			mSoundState = mSoundOnState;
			Log.w(S.TAG, "Sound state is on");
		} else {
			mSoundState = mSoundOffState;
			Log.w(S.TAG, "Sound state is off");
		}
	}
	
	public void setGameState(GameState gs){
		mGameState = gs;
	}


	public void setFirstLevel(){
		mLevel = S.level.lvl1;
	}

	public void setDifficulty(int mDifficulty) {
		this.mDifficulty = mDifficulty;
	}

	public SoundState getSoundState() {
		return mSoundState;
	}

	public int getLevel(){
		return mLevel;
	}

	public int getDifficulty() {
		return mDifficulty;
	}

	private boolean isRunning = false;
	
	/** Surface when we will draw */
	private SurfaceHolder mSurfaceHolder;
	
	protected SoundState mSoundState;
	protected SoundState mSoundOnState;// = new SoundOnState();
	protected SoundState mSoundOffState = new SoundOff();

	private GameState mGameState;
	private int mDifficulty = S.difficulty.normal;
	protected int mLevel = S.level.lvl1;
	private long mDelay;
	
}
