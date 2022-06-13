package com.port;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Text extends Menu {

	Font fontu = new Font("edo", true, false, 10);

	String txt;
	int size;

	public Text(String txtref, int sizeref) {
		txt = txtref;
		size = sizeref;

		GreenfootImage textImage = new GreenfootImage(1024, 576);
		textImage.setFont(new Font("consolas", size));
		int textWidth = (int) new GlyphLayout(textImage.getFont(), txtref).width;
		textImage.drawString(txt, 512 - textWidth / 2, 576 / 2);

		setImage(textImage);

	}

	public void act() {
		// Add your action code here.
	}
}
