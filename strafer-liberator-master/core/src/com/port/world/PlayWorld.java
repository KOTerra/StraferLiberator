package com.port.world;

import com.badlogic.gdx.graphics.Color;
import com.port.actor.Item;
import com.port.actor.npc.Npc;
import com.port.actor.npc.hostile.Droid;
import com.port.actor.npc.items.NpcItem;
import com.port.actor.player.Player;
import com.port.actor.player.items.Lantern;
import com.port.actor.player.items.Light;
import com.port.actor.utils.Inventory;
import com.port.display.Effect;
import com.port.display.HealthBar;
import com.port.display.HealthBarImg;
import com.port.display.HealthBarPlayer;
import com.port.display.MainMenu;
import com.port.display.MapMenu;
import com.port.display.Menu;
import com.port.display.Picture;
import com.port.display.Text;
import com.port.display.Tutorial;
import com.port.events.EventSystem;
import com.port.utils.Buton;
import com.port.utils.Dialog;
import com.port.utils.Scroller;

import greenfoot.*;
import greenfoot.actor.Actor;
import greenfoot.audio.GreenfootSound;
import greenfoot.display.GreenfootImage;
import greenfoot.world.World;

//lumea in care are loc toata simularea

public class PlayWorld extends World {

	int WIDE, HIGH;

	public static int originalX = 150, originalY = 200;

	public WorldListener worldListener;
	public EventSystem eventSystem;

	public Scroller scroller;

	Player player;

	public HealthBarPlayer healthBar;
	private boolean addedHealthBar = false;

	public GreenfootSound musicIdle;
	public GreenfootSound musicCombat;
	boolean musicLoaded = false;

	public PlayWorld() {
		super(WorldData.WIDTH, WorldData.HEIGHT, 1, false); // width, height, cellsize, daca sunt actorii restricted la
															// lume

		setPaintOrder(Buton.class, Menu.class, HealthBar.class, Text.class, Picture.class, //
				MapMenu.class, Tutorial.class, Dialog.class, //
				HealthBarImg.class, Effect.class, //
				Item.class, NpcItem.class, Lantern.class, Light.class, //
				Droid.class, Player.class, Npc.class);//

		WIDE = WorldData.WIDTH;
		HIGH = WorldData.HEIGHT;

		WorldData.loadWorldMatrices();

		addPlayer();
		WorldData.addedDialogs = false;
		addedHealthBar = false;
		addMainMenu();
		new Thread(new Runnable() {

			public void run() {
				musicIdle = new GreenfootSound("music/Default.mp3");
				musicCombat = new GreenfootSound("music/Combat.mp3");
				musicCombat.setVolume(45);
				musicIdle.setVolume(45);
				musicLoaded = true;
			}
		}).start();
		

	}

	private void addMainMenu() {
		addObject(new MainMenu(), WorldData.menuX, WorldData.menuY);

	}

	public void addPlayer() {
		GreenfootImage background = new GreenfootImage("map/worldSection/worldSection11.png");// imi pun fundalul
		setBackground(background);
		scroller = new Scroller(this, background, 8192, 8192);

		player = new Player();

		addObject(player, originalX, originalY);// pozitia pe newGame

		scroll();

		worldListener = new com.port.world.WorldListener(this);
		eventSystem = new EventSystem(this);

		addObject(worldListener, 1, 1);
		addObject(eventSystem, 1, 1);

	}

	Picture barBack = new Picture("UI/hud/healthBar.png");

	private void addHealthBar() {

		healthBar = new HealthBarPlayer("", "", player.getHp(), player.getHpMax());

		healthBar.setSafeColor(Color.TEAL.lerp(Color.SKY, 0.7f));
		healthBar.setDangerColor(Color.ORANGE);
		healthBar.setBarWidth(181);
		healthBar.setBarHeight(14);
		healthBar.setReferenceText("");
		healthBar.setTextColor(new Color(4, 69, 85, 214));

		addObject(barBack, 148, 40);
		addObject(healthBar, 172, 32);
	}

	void relocBar() {
		if (WorldData.PAUZA) {
			if (getObjects(Inventory.class).isEmpty()) {
				barBack.setLocation(-300, -100);
			}
		} else {
			barBack.setLocation(148, 40);
		}
	}

	// adauga obiectele pe toata mapa, nu doar pe suprafata de display
	public <T extends Actor> void initObject(T actor, int x, int y) {
		this.addObject(actor, x - Scroller.scrolledX, y - Scroller.scrolledY);
	}

	public <T extends Actor> void initUniqueObject(T actor, int x, int y) {
		if (this.getObjects(actor.getClass()).isEmpty()) {
			this.addObject(actor, x - Scroller.scrolledX, y - Scroller.scrolledY);
		}
	}

	/// scrolleaza lumea
	public void scroll() {
		if (player != null) {
			int dsX = (int) (player.getX() - WIDE / 2);
			int dsY = (int) (player.getY() - HIGH / 2);

			scroller.scroll(dsX, dsY);

		}
	}

	public Scroller getScroller() {
		return scroller;
	}

	public void act() {

		scroll();
		if (!addedHealthBar) {
			addHealthBar();
			addedHealthBar = true;
		}
		relocBar();
		if (musicLoaded) {
			updateMusic();
		}
		super.act();
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public EventSystem getEventSystem() {
		return eventSystem;
	}

	public Player getPlayer() {
		return player;
	}

	public HealthBar getHealthBar() {
		return healthBar;
	}

	public GreenfootSound getMusicIdle() {
		return musicIdle;
	}

	public GreenfootSound getMusicCombat() {
		return musicCombat;
	}

	public void updateMusic() {

		if (!WorldData.PAUZA) {

			if (!musicIdle.isPlaying()) {
				if (WorldData.isWalking && !WorldData.isFighting) {
					musicIdle.play();
				}
			}
			if (!musicCombat.isPlaying()) {
				if (WorldData.isFighting) {
					musicCombat.play();
					musicIdle.pause();
				}
			}

			if (musicCombat.isPlaying()) {
				if (!WorldData.isFighting) {
					musicCombat.pause();
				}
			}
		} else {
			if (musicIdle.isPlaying()) {
				musicIdle.pause();
			}
			if (musicCombat.isPlaying()) {
				musicCombat.pause();
			}

		}
	}

}