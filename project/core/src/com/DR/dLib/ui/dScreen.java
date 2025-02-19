package com.DR.dLib.ui;

import com.DR.dLib.dValues;
import com.DR.dLib.animations.dAnimation;
import com.badlogic.gdx.graphics.Texture;

public abstract class dScreen extends dUICard {

	private boolean isPaused = false;
	private dAnimation showAnimation;
	private dAnimation hideAnimation;
	
	public dScreen(float x, float y, Texture texture) {
		super(x, y, texture);
		this.setDimensions(dValues.VW, dValues.VH);
		setHasShadow(false);
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		if(showAnimation != null && showAnimation.isActive())
		{
			showAnimation.update(delta);
		}
		else if(hideAnimation != null && hideAnimation.isActive())
		{
			hideAnimation.update(delta);
		}
	}
	
	/**
	 * User clicked back button
	 */
	public abstract void goBack();
	
	/**
	 * Switch screens to provided screen, transitions, etc up to the current screen
	 * @param newScreen screen that will be switched to 
	 */
	public abstract void switchScreen(dScreen newScreen);
	
	public void setShowAnimation(dAnimation show)
	{
		showAnimation = show;
	}

	public void setHideAnimation(dAnimation hide)
	{
		hideAnimation = hide;
	}
	
	public void pause()
	{
		isPaused = true;
	}
	
	@Override
	public void show()
	{
		super.show();
		if(showAnimation != null)
		{
			showAnimation.start();
		}
	}
	
	@Override
	public void hide()
	{
		if(hideAnimation != null)
		{
			hideAnimation.start();
		}
		else
		{
			super.hide();
		}
	}
	
	public void resume()
	{
		isPaused = false;
	}
	
	public boolean isPaused()
	{
		return isPaused;
	}
}

