package com.DR.dLib.animations;

import com.DR.dLib.dTweener;
import com.DR.dLib.ui.dImage;

public class ShrinkAnimation extends dAnimation {

	protected float startSize = 5f;
	protected float endSize = 0f;
	
	public ShrinkAnimation(dImage objectToAnimate , float duration, AnimationStatusListener listener, int ID, float finalSize,  float startSize) {
		super(duration, listener, ID, objectToAnimate);
		endSize = finalSize;
		this.startSize = startSize;
	}

	@Override
	protected void animate(float time, float duration, float delta) {
		((dImage)getAnimatedObjects()[0]).setDimensions(dTweener.ExponentialEaseOut(time,startSize, endSize - startSize, duration)
				,dTweener.ExponentialEaseOut(time, startSize, endSize - startSize,  duration));
		getAnimatedObjects()[0].setOriginCenter();
	}

}
