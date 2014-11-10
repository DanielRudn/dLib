package com.DR.dLib;

public interface TimerListener {

	public void onTimerStart(int TimerID);
	
	public void onTimerTick(int TimerID);
	
	public void onTimerFinish(int TimerID);
	
}
