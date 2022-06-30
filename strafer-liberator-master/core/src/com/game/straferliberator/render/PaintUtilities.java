/**
 * 
 */
package com.game.straferliberator.render;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.port.UI.buton.Buton;
import com.port.UI.hud.HealthBar;
import com.port.UI.hud.HealthBarImg;
import com.port.UI.hud.HealthBarPlayer;
import com.port.UI.menu.Dialog;
import com.port.UI.menu.MainMenu;
import com.port.UI.menu.MapMenu;
import com.port.UI.menu.Menu;
import com.port.UI.menu.Tutorial;
import com.port.entity.item.Item;
import com.port.entity.item.npc.NpcItem;
import com.port.entity.item.player.Lantern;
import com.port.entity.item.player.Light;
import com.port.entity.mover.npc.Npc;
import com.port.entity.mover.npc.hostile.Droid;
import com.port.entity.mover.player.Player;
import com.port.utils.graphics.Transition;
import com.port.utils.graphics.Picture;
import com.port.utils.graphics.Text;

import greenfoot.Actor;

/**
 * This class contains all the utilities used for rendering a scene
 * 
 * @author TudosieRazvan
 *
 */
public abstract class PaintUtilities {

	/**
	 * The standard paint order, saved in an array
	 */
	public static Class<?>[] paintOrder = { Buton.class,MainMenu.class, Menu.class, HealthBar.class, Text.class, Picture.class,
			MapMenu.class, Tutorial.class, Dialog.class, HealthBarImg.class,HealthBarPlayer.class, Transition.class, Item.class, NpcItem.class,
			Lantern.class, Light.class, Droid.class, Player.class, Npc.class };

	/**
	 * Nivelul si listele efective trimise la worldRenderer
	 */
	static public HashMap<Integer, ArrayList<greenfoot.Actor>> objectsInPaintOrder = new HashMap<Integer, ArrayList<greenfoot.Actor>>();

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
	public static ArrayList<greenfoot.Actor> actorsToRenderInOrder = new ArrayList<>();

	/**
	 * A boolean which asks wheather or not the actor order was modified
	 */
	public static boolean isActorRenderOrderModified = true;

	public static int getPaintDepth() {
		return paintDepth;
	}

	public static void setPaintDepth(int paintDepth) {
		PaintUtilities.paintDepth = paintDepth;
	}

	/**
	 * This method sets the paint order to some typed classes
	 * 
	 * @param classes
	 */
	public static void setPaintOrder(Class<?>... classes) {
		paintOrder = classes;
		int length = classes.length;
		PaintUtilities.setPaintDepth(length + 1);
		for (Class<?> c : classes) {
			PaintUtilities.classPaintIndex.put(c, length);
			PaintUtilities.objectsInPaintOrder.put(length, new ArrayList<greenfoot.Actor>());

			length--;
		}
		PaintUtilities.objectsInPaintOrder.put(getPaintDepth(), new ArrayList<greenfoot.Actor>());// pt clase nedefinite
																									// in paintorder

	}

	public static void addObjectToPaintOrder(greenfoot.Actor obj) {
		Integer in = classPaintIndex.get(getAbsoluteClassInPaintOrder(obj));
		if (in != null) {

			objectsInPaintOrder.get(in).add(obj);
		} else {
			objectsInPaintOrder.get(getPaintDepth()).add(obj);
		}
		isActorRenderOrderModified = true;

	}

	public static void removeObjectFromPaintOrder(greenfoot.Actor obj) {
		Integer in = classPaintIndex.get(getAbsoluteClassInPaintOrder(obj));
		if (in != null) {
			objectsInPaintOrder.get(in).remove(obj);
		} else {
			objectsInPaintOrder.get(getPaintDepth()).remove(obj);
		}
	}

	public HashMap<Class<?>, Integer> getClassPaintIndex() {
		return classPaintIndex;
	}

	public HashMap<Integer, ArrayList<greenfoot.Actor>> getObjectsInPaintOrder() {
		return objectsInPaintOrder;
	}

	public static List<greenfoot.Actor> getActorsToRenderInOrder() {
		if (!isActorRenderOrderModified) {
			return actorsToRenderInOrder;
		}
		// we need to reorder the paint order
		actorsToRenderInOrder = new ArrayList<>();
		for (int i = 1; i <= getPaintDepth(); i++) {
			ArrayList<greenfoot.Actor> foundList;
			actorsToRenderInOrder.addAll(objectsInPaintOrder.get(i));
		
		}

		isActorRenderOrderModified = false;
		return actorsToRenderInOrder;
	}

	public static Class<?> getAbsoluteClassInPaintOrder(Object obj) {
		
		for (Class<?> cls : paintOrder) {
			if(isOneSubclass(obj.getClass(),cls)) {
				return cls;
			}
			
		}
		return obj.getClass();
	}
	private static boolean isOneSubclass(Class obj, Class<?>cls) {
		if(obj==cls) {
				return true;
		}
		if(obj.getSuperclass()!=null) {

			return isOneSubclass(obj.getSuperclass(),cls);
				
		}
		return false;
		
	}
}
