package com.port.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.game.straferliberator.StraferLiberator;
import com.game.straferliberator.render.PaintUtilities;
import com.port.UI.buton.Buton;
import com.port.UI.hud.HealthBar;
import com.port.UI.hud.HealthBarImg;
import com.port.UI.hud.HealthBarPlayer;
import com.port.UI.hud.Inventory;
import com.port.UI.menu.Dialog;
import com.port.UI.menu.MainMenu;
import com.port.UI.menu.MapMenu;
import com.port.UI.menu.Menu;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.Item;
import com.port.entity.item.npc.NpcItem;
import com.port.entity.item.player.Lantern;
import com.port.entity.item.player.Light;
import com.port.entity.mover.npc.Npc;
import com.port.entity.mover.npc.hostile.Droid;
import com.port.entity.mover.player.Player;
import com.port.system.event.EventSystem;
import com.port.utils.graphics.Transition;
import com.port.utils.graphics.Picture;
import com.port.utils.graphics.Text;

import greenfoot.*;

//lumea in care are loc toata simularea

public class PlayWorld extends World {

	int WIDE, HIGH;

	public static int originalX = 150, originalY = 200;

	public WorldListener worldListener;
	public EventSystem eventSystem;

	public Scroller scroller;
	public AssetManager assetManager=StraferLiberator.assetManager;
	
	Player player;

	public HealthBarPlayer healthBar;
	private boolean addedHealthBar = false;
	HealthBarImg barBack = new HealthBarImg("Player");
	
	public GreenfootSound musicIdle;
	public GreenfootSound musicCombat;
	boolean musicLoaded = false;

	public PlayWorld() {
		super(WorldData.WIDTH, WorldData.HEIGHT, 1, false); // width, height, cellsize, daca sunt actorii restricted la
															// lume

		PaintUtilities.setPaintOrder(PaintUtilities.paintOrder);//sets the paint order to the standard one

		WIDE = WorldData.WIDTH;
		HIGH = WorldData.HEIGHT;

		WorldData.loadWorldMatrices();

		addPlayer();
		WorldData.addedDialogs = false;
		addedHealthBar = false;
		addMainMenu();
		
		//puts the music on a new Thread 
		new Thread(new Runnable() {

			public void run() {
				musicIdle = 
				musicCombat = assetManager.get("sounds/music/Combat.mp3",GreenfootSound.class);
				musicCombat.setVolume(45);
				musicIdle.setVolume(45);
				musicLoaded = true;
			}
		}).start();
		

	}

	private void addMainMenu() {
		addObject(new MainMenu(),300, WorldData.HEIGHT-135);

	}

	public void addPlayer() {
		GreenfootImage background = assetManager.get("images/map/worldSection/worldSection11.png",GreenfootImage.class);// imi pun fundalul
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



	private void addHealthBar() {

		healthBar = new HealthBarPlayer("", "", player.getHp(), player.getHpMax());

		healthBar.setSafeColor(Color.TEAL.lerp(Color.SKY, 0.7f));
		healthBar.setDangerColor(Color.ORANGE);
		healthBar.setBarWidth(179);
		healthBar.setBarHeight(12);
		healthBar.setReferenceText("");
		healthBar.setTextColor(new Color(4, 69, 85, 214));

		
		addObject(healthBar, 172, 33);
		addObject(barBack, 148, 40);
	}

	void relocBar() {
		if (WorldData.PAUZA) {
			if (getObjects(Inventory.class).isEmpty()) {
				barBack.setLocation(-300, -100);
			}
		} else {
			barBack.setLocation(148, 40);
		}
		if(barBack.getWorld()!=this) {
			addObject(barBack,148,40);
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
