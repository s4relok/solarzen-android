package com.epazzzsoftware.solarzen;

import com.epazzzsoftware.andgame.GameObject;

import android.graphics.Canvas;

public class TargetList implements GameObject {
	
	public TargetList(Target ... targets) {
		mTargets = targets;
		
	}
	
	@Override
	public void doDraw(Canvas c) {
		for (int i = 0; i < mTargets.length; i++) {
			mTargets[i].doDraw(c);
		}
		
	}
	
	public boolean checkVictory(Object o[]){
		int matches = 0;
		
		for (int i = 0; i < o.length; i++) {
			Planet p = (Planet) o[i];
			
			if(G.SCREEN_RECT.contains(p.x-G.rTarget, p.y-G.rTarget,
					p.x+G.rTarget, p.y+G.rTarget)){
				
				for (int j = 0; j < mTargets.length; j++) {
					
					if(mTargets[j].isCross(p)){
						matches++;
						break;
					}						
					
				}
			
			}
			
		}
		
		if(matches == mTargets.length)
			return true;
		else
			return false;
		
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
		
		for (int i = 0; i < mTargets.length; i++) {
			mTargets[i].setCrossed(false);
			mTargets[i].update();
		}
	}

	private Target[] mTargets;

}
