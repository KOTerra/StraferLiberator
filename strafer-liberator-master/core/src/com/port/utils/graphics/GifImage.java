package com.port.utils.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.straferliberator.StraferLiberator;

import greenfoot.GreenfootImage;

/*
 * Clasa ajutatoare care actualizeaza in timp frame-urile unui gif in GreenfootImage
 */
public class GifImage {

	Animation<TextureRegion> animation;

	float frameDuration;
	float elapsed;
	List<GreenfootImage> greenfootImages = new ArrayList<>();
	int numberOfFrames = 0;
	float scaleX = -1, scaleY = -1;

	
	
	public GifImage(String file) {
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("images/" + file).read());
		

		frameDuration = animation.getFrameDuration();
		for (TextureRegion t : animation.getKeyFrames()) {
			greenfootImages.add(new GreenfootImage(t));
			++numberOfFrames;
		}
	}

	public GifImage(Animation anim) {
		animation = anim;
		
		animation.setFrameDuration(animation.getFrameDuration());
		frameDuration = animation.getFrameDuration();
		for (TextureRegion t : animation.getKeyFrames()) {
			greenfootImages.add(new GreenfootImage(t));
			++numberOfFrames;
		}
	}

	public GreenfootImage getCurrentImage() {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		int ind=animation.getKeyFrameIndex(elapsed);
		return greenfootImages.get(ind);
	}

	public List<GreenfootImage> getImages() {
		
		return greenfootImages;
	}

	public void scale(float x, float y) {
		scaleX = x;
		scaleY = y;
		for (GreenfootImage g : greenfootImages) {
			if (scaleX >= 0 && scaleY >= 0) {
				g.scale(scaleX, scaleY);
			}
		}
	}
}
