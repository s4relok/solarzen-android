/**
 * NewGameState.java 
 * NewGameState
 *
 * Created by Kolesin Andrey on 09.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.epazzzsoftware.andgame.GameManager;
import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.SoundOff;
import com.epazzzsoftware.andgame.TouchButton;

/**
 * @author s4relok
 *
 */
public class NewGameState extends GameState {

	public NewGameState(MyGameManager gm, GameState prev) {
		super(gm);
		
		_prev = prev;
		_planets = new PlanetButton[G.LEVELS];
		
		for (int i = 0; i < G.LEVELS; i++) {
			
			if (i<=MyGameManager.getUserLevel()) {
				
				_planets[i] = new PlanetButton((int)Math.round(G.X2 + Math.cos(_w)*G.X2MENU),
						(int)Math.round(G.Y2 + Math.sin(_w)*G.X2MENU),
						G.bmp.planetsss[i], i);
				
			} else {
				
				_planets[i] = new NullPlanetButton((int)Math.round(G.X2 + Math.cos(_w)*G.X2MENU),
						(int)Math.round(G.Y2 + Math.sin(_w)*G.X2MENU),
						null, i);
			}
			
			
			
			_w+=2*Math.PI/G.LEVELS;
			
		}
		
		_w=0;
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
		int x = new Float(event.getX()).intValue();
		int y = new Float(event.getY()).intValue();

		
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:	
			
			if(_btnSun.isTouch(x, y)){
				
				_rotateCnt = 10;
				
				_timer = new Timer();
				_timer.schedule(new TimerTask(){
					public void run(){
						_rotateCnt++;
					}
				}, 0, 100);
				
			} 
			
			else if(_btnBack.isTouch(x, y)){
				
				m.setGameState(_prev);
				
			}
			
			/*else if(_btnPractice.isTouch(x, y)){
				
				m.setGameState(new GameplayState((MyGameManager) m));
				
			}*/
			
			else if(_btnSound.isTouch(x, y)){
				
				MyGameManager.class.cast(m).switchSoundState();
				
			}
			
			else {
				
				for (int i = 0; i < G.LEVELS; i++) {
					if(_planets[i].contains(x, y)){
						m.setLevel(_planets[i].getLevelNumber());
						m.setGameState(new GameplayState((MyGameManager) m));
						return true;
					}
				}
				
			}
			break;
			
		case MotionEvent.ACTION_UP:
			
			if(_timer!=null)
				_timer.cancel();
			
			break;
			
		}
		
		return true;
	}
	
	public static void drawSoundState(Canvas canvas, GameManager gm) {
		Bitmap soundState = null;
		
		if(gm.getSoundState() instanceof SoundOff)
			soundState = G.bmp.sound_off;
		else
			soundState = G.bmp.sound_on;
		
		canvas.drawBitmap(soundState, G.SOUND_XY.x, G.SOUND_XY.y, null);
	}
		
	/* (non-Javadoc)
	 * @see com.epazzzsoftware.andgame.GameState#redrawCanvas(android.graphics.Canvas)
	 */
	@Override
	public void redrawCanvas(Canvas canvas) {
		
		canvas.drawBitmap(G.bmp.background, 0, 0, null);		
		canvas.drawBitmap(G.bmp.sunb, G.X2 - G.SUNB_R, G.Y2 - G.SUNB_R, null);
		
		drawSoundState(canvas, m);
		
		canvas.drawBitmap(G.bmp.blackhole_back, 0, 0, null);
		/*canvas.drawBitmap(G.bmp.blackhole_practice, G.SCREEN_WIDTH-G.bmp.blackhole_practice.getWidth(),
				G.SCREEN_HEIGTH-G.bmp.blackhole_practice.getHeight(), null);*/
		
		
		for (int i = 0; i < _planets.length; i++) {
			_planets[i].doDraw(canvas);
		}
		
		if(_rotateCnt!=0){
			
			_w += ((2*Math.PI/G.LEVELS)/10);
			
			for (int i = 0; i < G.LEVELS; i++) {
				
				_planets[i].set((int)Math.round(G.X2 + Math.cos(_w)*G.X2MENU),
					(int)Math.round(G.Y2 + Math.sin(_w)*G.X2MENU));
				
				_w+=2*Math.PI/G.LEVELS;
				
			}
			
			_rotateCnt--;
		}
		

	}
	private GameState _prev;
	private Timer _timer;
	private int _rotateCnt = 0;
	private PlanetButton[] _planets;
	private TouchButton _btnSound = new TouchButton(G.SOUND_XY.x, G.SOUND_XY.y, G.bmp.sound_on.getWidth(),
			G.bmp.sound_on.getWidth());
	private TouchButton _btnSun = new TouchButton(G.X2 - G.SUNB_R, G.Y2 - G.SUNB_R, G.SUNB_R*2, G.SUNB_R*2);
	private TouchButton _btnBack = new TouchButton(0, 0, G.bmp.blackhole_back.getWidth(), G.bmp.blackhole_back.getHeight());
	/*private TouchButton _btnPractice = new TouchButton(G.SCREEN_WIDTH-G.bmp.blackhole_practice.getWidth(),
			G.SCREEN_HEIGTH-G.bmp.blackhole_practice.getHeight(), G.bmp.blackhole_back.getWidth(), G.bmp.blackhole_back.getHeight());*/
	private float _w = 0;

}
