package com.DR.dLib.ui;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;
import com.DR.dLib.dValues;
import com.DR.dLib.animations.AnimationStatusListener;
import com.DR.dLib.animations.SlideElasticAnimation;
import com.DR.dLib.animations.dAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class dToggle extends dObject implements AnimationStatusListener {

	private dImage slider;
	private boolean enabled = false;
	private dAnimation slideAnim;
	private Color toggleColor = Color.WHITE;
	
	public dToggle(float x, float y, Texture backTexture, Texture sliderTexture) {
		super(x,y, backTexture);
		getSprite().setSize(64f, 24f);
		slider = new dImage(x,y, sliderTexture);
		slider.setDimensions(32f, 32f);
		slider.setY(y + 12f - slider.getHeight()/2f);
		slider.setHasShadow(true);
		enabled = false;
	}
	
	public dToggle(float x, float y, Texture backTexture, Texture sliderTexture, boolean enabled) {
		this(x,y, backTexture, sliderTexture);
		this.enabled = enabled;
		if(enabled)
		{
			slider.setX(slider.getX() + getWidth() - slider.getWidth());
		}
	}
	
	@Override
	public void render(SpriteBatch batch) 
	{
		getSprite().draw(batch);
		slider.render(batch);
	}

	@Override
	public void update(float delta)
	{
		if(getBoundingRectangle().contains(dValues.camera.position.x-dValues.VW/2f + (Gdx.input.getX() / (Gdx.graphics.getWidth() / dValues.VW))
						,dValues.camera.position.y-dValues.VH/2f + Gdx.input.getY() / (Gdx.graphics.getHeight() / dValues.VH)) && Gdx.input.justTouched())
		{
			if(slideAnim == null)
			{
				setEnabled(!enabled);
			}
			else if(slideAnim.isActive() == false)
			{
				setEnabled(!enabled);
			}
		}
		if(slideAnim != null && slideAnim.isActive())
		{
			slideAnim.update(delta);
		}
	}
	
	@Override
	public void setPosition(Vector2 pos)
	{
		super.setPosition(pos);
		updateSliderPosition(pos.x,pos.y);
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		super.setPosition(x, y);
		updateSliderPosition(x,y);
	}
	
	@Override
	public void setPos(Vector2 pos)
	{
		super.setPos(pos);
		updateSliderPosition(pos.x, pos.y);
	}
	
	@Override
	public void setPos(float x, float y)
	{
		super.setPos(x, y);
		updateSliderPosition(x, y);
	}
	
	@Override
	public void setX(float x)
	{
		super.setX(x);
		updateSliderPosition(x, getY());
	}
	
	@Override
	public void setY(float y)
	{
		super.setY(y);
		updateSliderPosition(getX(), y);
	}
	
	@Override
	public void setColor(Color c)
	{
		super.setColor(c);
		slider.setColor(c.r + .15f, c.g + 0.15f, + c.b + 0.15f, c.a);
		if(slideAnim == null || slideAnim.isActive() == false)
		{
			toggleColor = c;
		}
	}
	
	@Override
	public void setColor(float r, float g, float b, float a)
	{
		super.setColor(r, g, b, a);
		slider.setColor(r + .15f, g + 0.15f, b+.15f,a);
		if(slideAnim == null || slideAnim.isActive() == false)
		{
			toggleColor = new Color(r,g,b,a);
		}
	}

	private void setEnabled(boolean enabled)
	{
		if(this.enabled != enabled) // only do an animation if the value is not the same as the current value
		{
			if(enabled) // slide right
			{
				slideAnim = new SlideElasticAnimation(1.5f, this, 0, getWidth() - slider.getWidth(), 0, slider);
			}
			else // slide left
			{
				slideAnim = new SlideElasticAnimation(1.5f, this, 0, -getWidth() + slider.getWidth(), 0, slider);
			}
			slideAnim.start();
		}
		this.enabled = enabled;
	}
	
	/**
	 * When this objects position is changed, this method is called to move the slider with the background
	 */
	private void updateSliderPosition(float x, float y)
	{
		if(enabled)
		{
			slider.setPosition(x + getWidth() - slider.getWidth(), y + 12f - slider.getHeight()/2f);
		}
		else
		{
			slider.setPosition(x, y + 12f - slider.getHeight()/2f);
		}
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	@Override
	public void onAnimationStart(int ID, float duration) {}

	@Override
	public void whileAnimating(int ID, float time, float duration, float delta) {
		if(ID == 0)
		{
			if(enabled && time < duration/4f)
			{
				setColor(dTweener.LinearEase(time, Color.GRAY.r, toggleColor.r - Color.GRAY.r, duration/4f),
						dTweener.LinearEase(time, Color.GRAY.g, toggleColor.g - Color.GRAY.g, duration/4f),
						dTweener.LinearEase(time, Color.GRAY.b, toggleColor.b - Color.GRAY.b, duration/4f),
						1f);
			}
			else if(enabled == false && time < duration/4f)
			{
				setColor(dTweener.LinearEase(time, toggleColor.r, Color.GRAY.r - toggleColor.r, duration/4f),
						dTweener.LinearEase(time, toggleColor.g, Color.GRAY.g - toggleColor.g, duration/4f),
						dTweener.LinearEase(time, toggleColor.b, Color.GRAY.b - toggleColor.b, duration/4f),
						1f);
			}
		}	
	}

	@Override
	public void onAnimationFinish(int ID) {}

}
