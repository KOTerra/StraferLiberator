package com.port.utils.graphics;

import com.game.straferliberator.StraferLiberator;
import com.port.UI.menu.Menu;
import com.port.world.WorldData;

import greenfoot.Font;
import greenfoot.GreenfootImage;

/*
 * Clasa ajutatoare
 * actor care da display unui text
 */
public class Text extends Menu {

	String txt;
	int size;

	public Text(String txtref, int sizeref) {
		txt = txtref;
		size = sizeref;

		GreenfootImage textImage = new GreenfootImage(WorldData.WIDTH, WorldData.HEIGHT);
		textImage.setFont(StraferLiberator.cFont);
		//int textWidth = (int) new GlyphLayout(textImage.getFont(), txtref).width;
		textImage.drawString(txt, WorldData.WIDTH/2 , WorldData.HEIGHT / 2);

		setImage(textImage);
	}

}
