package com.DR.dLib.animations;

import com.DR.dLib.dObject;

public abstract class dAnimation {

	private float currentTime = 0;
	private float duration = 0;
	private boolean isActive = false;
	private dObject[] animatedObjects;
	private AnimationStatusListener animationListener;
	// used so that the listener can distinguish which animation is being played if it has multiple running at the same time
	private int animationID;

	public dAnimation(float duration, AnimationStatusListener listener, int ID, dObject... objectToAnimate)
	{
		animationListener = listener;
		animatedObjects = objectToAnimate;
		this.duration = duration;
		animationID = ID;
	}
	
	public void update(float delta)
	{
		if(currentTime < duration)
		{
			currentTime+=delta;
			if(animationListener != null)
			{
				animationListener.whileAnimating(animationID, currentTime, duration, delta);
			}
			animate(currentTime, duration, delta);
		}
		else if(currentTime >= duration)
		{
			isActive = false;
			if(animationListener != null)
			{
				animationListener.onAnimationFinish(animationID);
			}
		}
	}
	
	protected abstract void animate(float time, float duration, float delta);
	
	protected dObject[] getAnimatedObjects()
	{
		return animatedObjects;
	}
	
	public void start()
	{
		isActive = true;
		currentTime = 0;
		if(animationListener != null)
		{
			animationListener.onAnimationStart(animationID, duration);
		}
	}
	
	public void stop()
	{
		currentTime = duration;
	}
	
	public float getTime()
	{
		return currentTime;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public boolean isFinished()
	{
		return !isActive && currentTime >= duration;
	}

}
