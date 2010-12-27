package com.epazzzsoftware.solarzen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.epazzzsoftware.andgame.ActivityExtend;
import com.epazzzsoftware.andgame.GameManager;

public class SolarZen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            	
        
        ActivityExtend.defPackage = getResources().getString(R.string.defPackage);
        
        loadSettings();

        setContentView(R.layout.main);
    }

	private void loadSettings() {
		SharedPreferences settings = getPreferences(MODE_WORLD_READABLE);
        int level = settings.getInt("level", 0);
        MyGameManager.setUserLevel(level);
        boolean isOn = settings.getBoolean(IS_SOUND_ON, true);
        GameManager.setSoundSettings(isOn);
	}
    
    private void saveSettings() {
		SharedPreferences settings = getPreferences(MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(IS_SOUND_ON, GameManager.isSoundOn());
		editor.putInt("level", MyGameManager.getUserLevel());
		
	
	    // Don't forget to commit your edits!!!
	    editor.commit();
	}

	@Override
    protected void onStop() {
    	
    	saveSettings();
    	
    	super.onStop();
    	
    }

	@Override
    protected void onStart() {
    	super.onStart();
    	
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	
    }
    
    final static private String IS_SOUND_ON = "is_sound_on";
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    }
    
    @Override
    protected void onDestroy() {
    	
    	/*if(isFinishing())
    		MyGameView.killThreads();*/
    	
    	super.onDestroy();
    	
    }
    
    
        
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);        
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        case R.id.chg_launch:
            Planet.changeLaunchState();
            break;
        case R.id.quit:
            finish();
            break;
        }
        return true;
    }*/
    
}