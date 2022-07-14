package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.utils.graphics.Animation;
import com.port.utils.graphics.GifDecoder;
import com.port.utils.graphics.GifImage;

import greenfoot.GreenfootImage;

public class AnimationLoader extends AsynchronousAssetLoader<Animation, AnimationLoader.AnimationLoaderParameter> {

	public AnimationLoader(FileHandleResolver resolver) {
		super(resolver);
	}



	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, AnimationLoaderParameter parameter) {}


	@Override
	public Animation loadSync(AssetManager manager, String fileName, FileHandle file,
			AnimationLoaderParameter parameter) {
		
		GifImage gImg=GifDecoder.loadGIFAnimation(file.read());
		Array imgs = gImg.getImages();
		GreenfootImage[] images=new GreenfootImage[imgs.size];
		for (int i = 0; i < imgs.size; i++) {
			images[i] = (GreenfootImage) imgs.get(i);
		}
		Animation animation=new Animation(null,images);
		animation.setCycleActs(parameter.cycleActs);
		animation.setCycleCount(parameter.cycleCount);
		animation.setScalar(parameter.scalar);
		return animation;
	}


	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
			AnimationLoaderParameter parameter) {

		return null;
	}
	
	
	public static class AnimationLoaderParameter extends AssetLoaderParameters<Animation>{
		public int cycleActs;
		public int cycleCount;
		public int scalar;
		public AnimationLoaderParameter(int cycleActs, int cycleCount, int scalar) {
			this.cycleActs=cycleActs;
			this.cycleCount=cycleCount;
			this.scalar=scalar;
		}
	}

}
