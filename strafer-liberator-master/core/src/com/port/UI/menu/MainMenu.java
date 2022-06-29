package com.port.UI.menu;

import com.port.UI.buton.Buton;
import com.port.system.SaveSystem;
import com.port.utils.graphics.GifImage;
import com.port.world.WorldData;

import greenfoot.*;

public class MainMenu extends Menu {

	GifImage background = new GifImage("UI/menu/test2.gif");

	boolean butoanead = false;

	public GreenfootSound music;

	public MainMenu() {

		WorldData.PAUZA = true;
		setImage("UI/menu/titleScreen.png");
		WorldData.saveFileNumber = com.port.system.SaveSystem.getNumberOfSaveFiles() - 1;

	}

	private void addButoane() {
		getWorld().addObject(new Buton("Continue", this), 805, 225);
		getWorld().addObject(new Buton("New Game", this), 805, 380);

	}

	public void act() {
		setImage(background.getCurrentImage());
		getImage().scale(2400,1080);
		setLocation(300,900);

		if (!butoanead) {
			addButoane();
			butoanead = true;
			new Thread(new Runnable() {
				public void run() {
					music = new GreenfootSound("music/Rename.mp3");
					music.playLoop();
					music.setVolume(60);
				}
			}).start();
		}
	}

	public GreenfootSound getMusic() {
		return music;
	}

	public void setMusic(GreenfootSound music) {
		this.music = music;
	}
}
