package greenfoot;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.port.WorldData;

public class GreenfootImage extends com.badlogic.gdx.graphics.g2d.TextureRegion {

	Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.Alpha);
	FrameBuffer frameBuffer;

	private int transparency = 255;
	Color color = new Color(0, 0, 0);
	private Font font;

	float scaleX;
	float scaleY;

	public GreenfootImage(FileHandle file) {
		super(new Texture(file));
		scaleX = getWidth();
		scaleY = getHeight();
	}

	public GreenfootImage(String f) {
		super(new Texture(new FileHandle("assets/images/" + f)));
		scaleX = getWidth();
		scaleY = getHeight();

	}

	public GreenfootImage(int width, int height) {
		//super(new Texture(new FileHandle("assets/images/blank.png")),0,0,width,height);
		super(new Texture(new Pixmap(width, height,Pixmap.Format.RGBA8888)));
		pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		
		scaleX = width;
		scaleY = height;
	}

	public GreenfootImage(Texture t) {
		super(t);
		scaleX = getWidth();
		scaleY = getHeight();

	}

	public GreenfootImage(TextureRegion region) {
		super(region);
		scaleX = getWidth();
		scaleY = getHeight();
	}
	public GreenfootImage(GreenfootImage gfi) {
		super(gfi);
		scaleX=gfi.getWidth();
		scaleY=gfi.getHeight();
	}

	public void clear() {
		pixmap.setBlending(Pixmap.Blending.SourceOver);
		setColor(new Color(0,0,0,0f));
		pixmap.fill();
		makeDraw();
	}

	public void drawImage(GreenfootImage img2, int f, int g) { // imagine pasta imagine
		pixmap.drawPixmap(img2.getPixmap(), f, g);
		makeDraw();
	}

	

	public void drawString(java.lang.String string, int x, int y) { // text pasta imagine
		Pixmap pxmp=new Pixmap((int)scaleX,(int)scaleY,Pixmap.Format.RGBA8888);
		pxmp.setBlending(Pixmap.Blending.SourceOver);
		pxmp.setColor(new Color(0,0,0,0f));
		pxmp.fill();
		Texture texture = new Texture(pxmp);
		
		BitmapFontData fdata=font.getData();
		
		SpriteBatch spriteBatch = new SpriteBatch();
		frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		frameBuffer.begin();
		spriteBatch.begin();
			TextureRegion textureRegion = new TextureRegion(texture);
			textureRegion.flip(false, true);
			
			spriteBatch.draw(textureRegion,0,0);
		
			color.a=1;
			font.setColor(color);
			fdata.setScale(2*WorldData.WIDTH/scaleX, 2*WorldData.HEIGHT/scaleY);
			font.draw(spriteBatch, string, x, y);

			texture = frameBuffer.getColorBufferTexture();

		
			super.setRegion(texture);
		spriteBatch.end();
		frameBuffer.end();

	}

	private void makeDraw() {
		super.setRegion(new Texture(pixmap));
	}
	
	void drawLine(int x1, int y1, int x2, int y2) {
		pixmap.drawLine(x1, y1, x2, y2);
		makeDraw();
	}

	void drawOval(int x, int y, int width, int height) {	//face numa cerc atat s a putut
		pixmap.drawCircle(x, y, height);
		makeDraw();
	}

	void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

	}

	void drawRect(int x, int y, int width, int height) {
		pixmap.drawRectangle(x, y, width, height);
		makeDraw();
	}

	void fill() {
		pixmap.fill();
		makeDraw();
	}

	void fillOval(int x, int y, int width, int height) {

	}

	void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

	}

	void fillRect(int x, int y, int width, int height) {
		pixmap.fillRectangle(x, y, width, height);
		makeDraw();
	}

	Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		pixmap.setColor(color);
		this.color = color;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	public void scale(float f, float g) {
		scaleX = f;
		scaleY = g;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getWidth() {

		return super.getRegionWidth();
	}

	public float getHeight() {

		return super.getRegionHeight();
	}

	public Pixmap getPixmap() {
		return pixmap;
	}
}
