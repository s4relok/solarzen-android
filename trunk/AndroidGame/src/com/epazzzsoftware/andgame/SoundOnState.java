package com.epazzzsoftware.andgame;

import java.io.IOException;
import java.util.HashMap;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

public class SoundOnState implements SoundState {

	public SoundOnState(int num) {
		numChannels = num;		
		init();
		
	}
	
	
	/**
	 * @param loop 
	 * loop mode (0 = no loop, -1 = loop forever)
	 *
	 */
	@Override
	public void play(int id, int loop) {
		
		/*AudioManager mgr = (AudioManager) GameView.mContext.getSystemService(Context.AUDIO_SERVICE);
        int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);*/
		
        while(soundPool.play(soundPoolMap.get(id), 0.8f, 0.8f, 1, loop, 1f)==0);

	}
	
	public void release() 
    { 
        if (soundPool != null) 
        { 
            Log.d(TAG, "Closing SoundPool"); 
            stop();
            soundPool.release();
            
            soundPool = null; 
            Log.d(TAG, "SoundPool closed"); 
             
        } 
        
        Log.d(TAG, "Closing MediaPlayer's"); 
        for (MediaPlayer mp : mediaMap.values()) {
        	
        	if(mp.isPlaying())
        		mp.stop();
        	
			mp.release();
		}
        Log.d(TAG, "MediaPlayer's closed");
    } 
	
	public void stop(){
		
		if(soundPool == null) return;
		
		for (Integer i : soundPoolMap.keySet()) {
			soundPool.stop(soundPoolMap.get(i));
		}
	}	
	
	public void pause(){
		
		if(soundPool == null) return;
		
		for (Integer i : soundPoolMap.keySet()) {
			soundPool.pause(soundPoolMap.get(i));
		}
	}
	
	public void resume(){
		
		if(soundPool == null) return;
		
		for (Integer i : soundPoolMap.keySet()) {
			soundPool.resume(soundPoolMap.get(i));
		}
	}

	
	
	
    private void init() {
    	
         soundPool = new SoundPool(numChannels, AudioManager.STREAM_MUSIC, 100);
         soundPoolMap = new HashMap<Integer, Integer>();  
         
         mediaMap = new HashMap<Integer, MediaPlayer>();
    }
    
    protected void addSound(int id){
    	soundPoolMap.put(id, soundPool.load(GameView.mContext, id, 1));
    }
    
    protected void addMediaPlayer(int id){
    	mediaMap.put(id, MediaPlayer.create(GameView.mContext, id));
    }
    
    
    private int numChannels;
	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundPoolMap; 
	
	private HashMap<Integer, MediaPlayer> mediaMap;
	
	static final String TAG = "SoundSystem";

	@Override
	public void playMediaPlayer(int id, int loop) {
		
		mediaMap.get(id).setLooping(loop == -1);
		mediaMap.get(id).start();
		
	}


	@Override
	public void releaseMediaPlayer(int id) {
		
		mediaMap.get(id).release();
		
	}


	@Override
	public void releaseSound(int id) {
		
		soundPool.unload(soundPoolMap.get(id));
		
	}


	@Override
	public void stopMediaPlayer(int id) {
		
		mediaMap.get(id).stop();
		try {
			mediaMap.get(id).prepare();
			mediaMap.get(id).seekTo(0);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "Error MediaPlayer prepare"); 
		}
		
	}


	@Override
	public void pauseMediaPlayer(int id) {
		mediaMap.get(id).pause();
		
	}
	
	public MediaPlayer getMediaPlayer(int id) {
		return mediaMap.get(id);		
	}

}
