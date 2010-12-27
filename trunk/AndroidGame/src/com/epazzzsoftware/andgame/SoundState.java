package com.epazzzsoftware.andgame;

import android.media.MediaPlayer;

public interface SoundState {

	public void play(int id, int loop);	
	public void playMediaPlayer(int id, int loop);
	public void stopMediaPlayer(int id);
	public void pauseMediaPlayer(int id);
	
	public void releaseSound(int id);
	public void releaseMediaPlayer(int id);
	
	public MediaPlayer getMediaPlayer(int id);
	
	public void release();
	
	
	
	
}
