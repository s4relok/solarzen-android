package com.epazzzsoftware.solarzen;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.epazzzsoftware.andgame.GameObject;

public class Carousel implements GameObject {
	
	public Carousel(MyGameManager gm, ArrayList<Planet> lives, Planet ... planets) {
		mCells = new Planet[G.numInCarousel];
		
		m = gm;
		
		cntContainer = planets.length-G.numInCarousel;
		mContainer = planets.length > G.numInCarousel ? new Planet[cntContainer] 
		                                              : new Planet[0];
		mLives = lives;
		
		if(planets.length<G.numInCarousel){
			
			for (int i = 0; i < planets.length; i++) {
				mCells[i] = planets[i];
				setPosition(mCells[i], i);
			}
			
			for (int i = planets.length; i<G.numInCarousel; i++){
				mCells[i] = new NullPlanet();
			}
			
		} else {
			
			for (int i = 0; i < G.numInCarousel; i++) {
				mCells[i] = planets[i];
				setPosition(mCells[i], i);
			}
			
			if(cntContainer>0){
				for (int i = 0; i < cntContainer; i++) {
					mContainer[i] = planets[i+G.numInCarousel];
				}
			}
			
		}	
		
		
		pTxt = new Paint();
		pTxt.setColor(Color.WHITE);
		pTxt.setTextSize(27);
		pTxt.setTypeface(Typeface.SANS_SERIF);
		pTxt.setAntiAlias(true);
		
				
	}

	public void add(Planet p){
		
		for (int i = 0; i < G.numInCarousel; i++) {
			if(mCells[i] instanceof NullPlanet){
				mCells[i] = p;
				setPosition(mCells[i], i);
				return;
			}				
		}
		
		for (int i = 0; i < mContainer.length; i++) {
			if(mContainer[i] instanceof NullPlanet){
				p.setFixed(true);
				mContainer[i] = p;
				mContainer[i].set(-100, -100);
				
				cntContainer++;
				return;
			}
		}
		
	}
	
	public void slideRight(){
		
		Planet bp;
		
		bp = mCells[0];
		
		for (int i = 0; i < G.numInCarousel-1; i++) {
			mCells[i] = mCells[i+1];
			setPosition(mCells[i], i);
		}
		
		mCells[G.numInCarousel-1] = bp;
		mCells[G.numInCarousel-1].set(G.BBL_POS[(G.numInCarousel-1)*2] + 
				G.STR_C_X - G.STR_R, G.BBL_POS[(G.numInCarousel-1)*2+1] + G.STR_C_Y - G.STR_R);
	}
	
	public void resetCarousel(){

		for (int i = 0; i < G.numInCarousel; i++) {

			mCells[i].setFixed(true);
			setPosition(mCells[i], i);
		}
		
	}

	@Override
	public void doDraw(Canvas c) {
				
		c.drawBitmap(G.bmp.str, G.STR_C_X - G.STR_R, G.STR_C_Y - G.STR_R, null);
		c.drawBitmap(G.bmp.handle, G.STR_C_X - G.HANDLE_R, G.STR_C_Y - G.HANDLE_R, null);
		
		Planet dragged = new NullPlanet();
		
		for (int i = 0; i < G.numInCarousel; i++) {
			
			// position
			c.drawBitmap(G.bmp.bbl_in_str, G.BBL_POS[i*2] + G.STR_C_X - G.STR_R - G.BBL_R,
					G.BBL_POS[i*2+1] + G.STR_C_Y - G.STR_R - G.BBL_R , null);
			
			//planet
			mCells[i].doDraw(c);
			
			if(mCells[i].isDragged())
				dragged = mCells[i];
		}
		
		dragged.doDrawLaunch(c);
		
		if(cntContainer>0)
			c.drawText("+ " + String.valueOf(cntContainer), 10 , 467, pTxt);

	}

	@Override
	public void update() {
		
		for (int i = 0; i < G.numInCarousel; i++) {
			mCells[i].update();			
		}

	}
	
	@Override
	public boolean doTouchDown(int xc, int yc) {
		
		for (int i = 0; i < G.numInCarousel; i++) {
			
			if(mCells[i].isTouch(xc, yc)){
				
				mCells[i].doTouchDown(xc, yc);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean doTouchMove(int xc, int yc) {
		
		for (int i = 0; i < G.numInCarousel; i++) {
			
			mCells[i].doTouchMove(xc, yc);

		}
		return false;
	}

	@Override
	public boolean doTouchUp(int xc, int yc) {
		
		for (int i = 0; i < G.numInCarousel; i++) {
			
			if(mCells[i].doTouchUp(xc, yc)){
				
				m.getSoundState().play(R.raw.menu_out, 0);
				mLives.add(mCells[i]);				
				mCells[i] = nextFromContainer(i);				
				
				return true;
			}

		}
		
		return false;
	}
	
	private void setPosition(Planet p, int i) {
		p.set(G.BBL_POS[i*2] + G.STR_C_X - G.STR_R, G.BBL_POS[i*2+1] + G.STR_C_Y - G.STR_R);
	}

	private Planet nextFromContainer(int pos){
		for (int i = 0; i < mContainer.length; i++) {
			if(!(mContainer[i] instanceof NullPlanet)){
				Planet p = mContainer[i];
				mContainer[i] = new NullPlanet();
				
				setPosition(p, pos);
				
				cntContainer--;
				return p;
			}
		}
		
		return new NullPlanet();
	}

	private MyGameManager m;
	private Paint pTxt;
	private int cntContainer;
	private ArrayList<Planet> mLives;
	private Planet[] mCells;
	private Planet[] mContainer;

}
