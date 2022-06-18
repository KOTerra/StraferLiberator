package com.game.straferliberator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;

public class DesktopLauncher {
	public static LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	public static void main(String[] arg) {
		
		config.title = "Strafer Liberator";
		config.width = com.port.WorldData.WIDTH;
		config.height = com.port.WorldData.HEIGHT;
		
		config.pauseWhenMinimized = true;
		config.resizable = false;
		config.vSyncEnabled = true;
//config.fullscreen=true;
		new LwjglApplication(new StraferLiberator(), config);
	}
}
