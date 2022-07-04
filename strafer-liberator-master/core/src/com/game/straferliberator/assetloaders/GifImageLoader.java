package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.assetloaders.GreenfootImageLoader.GreenfootImageLoaderParameters;
import com.port.utils.graphics.GifImage;

import greenfoot.GreenfootImage;

public class GifImageLoader extends AsynchronousAssetLoader<GifImage, GifImageLoader.GifImageLoaderParameters> {

	public GifImageLoader(FileHandleResolver resolver) {
		super(resolver);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, GifImageLoaderParameters parameter) {
		// TODO Auto-generated method stub

	}

	@Override
	public GifImage loadSync(AssetManager manager, String fileName, FileHandle file,
			GifImageLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
			GifImageLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	static public class GifImageLoaderParameters extends AssetLoaderParameters<GifImage> {

	}
}