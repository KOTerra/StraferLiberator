/**
 * 
 */
package com.game.straferliberator.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.port.actor.Item;
import com.port.actor.npc.Npc;
import com.port.actor.npc.hostile.Droid;
import com.port.actor.npc.items.NpcItem;
import com.port.actor.player.Player;
import com.port.actor.player.items.Lantern;
import com.port.actor.player.items.Light;
import com.port.display.Effect;
import com.port.display.HealthBar;
import com.port.display.HealthBarImg;
import com.port.display.MapMenu;
import com.port.display.Menu;
import com.port.display.Picture;
import com.port.display.Text;
import com.port.display.Tutorial;
import com.port.utils.Buton;
import com.port.utils.Dialog;

/**
 * This class contains all the utilities used for rendering a scene
 * @author TudosieRazvan
 *
 */
public abstract class PaintUtilities {
	
	/**
	 * The standard paint order, saved in an array
	 */
	public static final Class<?>[] paintOrderStandard= {Buton.class, Menu.class, HealthBar.class, Text.class, Picture.class, 
			MapMenu.class, Tutorial.class, Dialog.class, 
			HealthBarImg.class, Effect.class, 
			Item.class, NpcItem.class, Lantern.class, Light.class, 
			Droid.class, Player.class, Npc.class};
	
	
	/**
	 * Nivelul si listele efective trimise la worldRenderer
	 */
	static public HashMap<Integer, List<greenfoot.actor.Actor>> objectsInPaintOrder = new HashMap<Integer, List<greenfoot.actor.Actor>>();
	
	/**
	 * Nivelul la care fiecare clasa e desenata sub sau peste celalalte
	 */
	static public HashMap<Class<?>, Integer> classPaintIndex = new HashMap<Class<?>, Integer>();  
	
	/**
	 * Nr de clase in paint order
	 */
	private static int paintDepth;
	
	
	/**
	 * The list of all the actors in the order of rendering
	 */
	public static ArrayList<greenfoot.actor.Actor> actorsToRenderInOrder=new ArrayList<>();
	
	/**
	 * A boolean which asks wheather or not the actor order was modified
	 */
	public static boolean isActorRenderOrderModified=true;

	public static int getPaintDepth() {
		return paintDepth;
	}

	public static void setPaintDepth(int paintDepth) {
		PaintUtilities.paintDepth = paintDepth;
	}

	/**
	 * This method sets the paint order to some typed classes
	 * @param classes
	 */
	public static void setPaintOrder(Class<?>... classes) {
		int length = classes.length;
		PaintUtilities.setPaintDepth(length);
		for (Class<?> c : classes) {
			PaintUtilities.classPaintIndex.put(c, new Integer(length));
			length--;
		}
	}
	
	
	public static void addObjectToPaintOrder(greenfoot.actor.Actor obj) {
		objectsInPaintOrder.get(classPaintIndex.get(obj.getClass())).add(obj);
	}
	public static void removeObjectFromPaintOrder(greenfoot.actor.Actor obj) {
		objectsInPaintOrder.get(classPaintIndex.get(obj.getClass())).remove(obj);
	}
	
	public HashMap<Class<?>, Integer> getClassPaintIndex() {
		return classPaintIndex;
	}
	public HashMap<Integer, List<greenfoot.actor.Actor>> getObjectsInPaintOrder() {
		return objectsInPaintOrder;
	}
	
	
	public List<greenfoot.actor.Actor> getActorsToRenderInOrder()
	{
		if(!isActorRenderOrderModified) {
			return actorsToRenderInOrder;
		}
		//we need to reorder the paint order
		actorsToRenderInOrder = new ArrayList<>();
		for(int i=0; i<paintOrderStandard.length; i++)
		{
			List<greenfoot.actor.Actor> foundList=objectsInPaintOrder.get(i);
			if(foundList.size()!= 0)
			{
				actorsToRenderInOrder.addAll(foundList);
			}
			
		}
		
		isActorRenderOrderModified=false;
		return actorsToRenderInOrder;
	}
}
