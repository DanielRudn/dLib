package com.DR.dLib.animations;

import com.DR.dLib.dTweener;
import com.DR.dLib.ui.dImage;

public class ShrinkElasticAnimation extends dAnimation {
	
	protected float endSize = 0f;
	protected float startSize = 5f;
	
	public ShrinkElasticAnimation(float duration, AnimationStatusListener listener, int ID, float startSize, float finalSize, dImage... objectsToAnimate)
	{
		super(duration, listener, ID, objectsToAnimate);
		this.startSize = startSize;
		endSize = finalSize;
	}

	@Override
	protected void animate(float time, float duration, float delta)
	{
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			((dImage)getAnimatedObjects()[x]).setDimensions(dTweener.ElasticOut(time, 0, endSize, duration, 6f)
					,dTweener.ElasticOut(time, 0, endSize, duration, 6f));
			getAnimatedObjects()[x].setOriginCenter();
		}
	}
	
	@Override
	public void start()
	{
		super.start();
		for(int x = 0; x < getAnimatedObjects().length; x++)
		{
			((dImage)getAnimatedObjects()[x]).setDimensions(0, 0);
		}
	}

}
