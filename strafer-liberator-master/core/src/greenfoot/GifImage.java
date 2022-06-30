package greenfoot;

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

public class GifImage {

	Animation<TextureRegion> animation;

	float frameDuration;
	float elapsed;
	GreenfootImage greenfootImage;
	float scaleX = -1, scaleY = -1;

	public GifImage(String file) {
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("images/" + file).read());

		frameDuration = animation.getFrameDuration();
	}

	public GreenfootImage getCurrentImage() {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		TextureRegion region = (animation.getKeyFrame(elapsed, true));
		GreenfootImage img = new GreenfootImage(region);
		if (scaleX >= 0 && scaleY >= 0) {
			img.scale(scaleX, scaleY);
		}
		return img;
	}

	public List<GreenfootImage> getImages() {
		List<GreenfootImage> l = new ArrayList<GreenfootImage>();

		for (TextureRegion t : animation.getKeyFrames()) {
			GreenfootImage img = new GreenfootImage(t);
			if (scaleX >= 0 && scaleY >= 0) {
				img.scale(scaleX, scaleY);
			}
			l.add(img);
		}
		return l;
	}

	public void scale(float x, float y) {
		scaleX = x;
		scaleY = y;
	}
}
