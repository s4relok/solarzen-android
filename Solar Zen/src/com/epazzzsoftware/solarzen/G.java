package com.epazzzsoftware.solarzen;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import com.epazzzsoftware.andgame.GameView;
import com.epazzzsoftware.andgame.S;

public class G {
	
	static void init(){
		bmp.init();
	}
	
	public static final int MAX_LENGTH = 3000;
	
	// game 
	public static final int LEVELS = 15;

	// screen
	public static final int SUN_R = 32;
	public static final int SUNB_R = 53;
	public static final int SCREEN_WIDTH = 320;
	public static final int SCREEN_HEIGTH = 480;	
	public static final int X2 = SCREEN_WIDTH/2;
	public static final int X2MENU = X2-24;
	public static final int Y2 = SCREEN_HEIGTH/2;
	public static final double TG_LIMIT = (float)X2/(float)Y2;
	public static final Rect SCREEN_RECT = new Rect(0,0, SCREEN_WIDTH, SCREEN_HEIGTH);
	
	// coordinates
	public static final Point SOUND_XY = new Point(SCREEN_WIDTH-50, 20);
	public static final Point BACK_GAME_XY = new Point(0, 10);
	
	// follower
	public static final int FONT_SIZE = 16;
	public static final int FOLLOWER_R = 5;
	public static final int FOLLOWER_TEXT_SHIFT = 10;
	public static final int FOLLOWER_TEXT_INDENT = 25;
	
	// planet
	public static final double g = 37;
	public static final int rPlanet = 30;
	public static final int rTarget = 27;
	public static final Rect startRect = new Rect(50, 320, 270, 480);
	public static final int numInCarousel = 5;
	
	// speed
	public static final double SPEED_DIVIDER  = 14;
	public static final double SPEED_MIN_DIVIDER  = 15;
	
	
	// arrow
	public static final int ARROW_LEN  = 223;
	public static final int ARROW_MIN_LEN  = 70;
	public static final int ARROW_WID2  = 16;
	public static final int ARROW_LEN1  = 153;
	public static final int ARROW_WID1  = 30;
	public static final int ARROW_LEN3  = 67;
	public static final int ARROW_WL  = 3;
	public static final int ARROW_OPACITY  = 65;
	public static final int SHIFT_UP_POW_TXT  = 70;
	public static final int SAMPLE_1_7  = (ARROW_LEN - ARROW_MIN_LEN)/7;
	
	
	// starter
	public static final int STR_C_X  = 161;
	public static final int STR_C_Y  = 429;
	public static final int STR_R  = 96;
	public static final int BBL_R  = 33;
	public static final int HANDLE_R  = 14;
	public static final int[] BBL_POS = {96, 40, 149, 79, 129, 142, 63, 142, 43, 79};
	
	// music 
	
	public static final int[][] RandomMusicSets = {
		{0,1,2}, {0,2,1}, {1,0,2}, {1,2,0}, {2,0,1}, {2,1,0}
	};
	
	// colors
	public static final int[] COLORS = {
		R.color.planet_1,
		R.color.planet_2,
		R.color.planet_3,
		R.color.planet_4,
		R.color.planet_5,
		R.color.planet_6,
		R.color.planet_7,
		R.color.planet_8,
		R.color.planet_9,
		R.color.planet_10,
		R.color.planet_11,
		R.color.planet_12,
		R.color.planet_13,
		R.color.planet_14,
		R.color.planet_15};
	
	// zen quotes
	public static final String[] QUOTES = {
		GameView.mContext.getString(R.string.zen01),
		GameView.mContext.getString(R.string.zen02),
		GameView.mContext.getString(R.string.zen03),
		GameView.mContext.getString(R.string.zen04),
		GameView.mContext.getString(R.string.zen05),
		GameView.mContext.getString(R.string.zen06),
		GameView.mContext.getString(R.string.zen07),
		GameView.mContext.getString(R.string.zen08),
		GameView.mContext.getString(R.string.zen09),
		GameView.mContext.getString(R.string.zen10),
		GameView.mContext.getString(R.string.zen11),
		GameView.mContext.getString(R.string.zen12),
		GameView.mContext.getString(R.string.zen13),
		GameView.mContext.getString(R.string.zen14),
		GameView.mContext.getString(R.string.zen15),};

	public static final int FONT_SIZE_QUTES = 24;
	
