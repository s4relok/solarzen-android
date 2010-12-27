package com.epazzzsoftware.solarzen;

public class CosTarget extends SlideXTarget {

	public CosTarget(int x, int h, int speed) {
		super(x, h, speed);
		
		heigth = h;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {		
		
		nextX();
		nextY();
		
		updateRect();
	}
	
	@Override
	protected void nextY() {
	
		y = (int) Math.abs( Math.round(  heigth*Math.cos( Math.toRadians(x) ) ) ) + 20;
	}

	private int heigth;
}
