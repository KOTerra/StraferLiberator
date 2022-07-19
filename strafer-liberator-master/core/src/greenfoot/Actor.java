package greenfoot;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.straferliberator.StraferLiberator;
import com.game.straferliberator.render.PaintManager;
import com.port.world.WorldData;

public class Actor extends com.badlogic.gdx.scenes.scene2d.ui.Image {
	/*
	 * Port pt greenfoot al clasei de Actor reprezinta o imagine cu bounds ce este
	 * desenata in World de WorldRenderer
	 **/

	private float x = 0, y = 0;
	public greenfoot.World world;
	public static greenfoot.GreenfootImage invisibleImg = new GreenfootImage(1, 1);
	greenfoot.GreenfootImage image = invisibleImg;
	SpriteBatch batch;
	boolean notRemovedYet = true;

	public Actor() {
		batch = StraferLiberator.batch;
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
		return Gdx.graphics.getHeight() - y;
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
		return (x <= 0 || y <= 0 || x >= com.port.world.WorldData.WIDTH - 1
				|| y >= com.port.world.WorldData.HEIGHT - 1);
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

	public greenfoot.World getWorld() {
		return world;
	}

	public void setWorld(greenfoot.World world) {
		super.setStage(world);
		this.world = world;
	}

	protected boolean intersects(Actor other) { /// merge, le facem asa toate

		/*
		 * Rectangle r = new Rectangle(x - iw() / 2, y - ih() / 2, iw(), ih());
		 * Rectangle r2 = new Rectangle(other.getX() - other.iw() / 2, other.getStageY()
		 * - other.ih() / 2, other.iw(), other.ih()); if (r.overlaps(r2)) { return true;
		 * }
		 * 
		 * return false;
		 */
		if(other==null) {
			return false;
		}
		return 	   x - this.iw() / 2 < other.getX() 	 + other.iw() / 2 
				&& x + this.iw() / 2 > other.getX()		 - other.iw() / 2
				&& y - this.ih() / 2 < other.getStageY() + other.ih() / 2 
				&& y + this.ih() / 2 > other.getStageY() - other.ih() / 2	;

	}

	public boolean isTouching(java.lang.Class<?> cls) {
		for (Object actor : getWorld().getObjects(cls)) {
			if (actor instanceof greenfoot.Actor && actor != this) {
				if (((greenfoot.Actor) actor).intersects(this)) {
					return true;
				}
			}
		}
		return false;
	}

	protected void removeTouching(java.lang.Class<?> cls) {
		for (Object actor : getWorld().getObjects(cls)) {
			if (((greenfoot.Actor) actor).intersects(this)) {
				world.removeObject((greenfoot.Actor) actor);
			}
		}
	}

	protected List<?> getIntersectingObjects(java.lang.Class<?> cls) {
		ArrayList<greenfoot.Actor> res = new ArrayList<>();
		for (Object actor : getWorld().getObjects(cls)) {
			if (((greenfoot.Actor) actor).intersects(this)) {
				res.add((Actor) actor);
			}
		}

		return res;
	}

	protected greenfoot.Actor getOneIntersectingObject(java.lang.Class<?> cls) {
		if (getIntersectingObjects(cls).isEmpty()) {
			return null;
		}
		return (greenfoot.Actor) getIntersectingObjects(cls).get(0);
	}

	protected greenfoot.Actor getOneObjectAtOffset(int dx, int dy, java.lang.Class<?> cls) {
		if (getWorld().getObjectsAt(this.getX() + dx, this.getY() + dy, cls).isEmpty()) {
			return null;
		}
		return (greenfoot.Actor) (getWorld().getObjectsAt(this.getX() + dx, this.getY() + dy, cls).get(0));
	}

	protected java.util.List<?> getObjectsInRange(int radius, java.lang.Class<?> cls) {
		ArrayList<greenfoot.Actor> res = new ArrayList<>();

		for (Object obj : getWorld().getObjects(cls)) {
			greenfoot.Actor actor = (greenfoot.Actor) obj;

			if(radiusOverlaping(actor,radius)) {
				if (actor != this) {
					res.add(actor);
				}
			}
		}
		return res;
	}

	private boolean radiusOverlaping(greenfoot.Actor actor, int radius) {
		float cx=getX();
		float cy=getStageY();
		float rx=actor.getX() - actor.iw() / 2;
		float ry=actor.getStageY() - actor.ih() / 2;
		
		float closestX = cx;
		float closestY = cy;

		if (cx < rx) {
			closestX = rx;
		} else if (cx > rx + actor.iw()) {
			closestX = rx + actor.iw();
		}

		if (cy < ry) {
			closestY = ry;
		} else if (cy > ry + actor.ih()) {
			closestY = ry + actor.ih();
		}

		closestX = closestX - cx;
		closestX *= closestX;
		closestY = closestY - cy;
		closestY *= closestY;

		return closestX + closestY < radius * radius;
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
		if (notRemovedYet) {
			if (isInScreenRange()) {

				float icx = (getX() - this.iw() / 2);
				float icy = (getStageY() - this.ih() / 2);
				batch.setColor(255, 255, 255, image.getTransparency());
				batch.draw(this.getImage(), icx, icy, // coordonatele
						this.iw() / 2, this.ih() / 2, // pct in care e rotit
						this.getImage().getScaleX(), this.getImage().getScaleY(), // width/height
						1, 1, // scale
						super.getRotation()); // rotation

				batch.setColor(Color.WHITE);
			}
		} else {
			PaintManager.removeObjectFromPaintOrder(this);
		}
	}

	public void setNotRemovedYet(boolean canDraw) {
		this.notRemovedYet = canDraw;
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
