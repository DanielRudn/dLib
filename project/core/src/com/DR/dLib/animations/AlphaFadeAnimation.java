package com.DR.dLib.animations;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;

/**
 * Fades objects alpha value from 100% to 0% (1f to 0f).
 */
public class AlphaFadeAnimation extends dAnimation {

	public AlphaFadeAnimation(float duration, AnimationStatusListener listener, int ID, dObject[] objectToAnimate) {
		super(duration, listener, ID, objectToAnimate);
	}

	@Override
	protected void animate(float time, float duration, float delta) {
		for(dObject obj : this.getAnimatedObjects())
		{
			obj.setAlpha(dTweener.LinearEase(time, 1f, -1f, duration));
		}
	}

}
