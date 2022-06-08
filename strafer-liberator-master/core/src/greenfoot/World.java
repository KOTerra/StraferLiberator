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
	int cellSize;
	private float wwidth, wheight;
	
	

	public World(int worldWidth, int worldHeight, int cellSize, boolean bounded) {
		this.cellSize=cellSize;
	}

//
	public void act() {
		super.act();// apeleaza act pt fiecare actor
		Array<com.badlogic.gdx.scenes.scene2d.Actor> l=getActors();
		for(com.badlogic.gdx.scenes.scene2d.Actor a:l) {
			
			((greenfoot.Actor) a).act();
											
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
			object.setWorld(this);
			super.addActor(object);
	
		object.setLocation(initx,inity);
	}

	
	
	public void removeObject(greenfoot.Actor actor) {					//si asta merge
		if (actor == null) {
			return;
		}
		actor.remove();
		Array<com.badlogic.gdx.scenes.scene2d.Actor> a=super.getActors();
		actor.setWorld(null);
		a.removeValue(actor,false);
	}

	public void removeObjects(Collection<? > objects) {						//si asta merge
		for(Object a:objects) {
			removeObject((Actor) a);
		}
	}


	@SuppressWarnings("unchecked")											////asta merge 
	public List<?> getObjects(java.lang.Class<?> cls) {
		List<greenfoot.Actor> l=new ArrayList<>();;
		Array<com.badlogic.gdx.scenes.scene2d.Actor> a=super.getActors();
		for(com.badlogic.gdx.scenes.scene2d.Actor actor:a) {
			if (cls == null || cls.isInstance(actor)) {
				l.add((greenfoot.Actor)actor);
			}
		}
		return l;
		
	}
	
	
	public List<?> getObjectsAt(float x, float y, java.lang.Class<?> cls){		//nu testai
		List<?> l=getObjects(cls);
		for(Object a:l) {
			if(a instanceof Actor) {
				Actor actor=(Actor)a;
				if(actor.getX()==x&&actor.getY()==y) {
					
				}
				else {
					l.remove(actor);
				}
			}
		}
		return l;
	}
	
	
	public int numberOfActors() {
		return getObjects(Actor.class).size();
	}
	
	  public void setBackground(GreenfootImage image) {
		 this.background=image;
	 }
	  public GreenfootImage getBackground() {
		  return this.background;
	  }

	public int getCellSize() {
		return cellSize;
	}
	public	float getWidth() {
		return (int) wwidth;
	}

	public float getHeight() {
		return (int) wheight;
	}

	

}
