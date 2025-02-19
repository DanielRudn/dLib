package com.DR.dLib;

public class dTimer {

	private float currentTime = 0;
	private float duration;
	private float tickTime;
	private float tickCounter = 0;
	private TimerListener listener;
	private boolean hasStarted = false;
	private boolean hasTicked = false;
	private int ID;
	
	/**
	 * A timer object 
	 * @param duration amount of time the timer runs
	 * @param tickTime after every tickTime, the listeners onTick method is called, I.E. if tickTime is one, then onTick is called once per second
	 * @param listener listener for the timer, can be null
	 */
	public dTimer(float duration, float tickTime, int ID, TimerListener listener)
	{
		this.duration = duration;
		this.tickTime = tickTime;
		this.listener = listener;
		this.ID = ID;
		currentTime = 0;
	}
	
	public void update(float delta)
	{
		if(hasStarted && currentTime < duration)
		{
			hasTicked = false;
			if(tickCounter - tickTime > delta)
			{
				hasTicked = true;
				if(listener != null)
				{
					listener.onTimerTick(ID);
				}
				// reset tickCounter
				tickCounter = delta;
			}
			// increment counters
			currentTime+=delta;
			tickCounter+=delta;
		}
		else if(hasStarted && currentTime >= duration)
		{
			finish();
		}
	}
	
	public void start()
	{
		hasStarted = true;
		currentTime = 0;
		tickCounter = 0;
		hasTicked = false;
		if(listener != null)
		{
			listener.onTimerStart(ID);
		}
	}
	
	public void stop()
	{
		finish();
	}
	
	public boolean isRunning()
	{
		return hasStarted;
	}
	
	public float getTime()
	{
		return currentTime;
	}
	
	public float getDuration()
	{
		return duration;
	}
	
	public float getTickTime()
	{
		return tickTime;
	}
	
	public boolean hasTicked()
	{
		return hasTicked;
	}
	
	private void finish()
	{
		hasStarted = false;
		if(listener != null)
		{
			listener.onTimerFinish(ID);
		}
	}
}
