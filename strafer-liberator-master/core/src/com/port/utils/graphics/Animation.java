package com.port.utils.graphics;

import greenfoot.*;

import java.util.Arrays;
import java.util.Collections;

/*
 * O clasa ajutatoare 
 * ruleaza un gif o singura data prin toate frame-urile
 */

public class Animation {

	// private Object animated; // the World or Actor object that to be animated

	private GreenfootImage[] frames; // the image set currently being used for this animated object
	private int cycleActs; // number of acts to complete a cycle of images
	private boolean active; // on-off switch
	private int timer; // internal timer

	private int cycleCount; // to count animation cycles
	private int cyclesToRun = 1; // number of times the animation is to cycle through its images
	private int scalar;

	/**
	 * creates an animation object that uses the given images for the given object;
	 *
	 * @param object the object to be animated
	 * @param imgSet the images to be used in the animation; a 'null' value or an
	 *               empty array will invalidate the animation
	 */
	public Animation(GreenfootImage[] imgSet) {
		scalar = 1;
		frames = imgSet;

	}

	/**
	 * sets the number of times the animation will cycle through its images
	 *
	 * @param count the number of times to cycle through the images of the animation
	 */
	public void setCycleCount(int count) {
		cyclesToRun = count < 0 ? 0 : count;
	}

	/**
	 * returns the images currently set to be used in the animation
	 *
	 * @return the array of images currently set to the animation
	 */
	public GreenfootImage[] getFrames() {
		return frames;
	}

	/**
	 * sets the speed regulator value (the time, in act cycles, it takes for the set
	 * of images to be shown once)
	 *
	 * @param acts the number of act cycles to complete one animation cycle in
	 */
	public void setCycleActs(int acts) {
		cycleActs = (acts < 0 ? 0 : acts);
	}

	/**
	 * returns the speed regulator value
	 *
	 * @return the number of act cycles that one animation cycle is currently set to
	 *         run in
	 */
	public int getCycleActs() {
		return cycleActs;
	}

	/**
	 * returns the active state of the animation
	 *
	 * @return the current active state the animation is currrently in
	 */
	/// public boolean isActive() {
	// return active;
	// }
	public int getCyclesToRun() {
		return cyclesToRun;
	}

	/**
	 * reverses the order of images in the animation set (allows the animation to be
	 * run backwards)
	 */
	public void reverseImageOrder() {
		Collections.reverse(Arrays.asList(frames));
	}

	public int getScalar() {
		return scalar;
	}

	public void setScalar(int scalar) {
		this.scalar = scalar;
	}

}
