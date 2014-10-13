package com.ketphish.spheredefense.android;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ketphish.spheredefense.SphereDefenseGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = 
				new AndroidApplicationConfiguration();
		setWindowProperties();
		initialize(new SphereDefenseGame(), config);
	}

	private void setWindowProperties() {
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
}
