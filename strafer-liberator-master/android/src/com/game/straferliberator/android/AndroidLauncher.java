package com.game.straferliberator.android;

import android.os.Bundle;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
        config.useWakelock = true;

		initialize(new StraferLiberator(), config);
	}
}
