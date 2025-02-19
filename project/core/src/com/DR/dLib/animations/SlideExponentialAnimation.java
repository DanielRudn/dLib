package com.DR.dLib.animations;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;

public class SlideExponentialAnimation extends dAnimation {

	private float deltaX = 0, deltaY = 0;
	private ArrayList<Float> startX;
	private ArrayList<Float> startY;
	
	public SlideExponentialAnimation(float duration, AnimationStatusListener listener, int ID, float deltaX, float deltaY, dObject... objectToAnimate) {
		super(duration, listener, ID, objectToAnimate);
		startX = new ArrayList<Float>();
		startY = new ArrayList<Float>();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	@Override
	protected void animate(float time, float duration, float delta) 
	{
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			getAnimatedObjects()[x].setPos(dTweener.ExponentialEaseOut(time, startX.get(x), deltaX, duration),
					dTweener.ExponentialEaseOut(time, startY.get(x), deltaY, duration));
		}
	}
	
	@Override
	public void start()
	{
		super.start();
		startX.clear();
		startY.clear();
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			startX.add(getAnimatedObjects()[x].getX());
			startY.add(getAnimatedObjects()[x].getY());
		}
	}

}
