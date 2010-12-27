/**
 * Planet.java 
 * Planet
 *
 * Created by Kolesin Andrey on 10.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

import com.epazzzsoftware.andgame.GameObject;
import com.epazzzsoftware.andgame.GameView;
import com.epazzzsoftware.andgame.TouchButton;

/**
 * @author s4relok
 *
 */
public class Planet extends Point implements GameObject {
	
	public Planet(){
		
	}
	
	public Planet(Bitmap b, int x, int y, int color){
		super(x, y);
						
		mTouchButton = new TouchButton(x-G.rPlanet, y-G.rPlanet, G.rPlanet*2, G.rPlanet*2);
				
		mImage = b;
		
		pLog = new Paint();
		pLog.setColor(Color.WHITE);
		pLog.setTextSize(15);
		pLog.setTypeface(Typeface.SANS_SERIF);
		pLog.setAntiAlias(true);
		pLog.setStyle(Style.FILL_AND_STROKE);
		
		mFollower = new Follower(color);
		
		
		mLaunchState = isArrowLaunch == true ? 
				new ArrowLaunch() : new HandLaunch();
		
	}
	
	@Override
	public void set(int x, int y) {
		super.set(x, y);				
		mTouchButton.mRect.set(x-G.rPlanet, y-G.rPlanet, x+G.rPlanet, y+G.rPlanet);
	}
	
	public boolean isCrush(Rect target){			
		if(target.contains(x, y))
			return true;
		else 
			return false;
	}
	
	
	
	public boolean isFarAway(){
		if(length >= G.MAX_LENGTH)
			return true;
		return false;
	}
	
	public boolean isCross(Rect target){
		
		float tx = target.left + G.rTarget;
		float ty = target.top + G.rTarget;
		
		float dX = tx - x;
	    float dY = ty - y;    

	    return Math.sqrt(dX * dX + dY * dY) < (G.SUN_R+ G.rPlanet - 7);		
	}
	
	public void update(){

		if(isFixed)	return;
		
		yys = 240 - y;
		xxs = 160 - x;
				
		length = (float) Math.sqrt(Math.pow(yys, 2)+Math.pow(xxs, 2));

		cosPl = yys/length;
		sinPl = xxs/length;
				
		gravitation =  (float) (com.epazzzsoftware.solarzen.G.g * ( (mass*67) / (Math.pow(length, 2)) ));
		
		tgPl = yys/xxs;
		
		mFollower.setCurrentPosition(x, y, tgPl);		
		
		// speed
		Vx += gravitation*sinPl;
		Vy += gravitation*cosPl;
		
		// position
		y += (int)Math.round(Vy);
		x += (int)Math.round(Vx);

	}
	
	private float yys;
	private float xxs;
	private float cosPl;
	private float sinPl;
	private float gravitation;
	float tgPl;
	
	public void doDrawLaunch(Canvas c){
		mLaunchState.doDraw(c);
	}
	
	public void doDraw(Canvas c){
		c.drawBitmap(mImage, x-G.rPlanet, y-G.rPlanet, null);		
		mFollower.doDraw(c);			
	}


	
	public boolean doTouchDown(int xc, int yc){
		
		if(isFixed)
			return mLaunchState.doTouchDown(xc, yc);
		return false;		
			
	}
	
	public boolean doTouchMove(int xc, int yc){
		
		if(isDragged())
			mLaunchState.doTouchMove(xc, yc);
		return true;
	}

	public boolean doTouchUp(int xc, int yc){
		
		if(isDragged()){
			mLaunchState.doTouchUp(xc, yc);
			return true;
		}
		
		return false;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFix) {
		isFixed = isFix;
		
		if(!isFix)
			numPlanetsFly++;
	}

	public boolean isTouch(int xc, int yc){
		return mTouchButton.isTouch(xc, yc);
	}

	class Follower extends Point implements GameObject {
		
		public Follower(int color) {
			
			mTextPaint = new Paint();
			mTextPaint.setColor(GameView.mContext.getResources().getColor(color));
			mTextPaint.setTextSize(G.FONT_SIZE);
			mTextPaint.setTypeface(Typeface.SANS_SERIF);
			mTextPaint.setAntiAlias(true);
			
			mCirclePaint = new Paint();
			mCirclePaint.setColor(GameView.mContext.getResources().getColor(color));
			mCirclePaint.setAntiAlias(true);			
			
		}
				
