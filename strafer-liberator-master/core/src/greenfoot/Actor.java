package greenfoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.game.straferliberator.StraferLiberator;
import com.port.GifImage;
import com.port.Inamic;
import com.port.WorldData;

public class Actor extends com.badlogic.gdx.scenes.scene2d.ui.Image {

	private float x = 0, y = 0;
	greenfoot.World world;
	greenfoot.GreenfootImage image;
	SpriteBatch batch;
	Rectangle rect;

	public Actor() {
		batch = StraferLiberator.batcher;
	}

	public void act() {
		super.act(0);
	}

	public float getX() {
		return super.getX();
	}

	public float getY() { // screen //y=0 in partea de sus

		return toScreenY(super.getY());
	}

	public float getStageY() { // stage //y=0 in partea de jos
		return super.getY();
	}

	public float toScreenY(float y) {
		if (world != null) {
			Vector2 v = world.stageToScreenCoordinates(new Vector2(0, y));

			return v.y;
		}
		return 0;
	}

	public void setPosition(float f, float g) {// se foloseste cu coordonate pe stage
		super.setPosition(f, g);
	}

	public void setLocation(float f, float g) {// se foloseste cu coordonate pe ecran
		x = f;
		y = toScreenY(g);
		setPosition(x, y);
		// System.out.println(y+" "+getY()+" "+getStageY());

	}

	public float getRotation() {
		return super.getRotation();
	}

	public void setRotation(float r) {
		super.setRotation(r);

	}

	public void turnTowards(float f, float g) {
		double a = Math.atan2(g - this.y, f - this.x);
		setRotation((int) Math.toDegrees(a));
	}

	public boolean isAtEdge() {
		return (x <= 0 || y <= 0 || x >= com.port.WorldData.WIDTH - 1 || y >= com.port.WorldData.HEIGHT - 1);
	}

	public void move(int distance) {
		double radians = Math.toRadians(getRotation());

		int dx = (int) Math.round(Math.cos(radians) * distance);
		int dy = (int) Math.round(Math.sin(radians) * distance);
		super.setPosition(x + dx, y + dy, Align.center);
	}

	public void turn(int amount) {
		setRotation(super.getRotation() + amount);
	}

	public greenfoot.World getWorld() {
		return world;
	}

	public void setWorld(greenfoot.World world) {
		super.setStage(world);
		this.world = world;
	}

	protected boolean intersects(Actor other) { /// merge, le facem asa toate
		Rectangle r = new Rectangle(x, y, image.getWidth(), image.getHeight());
		Rectangle r2 = new Rectangle(other.getX(), other.getStageY(), other.getImage().getWidth(),
				other.getImage().getHeight());

		if (r.overlaps(r2)) {
			return true;

		}
		return false;
	}

	protected boolean isTouching(java.lang.Class<?> cls) {
		java.util.List<?> l = getWorld().getObjects(cls);
		for (Object actor : l) {
			if (actor instanceof greenfoot.Actor) {
				if (((greenfoot.Actor) actor).intersects(this)) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	protected void removeTouching(java.lang.Class<?> cls) {
		java.util.List<?> l = (List<?>) getWorld().getObjects(cls);
		for (Object actor : l) {
			if (((greenfoot.Actor) actor).intersects(this)) {
				world.removeObject((greenfoot.Actor) actor);
			}
		}
	}

	protected List<?> getIntersectingObjects(java.lang.Class<?> cls) {
		List<?> l = getWorld().getObjects(cls);
		for (Object actor : l) {
			if (!((greenfoot.Actor) actor).intersects(this)) {
				l.remove(actor);
			}
		}
		return l;
	}

	@SuppressWarnings("rawtypes")
	protected greenfoot.Actor getOneIntersectingObject(java.lang.Class<?> cls) {
		return (greenfoot.Actor) getIntersectingObjects(cls).get(0);
	}

	protected greenfoot.Actor getOneObjectAtOffset(int dx, int dy, java.lang.Class<?> cls) {
		return (greenfoot.Actor) (getWorld().getObjectsAt(this.getX() + dx, this.getY() + dy, cls).get(0));
	}

	protected java.util.List<?> getObjectsInRange(int radius, java.lang.Class<?> cls) {
		List<?> l = getWorld().getObjects(cls);
		Circle c = new Circle(getX(), getStageY(), radius);
		for (Object obj : l) {
			greenfoot.Actor actor = (greenfoot.Actor) obj;
			Rectangle r = new Rectangle(actor.getX(), actor.getStageY(), 
										actor.getImage().getWidth(),actor.getImage().getHeight());
			if (!Intersector.overlaps(c, r)) {
				l.remove(actor);
			}
		}
		return l;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public greenfoot.GreenfootImage getImage() {
		return image;
	}

	public void setImage(greenfoot.GreenfootImage gi) {
		image = gi;
		float prevx = x, prevy = y;
		this.setBounds(1, 1, image.getWidth(), image.getHeight());
		x = prevx;
		y = prevy;
		setPosition(x, y);
	}

	public void setImage(String s) {
		greenfoot.GreenfootImage gi = new GreenfootImage(s);
		image = gi;
		float prevx = x, prevy = y;
		this.setBounds(1, 1, image.getWidth(), image.getHeight());
		x = prevx;
		y = prevy;
		setPosition(x, y);
	}

	public void draw() {
		batch.draw(this.getImage(), getX() - this.getImage().getWidth() / 2,
				getStageY() - this.getImage().getHeight() / 2);
	}

}
