package com.DR.dLib.animations;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;

public class SlideElasticAnimation extends dAnimation {

	// the distance (final - initial) all objects will move 
	protected float deltaPosX = 0;
	protected float deltaPosY = 0;
	private ArrayList<Float> startPosX;
	private ArrayList<Float> startPosY;
	
	public SlideElasticAnimation(float duration, AnimationStatusListener listener, int ID, float deltaX, float deltaY,  dObject... objectsToAnimate) {
		super(duration, listener, ID, objectsToAnimate);
		this.deltaPosX = deltaX;
		this.deltaPosY = deltaY;
		startPosX = new ArrayList<Float>();
		startPosY = new ArrayList<Float>();
	}

	@Override
	protected void animate(float time, float duration, float delta) {
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			getAnimatedObjects()[x].setPos(dTweener.ElasticOut(time,startPosX.get(x), deltaPosX, duration, 6f),
					dTweener.ElasticOut(time, startPosY.get(x), deltaPosY, duration, 6f));
		}
	}

	@Override
	public void start()
	{
		super.start();
		startPosX.clear();
		startPosY.clear();
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			startPosX.add(getAnimatedObjects()[x].getX());
			startPosY.add(getAnimatedObjects()[x].getY());
		}
	}
}
