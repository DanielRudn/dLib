package com.DR.dLib.animations;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;

public class SlideInOrderAnimation extends dAnimation {

	private float deltaX = 0;
	private ArrayList<Float> startPos;
	// delay between each object
	private final float ITEM_DELAY = .1f;
	
	/**
	 * Slides in from the side in order from top to bottom
	 * @param duration
	 * @param listener
	 * @param ID
	 * @param objectToAnimate
	 */
	public SlideInOrderAnimation(float duration, AnimationStatusListener listener, int ID, float deltaX, dObject[] objectToAnimate) 
	{
		super(duration, listener, ID, objectToAnimate);
		this.deltaX = deltaX;
		startPos = new ArrayList<Float>();
	}

	@Override
	protected void animate(float time, float duration, float delta) 
	{
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			if(time - x * ITEM_DELAY >= 0)
			{
				getAnimatedObjects()[x].setX(dTweener.ElasticOut(time - x * ITEM_DELAY, startPos.get(x), deltaX, duration - x * ITEM_DELAY, 6f));
			}
		}
	}
	
	@Override
	public void start()
	{
		super.start();
		startPos.clear();
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			startPos.add(getAnimatedObjects()[x].getX());
		}
	}

}
