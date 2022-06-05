package greenfoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.game.straferliberator.StraferLiberator;
import com.port.WorldData;

public class World extends com.badlogic.gdx.scenes.scene2d.Stage {

	SpriteBatch batch = StraferLiberator.batcher;
	GreenfootImage background;
	
	
	public World(int worldWidth, int worldHeight, int cellSize, boolean bounded) {

	}

	public void act() {
		super.act();// apeleaza act pt fiecare actor
		Array<com.badlogic.gdx.scenes.scene2d.Actor> l=getActors();
		for(com.badlogic.gdx.scenes.scene2d.Actor a:l) {
			
			((greenfoot.Actor) a).act();
											
		}
	}

	public void addObject(greenfoot.Actor object, float f, float g) {

		if (object.world != null) {
			if (object.world == this) {
				return; // Actor is already in the world
			}
			object.world.removeObject(object);
		}
		super.addActor(object);
		object.setWorld(this);
		object.setLocation(f, g);

	}

	
	
	public void removeObject(greenfoot.Actor object) {
		if (object == null) {
			return;
		}
		//object.clear();
		object.remove();
		//object.setWorld(null);
		//object.addAction(Actions.removeActor());
	}

	public void removeObjects(Collection<? extends Actor> objects) {
		for(com.badlogic.gdx.scenes.scene2d.Actor a:objects) {
			removeObject((Actor) a);
		}
	}

	@SuppressWarnings("unchecked")
	public <A> List<A> getObjects(Class<A> cls) {
		return (List<A>) Arrays.asList(super.getActors());
		
	}
	
	<A> java.util.List<A> getObjectsAt(int x, int y, java.lang.Class<A> cls){
		java.util.List<A> l = (List<A>) getObjects(cls);
		for (Iterator<A> iter = l.iterator(); iter.hasNext();) {
			Actor actor = (Actor) iter.next();
			if(!(actor.getX()==x&&actor.getY()==y)) {
				l.remove(actor);
			}
		}
		return l;
	
	}
	
	public int nrActori() {
		return Arrays.asList(super.getActors()).size();
	}
	
	  public void setBackground(GreenfootImage image) {
		 this.background=image;
	 }
	  public GreenfootImage getBackground() {
		  return this.background;
	  }

	

}
