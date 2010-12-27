package com.epazzzsoftware.solarzen;


public class SlideXTarget extends Target {

	public SlideXTarget(int x, int y, int speed) {
		super(x, y);
		_speed = speed;
		
	}
	
	@Override
	public void update() {
		
		nextX();		
		updateRect();
		
	}
	
	protected void updateRect(){
		mRect.set(x-G.rTarget, y-G.rTarget,
				x+G.rTarget, y+G.rTarget);
	}
	
	protected void nextX() {
		
		if(_speed>0)
			x = x != G.SCREEN_WIDTH+G.SUN_R ? x+_speed : -G.SUN_R;
		else
			x = x!= -G.SUN_R ? x+_speed : G.SCREEN_WIDTH+G.SUN_R;
		
	}
	
	protected void nextY() {

		
	}
	
	private int _speed;
	
}
