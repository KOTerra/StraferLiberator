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

import greenfoot.GifDecoder;
import greenfoot.GreenfootImage;

public class GifImage {
	
	Animation<TextureRegion> animation;
	
	float frameDuration;
	float elapsed;
	GreenfootImage greenfootImage;
	
	
	public GifImage(String file){
		 animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("images/"+file).read());

		 frameDuration=animation.getFrameDuration();
	}
	public GreenfootImage getCurrentImage(){
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        TextureRegion region=(animation.getKeyFrame(elapsed, true));
       
        return new GreenfootImage(region);
	}
	public List<GreenfootImage> getImages() {	 
		List<GreenfootImage> l =new ArrayList<GreenfootImage>();
		
		for(Object t:animation.getKeyFrames()) {
			TextureRegion a=(TextureRegion)t;
			l.add(new GreenfootImage(a));
		}
		return l;
	}
}
