package com.game.straferliberator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Strafer Liberator";
		config.width = 1024;
		config.height = 576;
		//config.fullscreen=true;
		config.pauseWhenMinimized=true;
		config.resizable=false;
		new LwjglApplication(new StraferLiberator(), config);
	}
}
