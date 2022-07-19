package com.port.entity.mover.npc.hostile;

import greenfoot.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.npc.LaserDroid;
import com.port.entity.item.player.BlackHole;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.Sword;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.AnimationRunner;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Droid extends HostileNpc {

	private String axa;
	private int dist;
	private int distParc;
	private boolean sensPoz = true;
	int gX, gY;
	GifImage img = StraferLiberator.assetManager.get("images/npc/inamic/droid/droid.gif", GifImage.class);

	public static int speed = 3;
	private int hp = 75;
	private int mass = 60;
	private final int pauza = WorldData.FPS;

	private long timpLaser = 0;
	private boolean mort = false;
	Animation animation;
	private AnimationRunner animationRunner;
	boolean startedAnimation = false;
	int cntDeath = 0;

	private long timpSab = 0;
	private long timpBolt = 0;

	public String gifLaser;

	public Droid(Scroller scrl, int x, int y, String xy, int dist) {
		super(scrl, x, y);
		this.dist = dist;
		distParc = 0;
		this.axa = xy;
		gX = x;
		gY = y;
		sensPoz = true;

		changeAnimation();
		animationRunner.setActiveState(false);
	}

	protected void gaseste() {

		Player player = ((PlayWorld) getWorld()).getPlayer();
		if (player != null) {
			turnTowards(player.getX(), player.getY());

		}
	}

	protected void move() {
		lovitSabie();
		lovitLaser();
		if (axa.equals("ox")) {
			int difpx = Scroller.scrolledX - prevsx;
			int difpy = Scroller.scrolledY - prevsy;

			worldX -= difpx;
			worldY -= difpy;
			super.move();

			if (sensPoz == true) {
				// sens pozitiv ,cresc X
				worldX += speed;
				distParc += speed;
				if (dist - distParc <= 0) {
					sensPoz = false;
					distParc = 0;
				}
			} else {
				worldX -= speed;
				distParc += speed;
				if (dist - distParc <= 0) {
					sensPoz = true;
					distParc = 0;
				}
			}

		} else if (axa.equals("oy")) {

			if (sensPoz == true) {
				// sens pozitiv ,cresc Y
				worldY += speed;
				distParc += speed;
				if (dist - distParc <= 0) {
					sensPoz = false;
					distParc = 0;
				}
			} else {
				worldY -= speed;
				distParc += speed;
				if (dist - distParc <= 0) {
					sensPoz = true;
					distParc = 0;
				}
			}

		}
		setLocation(worldX, worldY);
		if (timpLaser > pauza) {

			getWorld().addObject(new LaserDroid(), getX(), getY());
			timpLaser = 0;

		}
		timpLaser++;

	}

	protected void lovitSabie() {
		super.lovitSabie(this.mass);
		if (isTouching(Sword.class)) {
			timpSab++;
			if (timpSab >= 15) {
				takeDamage(Sword.damage);
				timpSab = 0;
			}

		}

	}

	protected void lovitLaser() {
		super.lovitLaser();
		Actor a = (Laser) getOneIntersectingObject(Laser.class);
		if (a != null) {
			timpBolt++;// cat timp ating ating laserul
			if (timpBolt >= 5) {
				removeTouching(Laser.class);
				takeDamage(Laser.damage);
				timpBolt = 0;
			}
		}
	}

	private void suckedBlackHole() {
		if (isTouching(BlackHole.class)) {
			takeDamage(10);
		}
	}

	private void takeDamage(int dmg) {
		hp -= dmg;
	}

	private void die() {
		if (hp <= 0) {
			mort = true;
		}
	}

	public void act() {

		if (WorldData.PAUZA == false && super.checkPlayerInChunck() == true && !freeze) {

			if (mort == true) {
				cntDeath++;
				if (cntDeath > 2) {
					if (!startedAnimation) {
						animationRunner.setActiveState(true);
						startedAnimation = true;
					}
				}
				if (animationRunner.isActive()) {
					animationRunner.run();
				}
				if (startedAnimation && !animationRunner.isActive()) {
					super.generateRandomHealthBoost();
					getWorld().removeObject(this);
				}
			} else {

				lovitSabie();
				lovitLaser();
				suckedBlackHole();
				die();

				timpAtins = 0;// {
				atingePlayer = true;// ataca

				// daca e in range sa nu l caute in toata lumea
				List players = getWorld().getObjects(Player.class);
				Player player = (Player) players.get(0);

				move();

				int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
				if (deltaPGX < 0) {
					deltaPGX *= (-1);
				}
				int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
				if (deltaPGY < 0) {
					deltaPGY *= (-1);
				}
				if (deltaPGX <= WorldData.WIDTH && deltaPGY <= WorldData.HEIGHT) {
					if (!WorldData.metDroid && WorldData.nrEvent <= 7) {
						getWorld().addObject(new Tutorial("Combat", "Droid", 2, false), WorldData.menuX,
								WorldData.menuY);
						WorldData.metDroid = true;
					}
					// aici intra functia de move
					gaseste();

				}
				int difpx = Scroller.scrolledX - prevsx;
				int difpy = Scroller.scrolledY - prevsy;

				worldX -= difpx;
				worldY -= difpy;
				prevsx = Scroller.scrolledX;
				prevsy = Scroller.scrolledY;

				this.knockbackMove();
			}
			if (animationRunner.isActive()) {
				animationRunner.run();
			} else {
				setImage(img.getCurrentImage());
			}
		}

	}

	private void changeAnimation() {
		animation = StraferLiberator.assetManager.get("images/npc/inamic/droid/droid_death.gif", Animation.class);
		animationRunner = new AnimationRunner(this, animation);

		animationRunner.run();
		animationRunner.setActiveState(true);
	}

	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
}