		public void setCurrentPosition(int x, int y, double ptg){
			tg = ptg;
			px = x;
			py = y;
			
			update();
		}
	
		@Override
		public void doDraw(Canvas c) {
			
			if(isFixed) return;
			
			if(!G.SCREEN_RECT.contains(lim_x, lim_y)){
				
				c.drawCircle(x, y, G.FOLLOWER_R, mCirclePaint);
				c.drawText(String.valueOf(Math.round(length)), x + txt_x, y + txt_y, mTextPaint);
				
			}
	
		}
	
		@Override
		public boolean doTouchDown(int xc, int yc) {
			return false;
		}

		@Override
		public boolean doTouchMove(int xc, int yc) {
			return false;
		}

		@Override
		public boolean doTouchUp(int xc, int yc) {
			return false;
		}

		@Override
		public void update() {

			double ctg =  (1/tg);
			
			if(Math.abs(ctg)< G.TG_LIMIT){
				
				double b = px - ctg*py;
				
				if(py<G.Y2){
					
					y = 0;						
					x = (int) Math.round(b);
	
					txt_y = G.FOLLOWER_R*2 + G.FOLLOWER_TEXT_SHIFT;	
					lim_y = py + G.rPlanet;	
					
				} else {
					
					y = G.SCREEN_HEIGTH;						
					x = (int) Math.round(ctg*y + b);			
					
					txt_y = -(G.FOLLOWER_R*2 + G.FOLLOWER_TEXT_SHIFT);					
					
					lim_y = py - G.rPlanet;
					
				}
								
				lim_x = px;
				txt_x = 0;
				
				mTextPaint.setTextAlign(Align.CENTER);
				
				if(!(G.SCREEN_WIDTH - x > G.FOLLOWER_TEXT_INDENT)){
					
					txt_x = (G.SCREEN_WIDTH - x) - G.FOLLOWER_TEXT_INDENT;
				}					
				else if(!(x > G.FOLLOWER_TEXT_INDENT)){
					
					txt_x = G.FOLLOWER_TEXT_INDENT - x;
				}
					
				
	
			} else {
				
				double b = py - tg*px;
				
				if(px<G.X2){
					
					x = 0;			
					y = (int) Math.round(b);
					
					txt_x = G.FOLLOWER_TEXT_SHIFT;					
					mTextPaint.setTextAlign(Align.LEFT);
					
					lim_x = px + G.rPlanet;
					
					
					
				} else {
					
					x = G.SCREEN_WIDTH;						
					y = (int) Math.round(tg*x + b);
					
					txt_x = - G.FOLLOWER_TEXT_SHIFT;					
					mTextPaint.setTextAlign(Align.RIGHT);
					
					lim_x = px - G.rPlanet;
				}
				
				lim_y = py;
				txt_y = G.FOLLOWER_R;
				
			}
			
		}
			
		private Paint mCirclePaint;
		private Paint mTextPaint;
		private double tg;
		
		private int txt_x;
		private int txt_y;
		
		private int px;
		private int py;
		
		private int lim_x;
		private int lim_y;
		
	}

	class HandLaunch implements GameObject {
	
		@Override
		public boolean doTouchDown(int xc, int yc) {
			if(G.startRect.contains(xc, yc)){
				setDragged(true);
				return true;
			}	
			return false;
		}
	
		@Override
		public boolean doTouchMove(int xc, int yc) {
			if(!G.startRect.contains(xc, yc)){
				releasePlanet();
			}
			
			set(xc, yc);
			return true;
		}
	
		@Override
		public boolean doTouchUp(int xc, int yc) {
			setDragged(false);			
			releasePlanet();
			return true;
		}
	
		@Override
		public void doDraw(Canvas c) {

			update();			
		}
	
		@Override
		public void update() {
			
			if(prevMovePos != null){
				
				moveSpeed.x = Math.abs(x - prevMovePos.x) < 1 ? 
						moveSpeed.x: x - prevMovePos.x;
				LOG_VX_INIT = moveSpeed.x;
				
				moveSpeed.y = Math.abs(y - prevMovePos.y) < 1 ?
						moveSpeed.y : y - prevMovePos.y;
				
				LOG_VY_INIT = moveSpeed.y;
				
			} else {
				prevMovePos = new Point();
			}
			
			prevMovePos.set(x, y);
		}
		
		private float validateSpeed(float d){
				
			return d/1;
		}
		
