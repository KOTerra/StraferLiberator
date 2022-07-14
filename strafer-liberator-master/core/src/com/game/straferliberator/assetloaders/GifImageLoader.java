package com.game.straferliberator.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.assetloaders.GreenfootImageLoader.GreenfootImageLoaderParameters;
import com.port.utils.graphics.GifDecoder;
import com.port.utils.graphics.GifImage;

import greenfoot.GreenfootImage;

public class GifImageLoader extends AsynchronousAssetLoader<GifImage, GifImageLoader.GifImageLoaderParameter> {

	public GifImageLoader(FileHandleResolver resolver) {

		super(resolver);

	}

	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, GifImageLoaderParameter parameter) {

	}

	@Override
	public GifImage loadSync(AssetManager manager, String fileName, FileHandle file,
			GifImageLoaderParameter parameter) {

		return (GifDecoder.loadGIFAnimation(file.read()));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, GifImageLoaderParameter parameter) {

		return null;
	}

	public static class GifImageLoaderParameter extends AssetLoaderParameters<GifImage> {


	}
}