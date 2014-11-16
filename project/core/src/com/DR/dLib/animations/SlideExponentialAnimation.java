package com.DR.dLib.animations;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;
import com.badlogic.gdx.math.Vector2;

public class SlideExponentialAnimation extends dAnimation {

	private float deltaX = 0, deltaY = 0;
	private ArrayList<Vector2> startPos;
	
	public SlideExponentialAnimation(float duration, AnimationStatusListener listener, int ID, float deltaX, float deltaY, dObject... objectToAnimate) {
		super(duration, listener, ID, objectToAnimate);
		startPos = new ArrayList<Vector2>();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	@Override
	protected void animate(float time, float duration, float delta) 
	{
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			getAnimatedObjects()[x].setPos(dTweener.ExponentialEaseOut(time, startPos.get(x).x, deltaX, duration),
					dTweener.ExponentialEaseOut(time, startPos.get(x).y, deltaY, duration));
		}
	}
	
	@Override
	public void start()
	{
		super.start();
		startPos.clear();
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			startPos.add(getAnimatedObjects()[x].getPos());
		}
	}

}