		private void releasePlanet() {
			
			if(moveSpeed.x==0
					&& moveSpeed.y == 0){
				return;
			}
						
			setFixed(false);
			
			Vx = validateSpeed(moveSpeed.x);
			Vy = validateSpeed(moveSpeed.y);
			
			// log
			LOG_VX_INIT = Vx;
			LOG_VY_INIT = Vy;
		}

		private Point prevMovePos;
		
	}

	class ArrowLaunch implements GameObject {

		public ArrowLaunch(){
						
			pInto = new Paint();
			pInto.setStyle(Style.FILL_AND_STROKE);
			pInto.setAntiAlias(true);
			pInto.setColor(GameView.mContext.getResources().getColor(R.color.arrow_in));
			
			pOut = new Paint();
			pOut.setStyle(Style.FILL_AND_STROKE);
			pOut.setAntiAlias(true);
			pOut.setColor(GameView.mContext.getResources().getColor(R.color.arrow_out));
			
			pPowTxt = new Paint();
			pPowTxt.setColor(Color.WHITE);
			pPowTxt.setTextSize(28);
			pPowTxt.setTypeface(Typeface.DEFAULT_BOLD);
			pPowTxt.setAntiAlias(true);
		
		}
	
		@Override
		public boolean doTouchDown(int xc, int yc) {
			
				setDragged(true);
				return true;
		}
	
		@Override
		public boolean doTouchMove(int xc, int yc) {
			mx = xc;
			my = yc;
			
			// arrow
			mx_xp = mx - x;
			my_yp = my - y;
			
			// full length
			mxy = (float) Math.sqrt(Math.pow(mx_xp, 2)+Math.pow(my_yp, 2));
	
			// angle correcting
			{
				sin = mx_xp/mxy;
				
				if(my - y > 0 )				
					m_angle = (float) Math.toDegrees(Math.asin(sin));
				else {
					if(Math.asin(sin)<0)
						m_angle = (float) Math.toDegrees(-(Math.PI + Math.asin(sin)));
					else
						m_angle = (float) Math.toDegrees(Math.PI - Math.asin(sin));
				}
			}
			
			// if too little
			if(mxy < G.ARROW_MIN_LEN){
				
				sin = mx_xp/mxy;
				cos = my_yp/mxy;			
				
				mx = x + sin*G.ARROW_MIN_LEN;
				my = y + cos*G.ARROW_MIN_LEN;
				
				mxy = G.ARROW_MIN_LEN;
				
			}
			
			// if too long
			else if(mxy > com.epazzzsoftware.solarzen.G.ARROW_LEN){
				
				sin = mx_xp/mxy;
				cos = my_yp/mxy;			
				
				mx = x + sin*G.ARROW_LEN;
				my = y + cos*G.ARROW_LEN;
				
				mxy = G.ARROW_LEN;
	
			}
			
			pow = Math.round( (mxy - G.ARROW_MIN_LEN) / G.SAMPLE_1_7) + 1;
			
			return false;
		}
	
		@Override
		public boolean doTouchUp(int xc, int yc) {
			setDragged(false);			
			this.releasePlanet();
			return true;
		}
			
		@Override
		public void doDraw(Canvas c) {
						
			c.save();
			
			c.rotate(-m_angle, x, y);
			
			int length = Math.round(mxy);
						
			path1.moveTo(x - G.ARROW_WID2, y);
			path1.lineTo(x + G.ARROW_WID2, y);
			path1.lineTo(x + G.ARROW_WID2 , y + length - G.ARROW_LEN3);
			path1.lineTo(x + G.ARROW_WID1, y + length - G.ARROW_LEN3);
			path1.lineTo(x, y + length);
			path1.lineTo(x - G.ARROW_WID1, y + length - G.ARROW_LEN3);
			path1.lineTo(x - G.ARROW_WID2, y + length - G.ARROW_LEN3);
			path1.lineTo(x - G.ARROW_WID2, y);
			path1.close();
			
			path2.moveTo(x - G.ARROW_WID2 + G.ARROW_WL, y + G.ARROW_WL);
			path2.lineTo(x + G.ARROW_WID2 - G.ARROW_WL, y + G.ARROW_WL);
			path2.lineTo(x + G.ARROW_WID2 - G.ARROW_WL, y + length - G.ARROW_LEN3 + G.ARROW_WL);
			path2.lineTo(x + G.ARROW_WID1 - G.ARROW_WL*2, y + length - G.ARROW_LEN3 + G.ARROW_WL);
			path2.lineTo(x, y + length - G.ARROW_WL*2);
			path2.lineTo(x - G.ARROW_WID1 + G.ARROW_WL*2, y + length - G.ARROW_LEN3 + G.ARROW_WL);
			path2.lineTo(x - G.ARROW_WID2 + G.ARROW_WL, y + length - G.ARROW_LEN3 + G.ARROW_WL);
			path2.lineTo(x - G.ARROW_WID2 + G.ARROW_WL, y + G.ARROW_WL);			
			path2.close();
			
			c.drawPath(path1, pOut);
			c.drawPath(path2, pInto);
			
			path1.rewind();
			path2.rewind();
						
			c.restore();	
			
			c.drawText(String.valueOf(pow), mx, my - G.SHIFT_UP_POW_TXT, pPowTxt);
			
		}
		
