package com.game.straferliberator.android;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;


import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.game.straferliberator.StraferLiberator;
import com.port.world.WorldData;
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WorldData.runOnAndroid=true;
		DisplayMetrics screenDimensions= Resources.getSystem().getDisplayMetrics();
		WorldData.setResolution(screenDimensions.widthPixels, screenDimensions.heightPixels);
		
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
        config.useWakelock = true;

		initialize(new StraferLiberator(), config);
	}
}
