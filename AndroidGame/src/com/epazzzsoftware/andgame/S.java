/**
 * G.java 
 * G
 *
 * Created by Kolesin Andrey on 24.06.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.andgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

/**
 * @author s4relok
 *
 */
public class S {
		
	public static void init(){
		sound.init();
		difficulty.init();
		level.init();
	}
	
	public static Point getXY(String name){
		String name_x = name+"_x"; 
		int x = getInt(name_x);
		String name_y = name+"_y"; 
		int y = getInt(name_y);
		return new Point(x,y);
	}
	
	public static Rect getRect(String name){
		String name_x = name+"_x"; 
		int x = getInt(name_x);
		String name_y = name+"_y"; 
		int y = getInt(name_y);
		String name_w = name+"_w"; 
		int w = getInt(name_w);
		String name_h = name+"_h"; 
		int h = getInt(name_h);
		return new Rect(x,y,w,h);
	}
	
	public static int getX(String name){
		String name_x = name+"_x"; 
		return getInt(name_x);
	}
	
	public static int getY(String name){
		String name_y= name+"_y"; 
		return getInt(name_y);
	}
	
	public static int getW(String name){
		String name_w = name+"_w"; 
		return getInt(name_w);
	}
	
	public static int getH(String name){
		String name_h= name+"_h"; 
		return getInt(name_h);
	}
	
	static private int getInt(String name){ 
		int id = mContext.getResources().getIdentifier(name,
				"attr", ActivityExtend.defPackage);
		
		int x = (id==0 ? -1:
			mContext.getResources().getInteger(id));
		
		if(x==-1){
			String msg = "Can't find Integer "+name;
			Log.w(TAG, msg);
		}
		return x;
	}
	
	static public String getString(String name){ 
		int id = mContext.getResources().getIdentifier(name,
				"attr", ActivityExtend.defPackage);
		
		String str = (id==0 ? null:
			mContext.getResources().getString(id));
		
		if(str==null){
			String msg = "Can't find String "+name;
			Log.w(TAG, msg);
		}
		return str;
	}
			
	public static Bitmap getBitmap(String name) {
		
		int id = mContext.getResources().getIdentifier(name,
				"drawable", ActivityExtend.defPackage);
		
		Bitmap b = (id==0 ? null:
			BitmapFactory.decodeResource(mContext.getResources(), id));
		
		if(b==null){
			String msg = "Can't find bitmap "+name;
			Log.w(TAG, msg);
		}
			
		 
		return b;
	}
	
	public static Bitmap getBitmap(int id) {
				
		Bitmap b = (id==0 ? null:
			BitmapFactory.decodeResource(mContext.getResources(), id));
		
		if(b==null){
			String msg = "Can't find bitmap by id "+ id;
			Log.w(TAG, msg);
		}
			
		 
		return b;
	}
	
	static public Bitmap[] getBitmaps(String format, int first, int last) {
		
		Bitmap[] bs = new Bitmap[last-first+1];
		String name;
		int j = 0;
		for (int i = first; i <= last; i++) {
			name = String.format(format, i);
			bs[j] = getBitmap(name); j++;			
		}
		 
		return bs;
	}
	
	static public MediaPlayer getSound(String name) {
		
		int id = mContext.getResources().getIdentifier(name,
				"raw", ActivityExtend.defPackage);
		
		MediaPlayer mp = (id==0 ? null:
			MediaPlayer.create(mContext, id));
		
		if(mp==null){
			String msg = "Can't find sound "+name;
			Log.w(TAG, msg);
		}
			
		return mp;
	}
	
	static public SoundPool getSoundPool(String name) {
		
		int id = mContext.getResources().getIdentifier(name,
				"raw", ActivityExtend.defPackage);
		
		SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
	
		sound.e = (id==0 ? -1:
			sp.load(mContext, id, 1));
		
		if(sound.e==-1){
			String msg = "Can't find sound "+name;
			Log.w(TAG, msg);
		}
			
		return sp;
	}
	
	public static int[] getDigitArray(int integer) {
		int pos = 0;
		int[] time = { 0, 0, 0 };
		String txtTime = String.valueOf(integer);
		char[] c = txtTime.toCharArray();
		int len = txtTime.length();
		
		for (int j = len - 1; j >= 0; j--) {
			int digit = Integer.parseInt(String.valueOf(c[j]));
			time[2 - pos] = digit;
			++pos;
		}
		return time;
	}
	
    public static final class sound {
    	static void init(){}
    	
    	public static final float VOLUME = 0.8f;
    	public static int e = 1;
    }
    
    public static final class difficulty {
    	static void init(){}
    	
    	public static final int easy = 0;
    	public static final int normal = 1;
    	public static final int hard = 2;
    }
    
    public static final class level {
    	static void init(){}
    	
    	public static final int lvl1 = 0;
    	public static final int lvl2 = 1;
    	public static final int lvl3 = 2;
    	public static final int lvl4 = 3;
    	public static final int lvl5 = 4;
    	public static final int lvl6 = 5;
    	public static final int lvl7 = 6;
    	public static final int lvl8 = 7;
    	public static final int lvl9 = 8;
    	public static final int lvl10 = 9;
    	public static final int lvl11 = 10;
    	public static final int lvl12 = 11;
    	public static final int lvl13 = 12;
    	public static final int lvl14 = 13;
    	public static final int lvl15 = 14;
    	public static final int lvl16 = 15;
    	public static final int lvl17 = 16;
    	public static final int lvl18 = 17;
    	public static final int lvl19 = 18;
    	public static final int lvl20 = 19;
    }
    
    static private Context mContext = GameView.mContext;
    
    //Directions
    public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int UP = 3;
	public static final int NONE = 4;
	    
    public static final String TAG = "Android Game";
    
    
}
