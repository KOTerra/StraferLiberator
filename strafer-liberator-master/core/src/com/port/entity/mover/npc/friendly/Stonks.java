package com.port.entity.mover.npc.friendly;

import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Dialog;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.GifImage;
import com.port.world.PlayWorld;
import com.port.world.Scroller;
import com.port.world.WorldData;

import greenfoot.*;

public class Stonks extends FriendlyNpc {
	private PlayWorld playWorld;
	private String dialogFile;

	private int nrDialog;

	GifImage img = StraferLiberator.assetManager.get("images/npc/friendly/stonks_idle.gif", GifImage.class);

	public Stonks(PlayWorld pw, Scroller scrl, String dialogFileref, int nrDialog) {
		super(pw, scrl, dialogFileref);
		playWorld = pw;
		this.nrDialog = nrDialog;
		dialogFile = dialogFileref;

	}

	public void addDialogs() {
		getWorld().addObject(new Dialog(this, "Stonks", nrDialog), WorldData.WIDTH / 2, WorldData.HEIGHT * 7 / 10);
	}

	private int timer = 0;

	public void act() {

		if (!WorldData.PAUZA) {
			timer++;
			if (intersects(playWorld.getPlayer()) && !WorldData.addedDialogs && timer >= WorldData.FPS * 3) {
				timer = 0;
				this.addDialogs();
				WorldData.addedDialogs = true;
			}
			setImage(img.getCurrentImage());
		}
	}

	public int getNrDialog() {
		return nrDialog;
	}

	public void setNrDialog(int nrDialog) {
		this.nrDialog = nrDialog;
	}
}