	// levels	
	public static final Target[][] LEVELS_TARGETS = {
		
		// 1
		{new Target(X2, 100)},
		
		// 2 
		{new Target(X2-100, 100), new Target(X2+100, 100)},
			
			// 3
			{new Target(X2, 100), new CircleTarget(X2, 100, 72, 0.05f)},
			
			// 4
			{new Target(X2-100, 100+70), new Target(X2+100, 100-70), new Target(X2, 100)},
			
			// 5
			{new CircleTarget(X2+50, 100, 60, -0.05f), 
			new CircleTarget(X2-50, 100, 60, 0.05f)},
				
			// 6
			{new CircleTarget(X2+50, 80, 40, 0.05f), 
			new CircleTarget(X2-50, 80, 40, 0.05f),},

			// 7
			{new SlideXTarget(50, 40, 1), 
			new SlideXTarget(90, 100, 2),
			new SlideXTarget(130, 160, 3),
			},
			
			// 8
			{new CircleTarget(X2+50, 100, 60, 0.05f), 
				new CircleTarget(X2-50, 100, 60, 0.05f),
				new CircleTarget(X2, 150, 60, -0.05f),
			},
			
			// 9
			{new CircleTarget(X2, 100, 90, 0.05f), 
				new SlideXTarget(50, 40, 1),
				new SlideXTarget(SCREEN_WIDTH-50, 160, -1),
			},
			
			// 10
			{new Target(X2, 100), 
				new CircleTarget(X2, 100, 67, -0.05f),
				new CircleTarget(X2, 100, 100, +0.05f),
				new SlideXTarget(50, 190, 1),
			},

			// 11
			{new SlideXTarget(50, 40, 1), 
			new SlideXTarget(90, 100, 2),
			new SlideXTarget(130, 160, 3),
			new CircleTarget(X2, Y2-21, 90, 0.1f),
			},
			
			// 12
			{new SlideXTarget(50, 40, 1), 
			new SlideXTarget(90, 100, 2),
			new SlideXTarget(130, 160, 3),
			new SlideXTarget(X2, 70, -4),
			new SlideXTarget(X2, 130, 4),
			},
			
			// 13
			{new CircleTarget(40, 70, 10, -0.4f), 
				new CircleTarget(SCREEN_WIDTH-40, 170, 10, 0.4f),
				new CircleTarget(X2, 100, 70, +0.05f), 
				new CircleTarget(X2, 100, 70, +0.05f, (float) ((2*Math.PI)/3)),
				new CircleTarget(X2, 100, 70, +0.05f, (float) (2*(2*Math.PI)/3)),
			},
			
			// 14
			{new CircleTarget(X2+50, 100, 60, -0.05f), 
			new CircleTarget(X2-50, 100, 60, 0.05f),
			new SlideXTarget(50, 40, 1),
			new SlideXTarget(SCREEN_WIDTH-50, 160, -1),},
			
			// 15
			{new CircleTarget(SCREEN_WIDTH-70, 70, 20, -0.2f), 
				new CircleTarget(SCREEN_WIDTH-70, 70, 20, -0.2f, (float)Math.PI ),
				new CircleTarget(70, 170, 20, 0.2f),
				new CircleTarget(70, 170, 20, 0.2f, (float)Math.PI),
			new CosTarget(SCREEN_WIDTH-50, 40, 2),
			new CosTarget(50, 160, -2),},
			
			
			
			// 16*
			{new CircleTarget(70, 70, 20, -0.1f), 
			new CircleTarget(SCREEN_WIDTH-70, 170, 20, 0.1f),
			new CosTarget(50, 40, 1),
			new CosTarget(SCREEN_WIDTH-50, 160, -1),},
			
		};
	
		
	public static final int[] RandomMUSIC = {
		R.raw.dance, R.raw.arab, R.raw.waltz};
		
	public static final class bmp {
		static void init(){}
        public static final Bitmap menu_background = S.getBitmap("menu_background");
        
        public static final Bitmap menu_text = S.getBitmap("menu_text");
        public static final Bitmap menu_text_out = S.getBitmap("menu_text_out");
        public static final Bitmap text_continue = S.getBitmap("text_continue");
        
        public static final Bitmap menu_text_abt = S.getBitmap("menu_text_abt");
        public static final Bitmap start = S.getBitmap("start");
        //public static final Bitmap background480 = S.getBitmap("background480");
        public static final Bitmap play_area480 = S.getBitmap("play_area480");
        public static final Bitmap sun = S.getBitmap("sun");
        public static final Bitmap[] planetsss = S.getBitmaps("planet_%d", 1, LEVELS);
        public static final Bitmap[] planets_disabled = S.getBitmaps("planet_%d_disabled", 1, LEVELS);
        public static final Bitmap[] help_pages = S.getBitmaps("help%d", 1, 5);
        
        
        public static final Bitmap str = S.getBitmap(R.drawable.str);
        public static final Bitmap bbl_in_str = S.getBitmap(R.drawable.bbl_in_str);
        public static final Bitmap background = S.getBitmap(R.drawable.background);
        public static final Bitmap handle = S.getBitmap(R.drawable.handle);
        public static final Bitmap target = S.getBitmap(R.drawable.target);
        public static final Bitmap sunb = S.getBitmap(R.drawable.sunb);
        public static final Bitmap sound_on = S.getBitmap(R.drawable.sound_on);
        public static final Bitmap sound_off = S.getBitmap(R.drawable.sound_off);
        public static final Bitmap blackhole_back = S.getBitmap(R.drawable.blackhole_back);
        //public static final Bitmap blackhole_practice = S.getBitmap(R.drawable.blackhole_practice);
        public static final Bitmap text_back = S.getBitmap(R.drawable.text_back);
        public static final Bitmap text_next = S.getBitmap(R.drawable.text_next);
        public static final Bitmap text_previous = S.getBitmap(R.drawable.text_previous);
    }    
}
