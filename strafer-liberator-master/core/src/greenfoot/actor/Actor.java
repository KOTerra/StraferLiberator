package greenfoot.actor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.straferliberator.display.StraferLiberator;
import com.port.world.WorldData;

import greenfoot.display.GreenfootImage;

public class Actor extends com.badlogic.gdx.scenes.scene2d.ui.Image {

	private float x = 0, y = 0;
	public greenfoot.world.World world;
	greenfoot.display.GreenfootImage image = new GreenfootImage(1, 1);
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

	}

	public float getRotation() {
		return -super.getRotation();
	}

	public void setRotation(float r) {

		super.setRotation(-r);

	}

	public void turnTowards(float f, float g) {
		double a = Math.atan2(g - this.y, f - this.x);
		setRotation(-(int) Math.toDegrees(a));
	}

	public boolean isAtEdge() {
		return (x <= 0 || y <= 0 || x >= com.port.world.WorldData.WIDTH - 1 || y >= com.port.world.WorldData.HEIGHT - 1);
	}

	public void move(int distance) {
		double radians = Math.toRadians(getRotation());

		int dx = (int) Math.round(Math.cos(radians) * distance);
		int dy = (int) Math.round(Math.sin(radians) * distance);
		setLocation(x + dx, getY() + dy);
	}

	public void turn(int amount) {
		setRotation(super.getRotation() - amount);
	}

	public greenfoot.world.World getWorld() {
		return world;
	}

	public void setWorld(greenfoot.world.World world) {
		super.setStage(world);
		this.world = world;
	}

	protected boolean intersects(Actor other) { /// merge, le facem asa toate

		Rectangle r = new Rectangle(x - iw() / 2, y - ih() / 2, iw(), ih());
		Rectangle r2 = new Rectangle(other.getX() - other.iw() / 2, other.getStageY() - other.ih() / 2, other.iw(),
				other.ih());
		if (r.overlaps(r2)) {
			return true;
		}

		return false;

	}

	public boolean isTouching(java.lang.Class<?> cls) {
		java.util.List<?> l = getWorld().getObjects(cls);
		for (Object actor : l) {
			if (actor instanceof greenfoot.actor.Actor && actor != this) {
				if (((greenfoot.actor.Actor) actor).intersects(this)) {
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
			if (((greenfoot.actor.Actor) actor).intersects(this)) {
				world.removeObject((greenfoot.actor.Actor) actor);
			}
		}
	}

	protected List<?> getIntersectingObjects(java.lang.Class<?> cls) {
		List<?> l = getWorld().getObjects(cls);
		ArrayList<greenfoot.actor.Actor> res = new ArrayList<>();
		for (Object actor : l) {
			if (((greenfoot.actor.Actor) actor).intersects(this)) {
				res.add((Actor) actor);
			}
		}

		return res;
	}

	@SuppressWarnings("rawtypes")
	protected greenfoot.actor.Actor getOneIntersectingObject(java.lang.Class<?> cls) {
		if (getIntersectingObjects(cls).isEmpty()) {
			return null;
		}
		return (greenfoot.actor.Actor) getIntersectingObjects(cls).get(0);
	}

	protected greenfoot.actor.Actor getOneObjectAtOffset(int dx, int dy, java.lang.Class<?> cls) {
		if (getWorld().getObjectsAt(this.getX() + dx, this.getY() + dy, cls).isEmpty()) {
			return null;
		}
		return (greenfoot.actor.Actor) (getWorld().getObjectsAt(this.getX() + dx, this.getY() + dy, cls).get(0));
	}

	protected java.util.List<?> getObjectsInRange(int radius, java.lang.Class<?> cls) {
		List<?> l = getWorld().getObjects(cls);
		ArrayList<greenfoot.actor.Actor> res = new ArrayList<>();
		Circle c = new Circle(getX(), getStageY(), radius);
		for (Object obj : l) {
			greenfoot.actor.Actor actor = (greenfoot.actor.Actor) obj;

			Rectangle r = new Rectangle(actor.getX() - actor.iw() / 2, actor.getStageY() - actor.ih() / 2, actor.iw(),
					actor.ih());
			if (Intersector.overlaps(c, r)) {
				if (actor != this) {
					res.add(actor);
				}
			}
		}
		return res;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public greenfoot.display.GreenfootImage getImage() {
		return image;
	}

	public void setImage(greenfoot.display.GreenfootImage gi) {
		image = gi;
		float prevx = x, prevy = y;
		this.setBounds(1, 1, image.getWidth(), image.getHeight());
		x = prevx;
		y = prevy;

		setPosition(x, y);

	}

	public void setImage(String s) {
		greenfoot.display.GreenfootImage gi = new GreenfootImage(s);
		image = gi;
		float prevx = x, prevy = y;

		this.setBounds(1, 1, image.getWidth(), image.getHeight());
		x = prevx;
		y = prevy;
		setPosition(x, y);

	}

	public void draw() {
		if (isInScreenRange()) {
			batch.draw(this.getImage(), getX() - this.iw() / 2, getStageY() - this.ih() / 2, // coordonatele
					this.iw() / 2, this.ih() / 2, // pct in care e rotit
					this.getImage().getScaleX(), this.getImage().getScaleY(), // width/height
					1, 1, // scale
					super.getRotation()); // rotation
		}
	}

	public boolean isInScreenRange() {
		if (getX() < -WorldData.WIDTH || getX() > WorldData.WIDTH * 1.5f || getY() < -WorldData.HEIGHT
				|| getY() > WorldData.HEIGHT * 1.5f) {
			return false;
		}
		return true;
	}

	public float iw() {
		return getImage().getWidth();
	}

	public float ih() {
		return getImage().getHeight();
	}

}
