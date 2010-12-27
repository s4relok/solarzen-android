/**
 * 
 */
package com.epazzzsoftware.solarzen;

import java.util.Random;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.SurfaceHolder;

import com.epazzzsoftware.andgame.GameManager;
import com.epazzzsoftware.andgame.GameState;
import com.epazzzsoftware.andgame.S;
import com.epazzzsoftware.andgame.SoundOff;

/**
 * @author s4relok
 *
 */
public class MyGameManager extends GameManager {

	MyGameManager(SurfaceHolder surfaceHolder, Context context) {
		// TODO: Delay (17) must be eliminated
		super(surfaceHolder, context, 0);
		
		
		
		Random r = new Random();
		_musicSet = G.RandomMusicSets[r.nextInt(6)];
		_curTrack = 0;
		
						
		mSoundOnState = new SoundOn(10);
		
		
		
		setSoundOn(GameManager.isSoundOn());
		
		startMusic();
				
		super.setGameState(new MenuState(this));
		
	}
	
	
	
	@Override
	public void setGameState(GameState gs) {		
		
		getSoundState().play(R.raw.menu_mark, 0);
		super.setGameState(gs);
		
	}
	
	public void switchSoundState(){
		
		pauseMusic();
		
		if(getSoundState() instanceof SoundOff){
			setSoundOn(true);
		}
		else {
			setSoundOn(false);
		}
		
		playMusic();
	}
	
	private void pauseMusic(){
		getSoundState().pauseMediaPlayer(G.RandomMUSIC[_musicSet[_curTrack]]);
		//
	}
	
	private void playMusic(){
		//
		getSoundState().playMediaPlayer(G.RandomMUSIC[_musicSet[_curTrack]], 0);
	}
	
	private void nextTrack(){
		 _curTrack++;
		_curTrack = _curTrack == 2 ? 0 : _curTrack;
	}
	
	@Override
	public void nextLevel() {
		
		if(super.getLevel()!= S.level.lvl15)
			super.nextLevel();
	
	}
	
	private void onTrackFinish(){
		nextTrack();
		startMusic();
	}
	
	private void startMusic(){
		
		mSoundOnState.getMediaPlayer(G.RandomMUSIC[_musicSet[_curTrack]]).setOnCompletionListener(new OnCompletionListener(){

			@Override
			public void onCompletion(MediaPlayer mp) {
				onTrackFinish();
				
			}
			
		});
		
		getSoundState().playMediaPlayer(G.RandomMUSIC[_musicSet[_curTrack]], 0);
		
	}
		
	static private int USER_LEVEL = S.level.lvl1;
	
	static public int getUserLevel(){
		return USER_LEVEL;
	}

	
	static public void setUserLevel(int level){
		USER_LEVEL = level;
	}
	
	private int[] _musicSet;
	private int _curTrack;
	

}
