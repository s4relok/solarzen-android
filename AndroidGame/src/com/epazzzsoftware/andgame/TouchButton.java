package com.epazzzsoftware.andgame;

import android.graphics.Rect;

public class TouchButton {
		
	public TouchButton(String name){
		this(S.getX(name),
				S.getY(name),
				S.getW(name),
				S.getH(name));
	}
	
	public TouchButton(String name, int i){						
		this(S.getX(name) + S.getW(name)*(i % 10),
				S.getY(name) + S.getH(name)*(i / 10),
				S.getW(name),
				S.getH(name));
	}	
	
	public TouchButton(int x, int y, int w, int h){
		mRect = new Rect(x, y, x+w, y+h);
	}
	
	public boolean isTouch (int x, int y){
		return mRect.contains(x, y);
	}

	public Rect mRect = null;
}
