/**
 * GameplayState.java 
 * GameplayState
 *
 * Created by Kolesin Andrey on 09.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.S;
import com.epazzzsoftware.andgame.TouchButton;

/**
 * @author s4relok
 *
 */
public class GameplayState extends GameState {

	public GameplayState(MyGameManager gm) {
		super(gm);
		
		int planetsNum = MyGameManager.getUserLevel() + 1;//m.getLevel()+1;
		
		Planet[] planets = new Planet[planetsNum];
		
		for (int i = planetsNum; i > 0; i--) {
			
			planets[(planetsNum) - i] = new Planet(G.bmp.planetsss[i-1], 160, G.rPlanet, G.COLORS[i-1]);
		}
		
		
		mCar = new Carousel((MyGameManager) m, mLives, planets);		
		mTargets = new TargetList(G.LEVELS_TARGETS[m.getLevel()]);
		
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
		
		if(isEnd) return false;
		
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:			
			if(btnReset.isTouch(x, y)){
				reset();
			}
			else if(btnSlide.isTouch(x, y))
				mCar.slideRight();
			
			else if(_btnBack.isTouch(x, y)){
				
				m.setGameState(new NewGameState((MyGameManager) m, new MenuState((MyGameManager) m)));
				
			}
			
			else if(_btnSound.isTouch(x, y)){

				MyGameManager.class.cast(m).switchSoundState();
				
			}
			
			else 
				return mCar.doTouchDown(x, y);
			
						
		case MotionEvent.ACTION_MOVE:		
			return mCar.doTouchMove(x, y);			
			
		case MotionEvent.ACTION_UP:			
			return mCar.doTouchUp(x, y);
					
		default:
			return false;
		}
	}

	@Override
	public void redrawCanvas(Canvas canvas) {

		canvas.drawBitmap(G.bmp.background, 0, 0, null);
		canvas.drawBitmap(G.bmp.play_area480, -80, 0, null);	
		canvas.drawBitmap(G.bmp.text_back, G.BACK_GAME_XY.x, G.BACK_GAME_XY.y, null);
		
		NewGameState.drawSoundState(canvas, m);
		
		canvas.drawBitmap(G.bmp.sun, sun_pos.x, sun_pos.y, null);
		
		
		if(!isEnd) {
			mCar.update();
			mTargets.update();
		}
			
		mCar.doDraw(canvas);		
		mTargets.doDraw(canvas);
		
		for (int i = 0; i < mLives.size(); i++) {
			Planet p = mLives.get(i);
			
			if(!isEnd){
				
				p.update();
				
				if(p.isCrush(btnReset.mRect) || p.isFarAway()){
					
					m.getSoundState().play(R.raw.pln_dst_bypln, 0);
					mCar.add(p);
					mCar.resetCarousel();
					p = new NullPlanet();
					mLives.set(i, p);
				}
				
			}
			
			p.doDraw(canvas);			
		}
		
		if(!isEnd){
			if(mTargets.checkVictory(mLives.toArray())){
				// onWin
				onWin();				
			}			
		}
							
	}

	private void onWin(){
		
		isEnd = true;
		
		m.getSoundState().play(R.raw.lvl_ends, 0);
		
		Timer t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				onAfterWin();				
			}
			
		}, 4000);
	}
	
	private void onAfterWin(){
		m.setGameState(new QuoteState((MyGameManager) m));
	}
	
	private void reset(){
		
		Planet.numPlanetsFly=0;
		
		for (Planet p : mLives){
			if(!(p instanceof NullPlanet))
				mCar.add(p);
		}
			
		
		mLives.clear();
		
		mCar.resetCarousel();
				
		
	}
	
	private boolean isEnd = false;
		
	// buttons
	private TouchButton btnReset = new TouchButton(G.X2 - G.SUN_R, G.Y2 - G.SUN_R, G.SUN_R*2, G.SUN_R*2);
	private TouchButton btnSlide = new TouchButton(G.STR_C_X - G.HANDLE_R, G.STR_C_Y - G.HANDLE_R,
			G.HANDLE_R*2,  G.HANDLE_R*2);
	
	private Carousel mCar;
	private TargetList mTargets;
	private ArrayList<Planet> mLives = new ArrayList<Planet>();
	
	private static final Point sun_pos = S.getXY("sun");
	
	private TouchButton _btnSound = new TouchButton(G.SOUND_XY.x, G.SOUND_XY.y, G.bmp.sound_on.getWidth(),
			G.bmp.sound_on.getWidth());
	
	private TouchButton _btnBack = new TouchButton(0, 0, G.bmp.blackhole_back.getWidth(), G.bmp.blackhole_back.getHeight());

}
