package greenfoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.game.straferliberator.StraferLiberator;
import com.port.WorldData;

public class World extends com.badlogic.gdx.scenes.scene2d.Stage {

	SpriteBatch batch = StraferLiberator.batcher;
	GreenfootImage background;
	
	
	public World(int worldWidth, int worldHeight, int cellSize, boolean bounded) {

	}

	public void act() {
		super.act();// apeleaza act pt fiecare actor
	}

	public void addObject(greenfoot.Actor object, float f, float g) {

		if (object.world != null) {
			if (object.world == this) {
				return; // Actor is already in the world
			}
			object.world.removeObject(object);
		}
		super.addActor(object);
		object.setLocation(f, g);

	}

	public void removeObject(greenfoot.Actor object) {
		if (object == null || object.world != this) {
			return;
		}
		object.remove();
	}

	public void removeObjects(Collection<? extends Actor> objects) {
		for (Iterator<? extends Actor> iter = objects.iterator(); iter.hasNext();) {
			Actor actor = iter.next();
			removeObject(actor);
		}
	}

	@SuppressWarnings("unchecked")
	public <A> List<A> getObjects(Class<A> cls) {
		return (List<A>) Arrays.asList(super.getActors());
	}
	
	  public void setBackground(GreenfootImage image) {
		 this.background=image;
	 }
	  public GreenfootImage getBackground() {
		  return this.background;
	  }

	

}
