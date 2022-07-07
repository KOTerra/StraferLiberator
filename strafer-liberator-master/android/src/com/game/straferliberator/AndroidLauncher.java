package com.game.straferliberator;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;
import com.port.world.WorldData;

/*Entry point pt android
 * */
public class AndroidLauncher extends AndroidApplication {
	@Override

	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		WorldData.runOnAndroid=true;
		DisplayMetrics screenDimensions= Resources.getSystem().getDisplayMetrics();
		WorldData.setResolution(screenDimensions.widthPixels, screenDimensions.heightPixels);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new StraferLiberator(), config);
	}
}
