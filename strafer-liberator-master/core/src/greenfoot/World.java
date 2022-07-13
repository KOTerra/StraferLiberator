package greenfoot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.game.straferliberator.render.PaintUtilities;

/*
 * Port al clasei din Greenfoot
 * este locul in care exista toti actorii 
 */
public class World extends com.badlogic.gdx.scenes.scene2d.Stage {

	SpriteBatch batch = StraferLiberator.batch;
	GreenfootImage background;
	int cellSize; // unitatea de masura pixel^2
	private float wwidth, wheight;

	public World(int worldWidth, int worldHeight, int cellSize, boolean bounded) {
		this.cellSize = cellSize;
		wwidth = worldWidth;
		wheight = worldHeight;
	}

	public void act() {
		Array<com.badlogic.gdx.scenes.scene2d.Actor> l = getActors();

		for (int i = 0; i < l.size; i++) {
			Actor a = (greenfoot.Actor) l.items[i];

			a.act();

		}
	}
//

	public void addObject(greenfoot.Actor object, float initx, float inity) {
		if (object.world != null) {
			if (object.world == this) {
				return; // Actor is already in the world
			}
			object.world.removeObject(object);
		}
		object.notRemovedYet = true;
		object.setWorld(this);
		super.addActor(object);
		PaintUtilities.addObjectToPaintOrder(object);
		object.setLocation(initx, inity);

	}

	public void removeObject(greenfoot.Actor actor) { // si asta mergess
		if (actor == null) {
			return;
		}

		actor.setNotRemovedYet(false);
		actor.remove();
		Array<com.badlogic.gdx.scenes.scene2d.Actor> a = super.getActors();
		actor.setWorld(null);
		a.removeValue(actor, false);

	}

	public void removeObjects(Collection<?> objects) { // si asta merge
		for (Object a : objects) {
			removeObject((Actor) a);
		}
	}

	public List<?> getObjects(java.lang.Class<?> cls) {
		List<greenfoot.Actor> l = new ArrayList<>();
		
		for (com.badlogic.gdx.scenes.scene2d.Actor actor : super.getActors()) {
			if (cls == null || cls.isInstance(actor)) {
				l.add((greenfoot.Actor) actor);
			}
		}
		return l;
	}

	public List<?> getObjectsAt(float x, float y, java.lang.Class<?> cls) { 
		List<greenfoot.Actor> res = new ArrayList<>();
		for (Object a : getObjects(cls)) {
			if (a instanceof Actor) {
				Actor actor = (Actor) a;
				if (	   actor.getX() - actor.iw() / 2 <= x 
						&& actor.getX() + actor.iw() / 2 >= x
						&& actor.getY() - actor.ih() / 2 <= y 
						&& actor.getY() + actor.ih() / 2 >= y) 
					{
					res.add(actor);
				}
			}
		}
		return res;
	}

	public int numberOfActors() {
		return getObjects(Actor.class).size();
	}

	public void setBackground(GreenfootImage image) {
		this.background = image;
	}

	public GreenfootImage getBackground() {
		return this.background;
	}

	public int getCellSize() {
		return cellSize;
	}

	public float getWidth() {
		return (int) wwidth;
	}

	public float getHeight() {
		return (int) wheight;
	}
}
