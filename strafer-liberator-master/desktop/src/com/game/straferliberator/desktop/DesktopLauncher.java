package com.game.straferliberator.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.straferliberator.StraferLiberator;
import com.port.world.WorldData;

/*
 * Entry point pt platforme desktop
 * */
public class DesktopLauncher {
	public static Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

	public static void main(String[] arg) {
		WorldData.runOnDesktop=true;
		Dimension screenDimensions=Toolkit.getDefaultToolkit().getScreenSize();
		WorldData.setResolution(screenDimensions.width, screenDimensions.height);
		
		config.setTitle( "Strafer Liberator");
		config.setWindowIcon(FileType.Internal, "icons/icon_128.png","icons/icon_64.png","icons/icon_32.png","icons/icon_16.png");
		
		config.setDecorated(false);
		config.setWindowedMode(WorldData.WIDTH, WorldData.HEIGHT);
		config.setResizable(false);
		
		

		config.setForegroundFPS(WorldData.FPS);
		config.useVsync(true);


		new Lwjgl3Application(new StraferLiberator(), config);

	}
}