		@Override
		public void update() {
			
		}

		private void releasePlanet() {
						
			setFixed(false);
			
			float min_x = sin*G.ARROW_MIN_LEN;
			float min_y = cos*G.ARROW_MIN_LEN;
			
			float dx = mx - x;
			float dy = my - y;					
			
			Vx = min_x/G.SPEED_MIN_DIVIDER + (dx - min_x) / G.SPEED_DIVIDER;
			Vy = min_y/G.SPEED_MIN_DIVIDER + (dy - min_y) / G.SPEED_DIVIDER;
			
			// log
			LOG_VX_INIT = Vx;
			LOG_VY_INIT = Vy;
		}
				
		private float mx;
		private float my;
		
		private float mx_xp;
		private float my_yp;
		private float mxy;
		private float sin;
		private float cos;
		private float m_angle;
		
		private Paint pInto;
		private Paint pOut;
		private Paint pPowTxt;
		
		
		private Path path1 = new Path();
		private Path path2 = new Path();
		
		protected int pow = 1;
		
	}

	
		
	@SuppressWarnings("unused")
	private void drawLogMessage(Canvas c) {
		
		if(numPlanetsFly==1 &&
				(!isFixed)){
			
			String[] msg = {
					"x: " + String.valueOf(x-G.rPlanet),
					"y: " + String.valueOf(y-G.rPlanet),
					"Vx: " + Vx,
					"Vy: " + Vy, 
					"Vx_Init: " + LOG_VX_INIT + " (" + moveSpeed.x + ")",
					"Vy_Init: " + LOG_VY_INIT + " (" + moveSpeed.y + ")"
					};
					
					
					
			for (int i = 0; i < msg.length; i++) {
				c.drawText(msg[i], 20, 20+ 18*i, pLog);
			}
		}
		
		
	}

	
	
	public static int numPlanetsFly = 0;



	static void changeLaunchState() {
		isArrowLaunch = !isArrowLaunch;
		
		/*mLaunchState = isArrowLaunch == true ? 
				mThis.new ArrowLaunch() : mThis.new HandLaunch();*/
	}

	public boolean isDragged() {
		return isDragged;
	}
	
	private void setDragged(boolean isDrag){
		isDragged = isDrag;
	}

	static boolean isArrowLaunch = true;

	float length = 0;
	
	private TouchButton mTouchButton;
	private Bitmap mImage;

	private Paint pLog;
	private Follower mFollower;
	private Point moveSpeed = new Point(0,0);

	private GameObject mLaunchState;

	private double mass = 10;
	
	private boolean isFixed = true;
	private boolean isDragged = false;
	
	private double Vx;
	private double Vy;
	
	private double LOG_VX_INIT;
	private double LOG_VY_INIT;
}



class NullPlanet extends Planet implements GameObject{
	
	public NullPlanet() {
		
	}
	
	@Override
	public void doDrawLaunch(Canvas arg0) {
		
	}
		
	@Override
	public void set(int x, int y) {
		
	}
	
	@Override
	public boolean isFixed() {
		return true;
	}
	
	@Override
	public void setFixed(boolean arg0) {
		
	}
	
	@Override
	public boolean isTouch(int arg0, int arg1) {
		return false;
	}
	
	

	@Override
	public void doDraw(Canvas c) {
		
	}

	@Override
	public boolean doTouchDown(int xc, int yc) {
		return false;
	}

	@Override
	public boolean doTouchMove(int xc, int yc) {
		return false;
	}

	@Override
	public boolean doTouchUp(int xc, int yc) {
		return false;
	}

	@Override
	public void update() {
		
	}
	
}








