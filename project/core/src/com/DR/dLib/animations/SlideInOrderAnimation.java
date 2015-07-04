package com.DR.dLib.animations;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;
import com.badlogic.gdx.math.Vector2;

public class SlideInOrderAnimation extends dAnimation {

	private float deltaX = 0, deltaY = 0;
	private ArrayList<Vector2> startPos;
	// delay between each object
	private final float ITEM_DELAY = .1f;
	
	/**
	 * Slides in from the side in order from top to bottom
	 * @param duration
	 * @param listener
	 * @param ID
	 * @param objectToAnimate
	 */
	public SlideInOrderAnimation(float duration, AnimationStatusListener listener, int ID, float deltaX, dObject... objectToAnimate) 
	{
		super(duration, listener, ID, objectToAnimate);
		this.deltaX = deltaX;
		startPos = new ArrayList<Vector2>();
	}
	
	public SlideInOrderAnimation(float duration, AnimationStatusListener listener, int ID, float deltaX, float deltaY, dObject... objectToAnimate) 
	{
		this(duration, listener, ID, deltaX, objectToAnimate);
		this.deltaY = deltaY;
	}

	@Override
	protected void animate(float time, float duration, float delta) 
	{
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			if(time - x * ITEM_DELAY >= 0)
			{
				getAnimatedObjects()[x].setX(dTweener.ElasticOut(time - x * ITEM_DELAY, startPos.get(x).x, deltaX, duration - x * ITEM_DELAY, 6f));
				getAnimatedObjects()[x].setY(dTweener.ElasticOut(time - x * ITEM_DELAY, startPos.get(x).y, deltaY, duration - x * ITEM_DELAY, 6f));
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
			startPos.add(new Vector2(getAnimatedObjects()[x].getPos()));
		}
	}

}
