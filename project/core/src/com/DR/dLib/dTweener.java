package com.DR.dLib;

public final class dTweener {

	public static float LinearEase(float time, float startPos, float deltaPos, float duration)
	{
		return deltaPos*(time/duration) + startPos;
	}
	
	public static float MoveToAndSlow(float pos, float target, float speed)
	{
		return pos+=((target-pos)*speed);
	}
	
	public static float ExponentialEaseOut(float time ,float startPos , float deltaPos, float duration) {
		return (time==duration) ? startPos+deltaPos : deltaPos * (-(float)Math.pow(2, -10 * time/duration) + 1) + startPos;	
	}
	
	public static float CubicEaseIn(float time, float startPos, float deltaPos, float duration)
	{
		return deltaPos*(time/=duration)*time*time + startPos;
	}
	
	public static float ElasticIn(float time, float startPos, float deltaPos, float duration)
	{
        if (time==0) return startPos;  if ((time/=duration)==1) return startPos+deltaPos;  
        float p=duration*.3f;
        float a=deltaPos; 
        float s=p/4;
        return -(a*(float)Math.pow(2,10*(time-=1)) * (float)Math.sin( (time*duration-s)*(2*(float)Math.PI)/p )) + startPos;
	}
	
	public static float ElasticOut(float time, float startPos, float deltaPos, float duration)
	{
        if (time==0) return startPos;  if ((time/=duration)==1) return startPos+deltaPos;  
        float p=duration*.45f;
        float a=deltaPos; 
        float s=p/4;
        return (a*(float)Math.pow(2,-10*time) * (float)Math.sin( (time*duration-s)*(2*(float)Math.PI)/p ) + deltaPos + startPos); 
	}
	
	//same as above but has parameter for elastic strength
	public static float ElasticOut(float time, float startPos, float deltaPos, float duration, float strength)
	{
        if (time==0) return startPos;  if ((time/=duration)==1) return startPos+deltaPos;  
        float p=duration*.45f;
        float a=deltaPos; 
        float s=p/4;
        return (a*(float)Math.pow(strength,-10*time) * (float)Math.sin( (time*duration-s)*(2*(float)Math.PI)/p ) + deltaPos + startPos); 
	}
	
	public static float BounceOut(float time ,float startPos, float delta, float duration) {
		if ((time/=duration) < (1/2.75f)) {
			return delta*(7.5625f*time*time) + startPos;
		} else if (time < (2/2.75f)) {
			return delta*(7.5625f*(time-=(1.5f/2.75f))*time + .75f) + startPos;
		} else if (time < (2.5/2.75)) {
			return delta*(7.5625f*(time-=(2.25f/2.75f))*time + .9375f) + startPos;
		} else {
			return delta*(7.5625f*(time-=(2.625f/2.75f))*time + .984375f) + startPos;
		}
	}
	
}
