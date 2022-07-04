package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import greenfoot.GreenfootImage;

public class GreenfootImageLoader
		extends AsynchronousAssetLoader<GreenfootImage, GreenfootImageLoader.GreenfootImageLoaderParameters> {

	public GreenfootImageLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file,
			GreenfootImageLoaderParameters parameter) {
		// TODO Auto-generated method stub

	}

	@Override
	public GreenfootImage loadSync(AssetManager manager, String fileName, FileHandle file,
			GreenfootImageLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
			GreenfootImageLoaderParameters parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	static public class GreenfootImageLoaderParameters extends AssetLoaderParameters<GreenfootImage> {

	}
}