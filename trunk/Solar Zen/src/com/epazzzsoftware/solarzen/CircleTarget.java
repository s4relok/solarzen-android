package com.epazzzsoftware.solarzen;

public class CircleTarget extends Target {

	public CircleTarget(int x, int y, int radius, float wd) {
		super(x, y);
		xc = x;
		yc = y;
		r = radius;
		w = wd;
		// update
		set(x-r, y);
		mRect.set(x-G.rTarget, y-G.rTarget,
				x+G.rTarget, y+G.rTarget);
	}
	
	public CircleTarget(int x, int y, int radius, float wd, float wbegin) {
		this(x, y, radius, wd);
		
		angle = wbegin;
	}
	
	@Override
	public void update() {
		
		angle+=w;
		
		if(angle == 360)
			angle = 0;
				
		nextX();
		nextY();
						
		mRect.set(x-G.rTarget, y-G.rTarget,
				x+G.rTarget, y+G.rTarget);
	}

	private void nextX() {
		x = (int) (xc + r*Math.cos(angle));
	}
	
	private void nextY() {
		y = (int) (yc + r*Math.sin(angle));
	}
	

	private float w;
	private float angle = 0;
	private int r;
	private int xc;
	private int yc;

}
