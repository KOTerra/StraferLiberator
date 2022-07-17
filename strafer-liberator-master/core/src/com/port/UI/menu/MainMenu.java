package com.port.UI.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.button.Button;
import com.port.system.SaveSystem;
import com.port.utils.graphics.GifImage;
import com.port.utils.graphics.Picture;
import com.port.world.WorldData;

import greenfoot.*;


public class MainMenu extends Menu {

	AssetManager assetManager=StraferLiberator.assetManager;	
	
	
	GifImage background = assetManager.get("images/UI/menu/mainMenu/blur.gif",GifImage.class);
	Actor logo = new Actor();
	GifImage playerAnimationGif=assetManager.get("images/UI/menu/mainMenu/playerAnimation.gif",GifImage.class);
	Actor playerAnimation=new Actor();
	
	Button butonContinue = new Button("continue", this);
	Button butonNewGame = new Button("newGame", this);
	Button butonExit;
	
	
	List<Actor> thingsToRemove = new ArrayList<>();

	boolean addedStuff = false;

	public GreenfootSound music;

	public MainMenu() {

		WorldData.PAUZA = true;
		WorldData.saveFileNumber = com.port.system.SaveSystem.getNumberOfSaveFiles() - 1;

		logo.setImage(assetManager.get("images/UI/menu/mainMenu/logo.png", GreenfootImage.class));
		background.scale(WorldData.WIDTH, WorldData.HEIGHT);
	}

	private void addStuff() {
		getWorld().addObject(butonContinue, WorldData.WIDTH / 2 - 300, WorldData.HEIGHT - 100);
		getWorld().addObject(butonNewGame, WorldData.WIDTH / 2 + 300, WorldData.HEIGHT - 100);

		getWorld().addObject(logo, WorldData.WIDTH / 2, WorldData.HEIGHT / 2);
		getWorld().addObject(playerAnimation, WorldData.WIDTH/2,220);
		thingsToRemove.add(playerAnimation);
		thingsToRemove.add(logo);
		
		if(Gdx.app.getType().equals(ApplicationType.Desktop)) {
			butonExit=new Button("Exit",this);
			getWorld().addObject(butonExit, WorldData.WIDTH-100, WorldData.HEIGHT-100);
			thingsToRemove.add(butonExit);
		}
	}

	public List<Actor> getThingsToRemove() {
		return thingsToRemove;
	}

	public void act() {
		setImage(background.getCurrentImage());
		playerAnimation.setImage(playerAnimationGif.getCurrentImage());
		setLocation(300, WorldData.HEIGHT-135);
		if (!addedStuff) {
			addStuff();
			addedStuff = true;
			new Thread(new Runnable() {
				public void run() {
					music = assetManager.get("sounds/music/Rename.mp3",GreenfootSound.class);
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
