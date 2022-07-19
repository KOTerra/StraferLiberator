package com.port.entity.mover.npc.hostile;

import greenfoot.*;

import java.util.List;

import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.npc.TaserDolpatian;
import com.port.entity.item.player.BlackHole;
import com.port.entity.item.player.Laser;
import com.port.entity.item.player.Light;
import com.port.entity.item.player.Sword;
import com.port.entity.mover.player.BasePlayer;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.AnimationRunner;
import com.port.utils.graphics.GifImage;
import com.port.world.Scroller;
import com.port.world.WorldData;

public class Dolpatian extends Goblin {

	boolean eVizibil = false;
	public static int mass = 100;
	int hp = 75;

	private long timpSab = 0;
	private long timpBolt = 0;

	Animation animation;
	private AnimationRunner animationRunner;
	boolean startedAnimation = false;
	int cntDeath = 0;

	public Dolpatian(Scroller scrl, int x, int y) {
		super(scrl, x, y);

		eVizibil = false;
		// am gif-urile schimbate

		directie.put("D",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_m_D.gif", GifImage.class));
		directie.put("W",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_m_W.gif", GifImage.class));
		directie.put("A",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_m_A.gif", GifImage.class));
		directie.put("S",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_m_S.gif", GifImage.class));
		directie.put("idle",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_m_S.gif", GifImage.class));
		directie.put("inviz",
				StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_m_Idle.gif", GifImage.class));

		changeAnimation();
		animationRunner.setActiveState(false);
	}

	long timpVizibilPrec = 0;

	void atinsLight() {
		if (isTouching(Light.class)) {
			eVizibil = true;
			timpVizibilPrec = System.currentTimeMillis();
		} else {
			if (eVizibil) {
				long timpCurent = System.currentTimeMillis();
				if (timpCurent - timpPrec >= 9950) {
					eVizibil = false;
				}
			}
		}
	}

	void atingPlayer() {
		List l = getObjectsInRange(40, Player.class);
		if (l.size() > 0) {
			timpVizibilPrec = System.currentTimeMillis();
			npcImg = directie.get(super.gif);
		}
	}

	protected void atac() {
		if (!usedItem) {
			getWorld().removeObjects(getWorld().getObjects(TaserDolpatian.class));
			getWorld().addObject(new TaserDolpatian(this), getX(), getY());
		}
		usedItem = true;
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

			gif = "idle";

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
				atinsLight();
				long waitseed = Greenfoot.getRandomNumber(2500);

				if (isTouching(BasePlayer.class)) {

					timpAtins = 0;// {
					atingePlayer = true;// ataca
					atac();/////// {

					gif = "idle";
					eVizibil = true;

					atingPlayer();
				} else {

					long wait = Greenfoot.getRandomNumber(20) + 30 + waitseed;// {
					wait = 0;

					if (atingePlayer == true) {
						timpAtins++;
						if (timpAtins >= wait) /////////////////////// ia o pauza
						{
							atingePlayer = false;
							usedItem = false;

						}
					} /////////////////////////////////////////////// {
					else {
						// daca e in range sa nu l caute in toata lumea
						List players = getWorld().getObjects(Player.class);
						Player player = (Player) players.get(0);
						int deltaPGX = player.getWorldX() - (this.worldX + Scroller.scrolledX);
						if (deltaPGX < 0) {
							deltaPGX *= (-1);
						}
						int deltaPGY = player.getWorldY() - (this.worldY + Scroller.scrolledY);
						if (deltaPGY < 0) {
							deltaPGY *= (-1);
						}
						if (deltaPGX <= WorldData.WIDTH * 0.65f && deltaPGY <= WorldData.HEIGHT * 0.65f) {

							if (!WorldData.metDolpatian && WorldData.nrEvent <= 20 && eVizibil) {
								getWorld().addObject(new Tutorial("Combat", "Dolpatian", 3, false), WorldData.menuX,
										WorldData.menuY);
								WorldData.metDolpatian = true;
							}
							gaseste();// cauta playerul

							if (!eVizibil) {
								gif = "inviz";
							}
						}
						atingPlayer();
						int difpx = Scroller.scrolledX - prevsx;
						int difpy = Scroller.scrolledY - prevsy;

						worldX -= difpx;
						worldY -= difpy;
						prevsx = Scroller.scrolledX;
						prevsy = Scroller.scrolledY;
					}

				}
				this.knockbackMove();
				die();
			}

			if (animationRunner.isActive()) {
				animationRunner.run();
			} else {
				npcImg = directie.get(gif);
				setImage(npcImg.getCurrentImage());
			}
		}

	}

	private void changeAnimation() {
		animation = StraferLiberator.assetManager.get("images/npc/inamic/dolpatian/dolpatian_death.gif",
				Animation.class);
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
