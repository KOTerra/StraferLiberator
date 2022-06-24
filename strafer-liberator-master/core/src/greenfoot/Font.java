package greenfoot;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

public class Font extends com.badlogic.gdx.graphics.g2d.BitmapFont {

	boolean bold;
	boolean italic;
	float size = 10;

	BitmapFontData data = new BitmapFontData();

	public Font(int size) {

	}

	public Font(boolean bold, boolean italic, int size) {

	}

	public Font(java.lang.String name, int size) {

		super(new FileHandle("assets/fonts/" + name + ".fnt"), true);
		data = getData();
		data.setScale(size / 10);
	}

	public Font(java.lang.String name, boolean bold, boolean italic, int size) {
		super(new FileHandle("assets/fonts/" + name + ".fnt"), true);
		data = getData();
		data.setScale(size);
	}

	public void setColor(Color color) {
		super.setColor(color);
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
		data.setScale(size / 10);
	}

}