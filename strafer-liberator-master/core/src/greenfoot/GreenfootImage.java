package greenfoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.game.straferliberator.StraferLiberator;
import com.port.world.WorldData;


/*
 * port al clasei din Greenfoot 
 * reprezinta imaginile care le sunt atribuite Actorilor sau fundalurilor din World
 */
public class GreenfootImage extends com.badlogic.gdx.graphics.g2d.TextureRegion {

	Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
	FrameBuffer frameBuffer;

	private int transparency = 255;
	com.badlogic.gdx.graphics.Color backgroundColor = new Color(0, 0, 0, 0f);
	com.badlogic.gdx.graphics.Color textColor = new Color(0, 0, 0, 1);

	 Font font = StraferLiberator.cFont;


	float scaleX;
	float scaleY;
	float difx=0;
	float dify=0;

	public GreenfootImage(FileHandle file) {
		super(new Texture(file));
		scaleX = getWidth();
		scaleY = getHeight();
	}

	public GreenfootImage(String f) {
		super(new Texture(Gdx.files.internal("images/" + f)));
		scaleX = getWidth();
		scaleY = getHeight();
	
	}

	public GreenfootImage(int width, int height) {
		super(new Texture(new Pixmap(width, height, Pixmap.Format.RGBA8888)));
		pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

		scaleX = width;
		scaleY = height;
	}

	public GreenfootImage(Texture t) {
		super(t);
		t.getTextureData().prepare();
		pixmap = t.getTextureData().consumePixmap();
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
		scaleX = gfi.getWidth();
		scaleY = gfi.getHeight();
	}

	public GreenfootImage(String string, int i, Color textColor, Color backgroundColor) {

		int textWidth = (int) new GlyphLayout(font, string).width;
		int textHeight = (int) new GlyphLayout(font, string).height;
		setRegion(new Texture(new Pixmap(textWidth, textHeight, Pixmap.Format.RGBA8888)));
		pixmap = new Pixmap(textWidth, textHeight, Pixmap.Format.RGBA8888);

		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		scaleX = textWidth;
		scaleY = textHeight;

	}

	public void clear() {
		pixmap.setBlending(Pixmap.Blending.SourceOver);
		setColor(new Color(0, 0, 0, 0f));
		pixmap.fill();
		makeDraw();
	}

	public void drawImage(GreenfootImage img2, float f, float g) { // imagine pasta imagine
		if (!getTexture().getTextureData().isPrepared()) {
			getTexture().getTextureData().prepare();
		}
		pixmap = getTexture().getTextureData().consumePixmap();
		pixmap.drawPixmap(img2.getPixmap(), (int) f, (int) g);
		makeDraw();
		getTexture().getTextureData().disposePixmap();
	}

	public void drawString(java.lang.String string, int x, int y) { // text pasta imagine

		Pixmap pxmp = new Pixmap((int) scaleX, (int) scaleY, Pixmap.Format.RGBA8888);
		pxmp.setBlending(Pixmap.Blending.SourceOver);
		pxmp.setColor(backgroundColor);
		pxmp.fill();
		Texture texture = new Texture(pxmp);

		BitmapFontData fdata = font.getData();

		SpriteBatch spriteBatch = new SpriteBatch();
		frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

		frameBuffer.begin();
		spriteBatch.begin();
		TextureRegion textureRegion = new TextureRegion(texture);
		textureRegion.flip(false, true);

		spriteBatch.draw(textureRegion, 0, 0);

		textColor.a = 1;
		font.setColor(textColor);
		fdata.setScale(2 * WorldData.WIDTH / scaleX, 2 * WorldData.HEIGHT / scaleY);
		font.draw(spriteBatch, string, x, y);

		texture = frameBuffer.getColorBufferTexture();

		super.setRegion(texture);
		spriteBatch.end();
		frameBuffer.end();

	}

	private void makeDraw() {
		super.setRegion(new Texture(pixmap));
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		pixmap.drawLine(x1, y1, x2, y2);
		makeDraw();
	}

	void drawOval(int x, int y, int width, int height) { // face numa cerc atat s a putut
		pixmap.drawCircle(x, y, height);
		makeDraw();
	}

	void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

	}

	public void drawRect(int x, int y, float f, float g) {

		pixmap.drawRectangle(x, y, (int) f, (int) g);
		makeDraw();
	}

	public void fill() {
		pixmap.fill();
		makeDraw();
	}

	void fillOval(int x, int y, int width, int height) {

	}

	void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

	}

	public void fillRect(int x, int y, int width, int height) {
		pixmap.fillRectangle(x, y, width, height);
		makeDraw();
	}

	public void setColor(com.badlogic.gdx.graphics.Color safeColor) {
		this.backgroundColor = safeColor;
		pixmap.setColor(backgroundColor);
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	public void scale(float f, float g) {
		difx=scaleX-f;
		dify=scaleY-g;
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

	public com.badlogic.gdx.graphics.Color getColor() {
		return backgroundColor;
	}

	public com.badlogic.gdx.graphics.Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setTransparency(int t) {
		transparency=t;
	}

	public float getTransparency() {
		return transparency;
	}

	public void dispose() {
		try {
			pixmap.dispose();
			getTexture().dispose();
			frameBuffer.dispose();
		}catch(Exception e){
			
		}
	}

}
