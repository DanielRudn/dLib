package com.DR.dLib.animations;

import com.DR.dLib.dTweener;
import com.DR.dLib.dValues;
import com.DR.dLib.ui.dImage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class ExpandAnimation extends dAnimation {
	
	protected float endSize = 5f;
	
	public ExpandAnimation(dImage objectToAnimate ,float duration, AnimationStatusListener listener, int ID, Color c, float finalSize)
	{
		super(duration, listener, ID, objectToAnimate);
		endSize = finalSize;
		getAnimatedObjects()[0].setColor(c);
	}

	@Override
	protected void animate(float time, float duration, float delta)
	{
		((dImage)getAnimatedObjects()[0]).setDimensions(dTweener.ExponentialEaseOut(time, 0, endSize, duration)
				,dTweener.ExponentialEaseOut(time, 0, endSize, duration));
		getAnimatedObjects()[0].setOriginCenter();
	}
	
	@Override
	public void start()
	{
		super.start();
		getAnimatedObjects()[0].setPos(dValues.camera.position.x-dValues.VW/2f + (Gdx.input.getX() / (Gdx.graphics.getWidth() / dValues.VW)),dValues.camera.position.y-dValues.VH/2f + Gdx.input.getY() / (Gdx.graphics.getHeight() / dValues.VH));
		((dImage)getAnimatedObjects()[0]).setDimensions(0, 0);
	}

}
