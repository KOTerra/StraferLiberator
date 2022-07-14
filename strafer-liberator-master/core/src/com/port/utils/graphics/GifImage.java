package com.port.utils.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.world.WorldData;

import greenfoot.GreenfootImage;

/*
 * Clasa ajutatoare care actualizeaza in timp frame-urile unui gif in GreenfootImage
 */
public class GifImage {

	float frameDuration;
	
	Array greenfootImages = new Array(GreenfootImage.class);
	int numberOfFrames = 0;
	float scaleX = -1, scaleY = -1;

	
	

	public GifImage(float frameDuration, Array gImg) {
		greenfootImages=gImg;
		this.frameDuration=frameDuration;
	}

	public GreenfootImage getCurrentImage() {

		int ind=getKeyFrameIndex(WorldData.elapsed);
		
		return (GreenfootImage) greenfootImages.get(ind);
	}

	public Array getImages() {
		
		return greenfootImages;
	}

	public void scale(float x, float y) {
		scaleX = x;
		scaleY = y;
		for (Object g : greenfootImages) {
			if (scaleX >= 0 && scaleY >= 0) {
				((GreenfootImage)g).scale(scaleX, scaleY);
			}
		}
	}
	public int getKeyFrameIndex (float stateTime) {
		if (greenfootImages.size == 1) return 0;

		int frameNumber = (int)(stateTime / frameDuration);

			frameNumber = frameNumber % greenfootImages.size;
	

	
		return frameNumber;
	}

}
