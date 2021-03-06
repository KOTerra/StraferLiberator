package com.port.UI.menu;

import com.game.straferliberator.StraferLiberator;
import com.port.UI.button.Button;
import com.port.utils.graphics.Picture;
import com.port.world.WorldData;

import greenfoot.*;

/**
 * O clasa pentru meniurile de tutorial, folosita si pentru cutsceneuri
 */
public class Tutorial extends Menu {

	private int nrSlide;
	private int nrSlideMax;

	/**
	 * Numele folderului in care sunt slideurile unui tutorial aferent adica numele
	 * tutorialului
	 */
	private String img;
	private String tip;
	private Picture picture = new Picture(
			StraferLiberator.assetManager.get("images/UI/tutorial/tutorial.png", GreenfootImage.class));
	private Button buton = new Button("next", this);

	private boolean addedButon = false;
	private boolean addedButonBack = false;
	private boolean inPause;
	private boolean addedPicture = false;

	public Tutorial(String tipref, String imgref, int slideref, boolean inPauseref) {
		WorldData.PAUZA = true;
		nrSlideMax = slideref;
		tip = tipref;
		nrSlide = 0;
		img = imgref;
		addedButon = false;
		addedPicture = false;
		this.inPause = inPauseref;

		setImage(StraferLiberator.assetManager.get("images/UI/tutorial/tutorial.png", GreenfootImage.class));

	}

	public void displayPicture() {
		if (!addedPicture) {
			getWorld().addObject(picture, WorldData.menuX, WorldData.menuY);
			updateImage();

			addedPicture = true;
		}
		if (addedPicture) {
			picture.setLocation(WorldData.menuX, WorldData.menuY);
		}

	}

	int butonTutorialInitialx;

	public void addButon() {
		if (!addedButon) {
			if (tip == "Cutscene") {
				getWorld().addObject(buton, WorldData.WIDTH - 100, WorldData.HEIGHT - 100);
			} else {
				butonTutorialInitialx = (int) (WorldData.menuX + getImage().getWidth() / 2 - 50);
				getWorld().addObject(buton, butonTutorialInitialx, WorldData.menuY + getImage().getHeight() / 2 - 50);
			}

			addedButon = true;
		}
		if (addedButon) {
			if (tip == "Cutscene") {
				buton.setLocation(WorldData.WIDTH - 100, WorldData.HEIGHT - 100);
			} else {
				buton.setLocation(butonTutorialInitialx, WorldData.menuY + getImage().getHeight() / 2 - 50);
			}
		}
	}

	public void act() {
		addButon();
		displayPicture();
		this.setLocation(WorldData.menuX, WorldData.menuY);
	}

	public void updateImage() {
		if (tip == "Cutscene") {
			picture.setImageName("UI/cutscene/" + img + "/" + img + nrSlide + ".png");
		} else {
			picture.setImageName("UI/tutorial/tutorialSlides/" + tip + "/" + img + "/" + tip + "#" + "tutorial" + img
					+ nrSlide + ".png");
		}
	}

	public String toString() {
		String str = tip + " " + img + " " + nrSlideMax;
		return str;
	}

	public int getNrSlideMax() {
		return nrSlideMax;
	}

	public int getNrSlide() {
		return nrSlide;
	}

	public void setNrSlide(int nrSlide) {
		if (nrSlide >= 0) {
			this.nrSlide = nrSlide;
		}
	}

	public String getImgName() {
		return img;
	}

	public String getTip() {
		return tip;
	}

	public boolean isLastSlide() {
		return nrSlide == nrSlideMax - 1 ? true : false;
	}

	public boolean isFirstSlide() {
		return nrSlide == 0 ? true : false;
	}

	public boolean isAddedPicture() {
		return addedPicture;
	}

	public void setAddedPicture(boolean addedPicture) {
		this.addedPicture = addedPicture;
	}

	public boolean isAddedButonBack() {
		return addedButonBack;
	}

	public void setAddedButonBack(boolean addedButonBack) {
		this.addedButonBack = addedButonBack;
	}

	public boolean isInPause() {
		return inPause;
	}

	public Picture getPicture() {
		return picture;
	}
}
