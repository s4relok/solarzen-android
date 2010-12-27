package com.epazzzsoftware.solarzen;

import com.epazzzsoftware.andgame.SoundOnState;

public class SoundOn extends SoundOnState {

	public SoundOn(int num) {
		super(num);
		
		for (int i = 0; i < G.RandomMUSIC.length; i++) {
			addMediaPlayer(G.RandomMUSIC[i]);
		}
		
		addSound(R.raw.menu_out);
		addSound(R.raw.pln_dst_bypln);
		addSound(R.raw.lvl_ends);
		addSound(R.raw.menu_mark);
		
				
	}
	
	

}
