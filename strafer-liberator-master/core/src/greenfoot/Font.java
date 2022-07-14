package greenfoot;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font extends com.badlogic.gdx.graphics.g2d.BitmapFont {
	/* Port al clasei Font din Greenfoot
	 * Este folosit pt a desena pe GreenfootImage
	 * */
	boolean bold;

	boolean italic;
	float size = 10;

	BitmapFontData data;

	public Font(int size) {

	}

	public Font(boolean bold, boolean italic, int size) {

	}

	public Font(java.lang.String name, int size) {

		//super(new FileHandle("assets/fonts/" + name + ".fnt"), true);
		super(Gdx.files.internal("fonts/" + name + ".fnt"), true);
		data = getData();
		data.setScale(size / 10);
	}

	public Font(java.lang.String name, boolean bold, boolean italic, int size) {
		//super(new FileHandle("assets/fonts/" + name + ".fnt"), true);
		super(Gdx.files.internal("fonts/" + name + ".fnt"), true);
		data = getData();
		data.setScale(size);
	}
	public Font(java.lang.String name, boolean bold, boolean italic, int size,boolean atAssetManager) {
		super(Gdx.files.internal( name ), true);
		data = getData();
		data.setScale(size);
	}

	public void setColor(Color color) {
		super.setColor(color);
	}

	public void setData(BitmapFontData d) {
		data=d;
	}
	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
		data.setScale(size / 10);
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}


}