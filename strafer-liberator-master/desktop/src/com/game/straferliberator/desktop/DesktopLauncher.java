package com.game.straferliberator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Strafer Liberator";
		config.width = com.port.WorldData.WIDTH;
		config.height = com.port.WorldData.HEIGHT;
		// config.fullscreen=true;
		config.pauseWhenMinimized = true;
		config.resizable = false;
		config.vSyncEnabled = true;

		new LwjglApplication(new StraferLiberator(), config);
	}
}
