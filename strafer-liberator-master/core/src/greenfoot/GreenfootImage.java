package greenfoot;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GreenfootImage extends com.badlogic.gdx.graphics.g2d.TextureRegion{

	public GreenfootImage(FileHandle file) {super(new Texture(file));}
	
	public GreenfootImage(String f) {
		super(new Texture(new FileHandle("assets/images/"+f)));
		//super(new FileHandle(f));
		
	}

	public GreenfootImage(int width, int height) {
		super(new Texture(width, height,Pixmap.Format.RGB888));
	}
	
	public GreenfootImage(Texture t) {
		super(t);
		
	}



	public GreenfootImage(TextureRegion region) {
		super(region);
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public void drawImage(GreenfootImage lastImage, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public float getWidth() {

		return super.getRegionWidth();
	}

	public float getHeight() {

		return super.getRegionHeight();
	}

}
