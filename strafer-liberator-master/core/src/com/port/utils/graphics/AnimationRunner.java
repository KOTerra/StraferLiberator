package com.port.utils.graphics;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * runs a given animation in a given animated object\
 * @author stoica_mihai
 *
 */
public class AnimationRunner {

	Object animated;
	Animation animation;
	private GreenfootImage[] frames;
	private int timer = 0;
	private int cycleCount = 0;
	private boolean active; // on-off switch
	private int scalar;

	public AnimationRunner(Object animated, Animation animation) {

		this.animated = animated;
		this.animation = animation;
		frames = this.animation.getFrames();
		this.scalar = animation.getScalar();
		if (frames != null && frames.length != 0) {
			setFrame(0);
		}
	}

	/**
	 * if animating, runs the timer and updates the image when needed
	 *
	 * @return a flag indicating the current animation cycle has completed
	 */
	int tmr = 0;

	public void run() {
		tmr++;
		if (tmr >= scalar) {
			tmr = 0;
			if (animation.getCycleActs() == 0 || !active) {
				return;
			}

			int inFrame = timer * frames.length / animation.getCycleActs();
			timer = (timer + 1) % animation.getCycleActs();
			int outFrame = timer * frames.length / animation.getCycleActs();
			if (inFrame != outFrame) {
				setFrame(outFrame);
			}

		}
		if (timer == 0 && animation.getCyclesToRun() > 0 && (++cycleCount == animation.getCyclesToRun() * scalar)) {
			cycleCount = 0;
			active = false;
		}
	}

	public void setFrame(int index) {
		if (animated instanceof Actor) {
			((Actor) animated).setImage(frames[index]);
		} else if (animated instanceof World) {
			((World) animated).setBackground(frames[index]);
		}
	}

	/**
	 * sets the active state of the animation; it is only set active if field values
	 * are set properly
	 *
	 * @param state a true/false value indicating which active state to set the
	 *              animation in
	 */
	public void setActiveState(boolean state) {

		if (frames == null || frames.length == 0) {
			return;
		}
		active = state;
	}

	/**
	 * returns the active state of the animation
	 *
	 * @return the current active state the animation is currrently in
	 */
	public boolean isActive() {
		return active;
	}
}
