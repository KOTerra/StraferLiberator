package com.game.straferliberator;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;


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

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			Window applicationWindow = getApplicationWindow();
			WindowManager.LayoutParams attrib = applicationWindow.getAttributes();
			attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
		}

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
	//	config.useImmersiveMode=true;

		DisplayMetrics screenDimensions= Resources.getSystem().getDisplayMetrics();
		WorldData.setResolution(screenDimensions.widthPixels, screenDimensions.heightPixels);


		initialize(new StraferLiberator(), config);
	}
}
