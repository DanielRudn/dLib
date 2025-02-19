package com.DR.dLib.animations;

public interface AnimationStatusListener {

	/**
	 * When the animation is about to start, do all your custom initializations here
	 * @param ID the ID of the animation 
	 * @param duration length of animation
	 */
	public void onAnimationStart(int ID, float duration);
	
	/**
	 * Called every time an animation is updated, used for animating elements
	 * @param ID the ID of the animation 
	 * @param time current time of animation
	 * @param duration length of animation
	 * @param delta delta time, time between updates
	 */
	public void whileAnimating(int ID, float time, float duration, float delta);
	
	/**
	 * Called when the animation has finished, start other animations and finalize objects here
	 * @param ID the ID of the animation 
	 */
	public void onAnimationFinish(int ID);
	
}
