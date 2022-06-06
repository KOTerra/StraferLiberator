package greenfoot;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.game.straferliberator.StraferLiberator;
import com.port.WorldData;

public class Actor extends com.badlogic.gdx.scenes.scene2d.ui.Image {

	private float x, y;
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
		return super.getX(Align.center);
	}

	public float getY() {
		return com.port.WorldData.HEIGHT - super.getY(Align.center);
	}

	public float getRotation() {
		return super.getRotation();
	}

	public void setRotation(float r) {
		super.setRotation(r);

	}

	public void turnTowards(float f, float g) {
		this.x = this.getX();
		this.y = this.getY();

		double a = Math.atan2(g - this.y, f - this.x);
		setRotation((int) Math.toDegrees(a));
	}

	public boolean isAtEdge() {
		this.x = this.getX();
		this.y = this.getY();
		return (x <= 0 || y <= 0 || x >= com.port.WorldData.WIDTH - 1 || y >= com.port.WorldData.HEIGHT - 1);
	}

	public void setLocation(float f, float g) {
		super.setPosition(f, g, Align.center);
	}

	public void move(int distance) {
		this.x = this.getX();
		this.y = this.getY();
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
		this.world = world;
	}

	protected boolean intersects(Actor other) {
		if(other.hit(this.x, this.y, false) != null) {
			return true;
		}
		return false;
	}
	
	protected boolean isTouching(java.lang.Class<?> cls) {
		java.util.List<?> l = getWorld().getObjects(cls);
		for (Iterator<?> iter = l.iterator(); iter.hasNext();) {
			Actor actor = (Actor) iter.next();
			if (actor.hit(this.x, this.y, false) != null) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	protected void	removeTouching(java.lang.Class<?> cls) {
		java.util.List<?> l = (List<?>) getWorld().getObjects(cls);
		for (Iterator<?> iter = l.iterator(); iter.hasNext();) {
			Actor actor = (Actor) iter.next();
			if (actor.hit(this.x, this.y, false) == null) {
				actor.remove();
			}
		}
	}

	protected <A> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		java.util.List<A> l = getWorld().getObjects(cls);
		for (Iterator<A> iter = l.iterator(); iter.hasNext();) {
			Actor actor = (Actor) iter.next();
			if (actor.hit(this.x, this.y, false) == null) {
				l.remove(actor);
			}
		}
		return l;
	}

	@SuppressWarnings("rawtypes")
	protected greenfoot.Actor getOneIntersectingObject(java.lang.Class<?> cls) {
		return (greenfoot.Actor) getIntersectingObjects(cls).get(0);
	}
	
	protected Actor	getOneObjectAtOffset(int dx, int dy, java.lang.Class<?> cls) {
		java.util.List<?> l = (List<?>) getWorld().getObjects(cls);
		for (Iterator<?> iter = l.iterator(); iter.hasNext();) {
			Actor actor = (Actor) iter.next();
			if (actor.hit(this.x+dx, this.y+dy, false) != null) {
				return actor;
			}
		}
		return null;
		
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
		//setDrawable((Drawable) ((Texture) image));
		this.setBounds(0, 0, image.getWidth(), image.getHeight());
		// makeBoundsRect();
		draw(batch, 1);
	}

	public void setImage(String s) {
		greenfoot.GreenfootImage gi = new GreenfootImage(s);
		image = gi;
		setDrawable((Drawable) (image));
		this.setBounds(0, 0, image.getWidth(), image.getHeight());
		// makeBoundsRect();
		draw(batch, 1);
	}

	public void draw() {
		batch.draw(this.getImage(),
				getX(Align.topLeft), 
				getY()-this.getImage().getHeight()/2);										
	}

	public void makeBoundsRect() {
		float w = this.getDrawable().getLeftWidth();
		float h = this.getDrawable().getBottomHeight();
		float x = super.getX(Align.bottomLeft);
		float y = super.getY(Align.bottomLeft);
		rect = new Rectangle(x, y, w, h);
	}

	public Rectangle getRect() {
		return rect;
	}


}
