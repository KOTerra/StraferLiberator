package greenfoot.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.display.StraferLiberator;
import com.game.straferliberator.render.PaintUtilities;

import greenfoot.actor.Actor;
import greenfoot.display.GreenfootImage;

public class World extends com.badlogic.gdx.scenes.scene2d.Stage {

	SpriteBatch batch = StraferLiberator.batcher;
	GreenfootImage background;
	int cellSize; // unitatea de masura pixel^2
	private float wwidth, wheight;

	

	

	public World(int worldWidth, int worldHeight, int cellSize, boolean bounded) {
		this.cellSize = cellSize;
		wwidth = worldWidth;
		wheight = worldHeight;
	}


	public void act() {
		// super.act(1f);// apeleaza act pt fiecare actor
		Array<com.badlogic.gdx.scenes.scene2d.Actor> l = getActors();

		for (int i = 0; i < l.size; i++) {
			Actor a = (greenfoot.actor.Actor) l.items[i];

			a.act();

		}
	}
//

	public void addObject(greenfoot.actor.Actor object, float initx, float inity) {
		if (object.world != null) {
			if (object.world == this) {
				return; // Actor is already in the world
			}
			object.world.removeObject(object);
		}
		object.setWorld(this);
		super.addActor(object);
		addObjectToPaintOrder(object);
		object.setLocation(initx, inity);
	}

	public void removeObject(greenfoot.actor.Actor actor) { // si asta mergess
		if (actor == null) {
			return;
		}
		actor.remove();
		Array<com.badlogic.gdx.scenes.scene2d.Actor> a = super.getActors();
		actor.setWorld(null);
		a.removeValue(actor, false);
<<<<<<< Updated upstream
		PaintUtilities.removeObjectFromPaintOrder(actor);
=======
		//removeObjectFromPaintOrder(actor);
>>>>>>> Stashed changes
	}

	public void removeObjects(Collection<?> objects) { // si asta merge
		for (Object a : objects) {
			removeObject((Actor) a);
		}
	}

	@SuppressWarnings("unchecked") //// asta merge
	public List<?> getObjects(java.lang.Class<?> cls) {
		List<greenfoot.actor.Actor> l = new ArrayList<>();
		;
		Array<com.badlogic.gdx.scenes.scene2d.Actor> a = super.getActors();
		for (com.badlogic.gdx.scenes.scene2d.Actor actor : a) {
			if (cls == null || cls.isInstance(actor)) {
				l.add((greenfoot.actor.Actor) actor);
			}
		}
		return l;
	}

	public List<?> getObjectsAt(float x, float y, java.lang.Class<?> cls) { // nu testai
		List<?> l = getObjects(cls);
		List<greenfoot.actor.Actor> res = new ArrayList<>();
		for (Object a : l) {
			if (a instanceof Actor) {
				Actor actor = (Actor) a;

				Rectangle r = new Rectangle(actor.getX() - actor.iw() / 2, actor.getY() - actor.ih() / 2, actor.iw(),
						actor.ih());

				if (r.contains(new Vector2(x, y))) {
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
