package com.game.straferliberator.desktop;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;

public class DesktopLauncher {
	public static LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	public static void main(String[] arg) {
		
		config.title = "Strafer Liberator";
		config.width = com.port.world.WorldData.WIDTH;
		config.height = com.port.world.WorldData.HEIGHT;
		
		config.pauseWhenMinimized = true;
		config.resizable = false;
		config.vSyncEnabled = true;
		config.foregroundFPS=90;
		
		config.addIcon("assets/icons/icon_128.png", FileType.Internal);
		config.addIcon("assets/icons/icon_64.png", FileType.Internal);
		config.addIcon("assets/icons/icon_32.png", FileType.Internal);
		config.addIcon("assets/icons/icon_16.png", FileType.Internal);
//config.fullscreen=true;
	
		
		new LwjglApplication(new StraferLiberator(), config);
		
	}
}
