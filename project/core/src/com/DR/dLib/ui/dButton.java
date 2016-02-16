package com.DR.dLib.ui;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;
import com.DR.dLib.dValues;
import com.DR.dLib.animations.AnimationStatusListener;
import com.DR.dLib.animations.ExpandAnimation;
import com.DR.dLib.animations.dAnimation;
import com.DR.dLib.utils.dUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class dButton extends dObject implements AnimationStatusListener {

	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/

	private boolean enabled = true, clicked = false, pushedScissors = false;
	private dText buttonText;
	private float bWidth, bHeight;
	private dImage clickCircle = null;
	private dAnimation circleAnimation = null;
	private final int CIRCLE_ANIM_ID = 156;
	private Rectangle scissors = new Rectangle(), clip = new Rectangle();
	
	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/
	
	public dButton(float x, float y, Sprite sprite, String text) {
		super(x, y, sprite);
		buttonText = new dText(0,0,18f,text);
		buttonText.setPos(x + getWidth()/2f - buttonText.getWidth()/2f, y + getHeight()/2f - buttonText.getHeight()/2f);
		buttonText.setColor(Color.WHITE);
		bWidth = sprite.getWidth();
		bHeight = sprite.getHeight();
	}
	
	public dButton(float x, float y, Sprite sprite, dText text) {
		super(x, y, sprite);
		buttonText = text;
		bWidth = sprite.getWidth();
		bHeight = sprite.getHeight();
	}
	
	public dButton(float x, float y, Sprite sprite, dText text, Texture circle, float duration)
	{
		super(x,y,sprite);
		buttonText = text;
		bWidth = sprite.getWidth();
		bHeight = sprite.getHeight();
		clickCircle = new dImage(getX(),getY(), circle);
		clickCircle.setColor(.8f,.8f,.8f,0.25f);
//		clickCircle.setOriginCenter();
		clickCircle.setDimensions(0, 0);
		circleAnimation = new ExpandAnimation(clickCircle, duration, this, CIRCLE_ANIM_ID, clickCircle.getColor(), getWidth()*2f);
	}
	
	public dButton(float x, float y, Sprite sprite, String text, Texture circle, float duration)
	{
		super(x, y, sprite);
		buttonText = new dText(0,0,18f,text);
		buttonText.setPos(x + getWidth()/2f - buttonText.getWidth()/2f, y + getHeight()/2f - buttonText.getHeight()/2f);
		buttonText.setColor(Color.WHITE);
		bWidth = sprite.getWidth();
		bHeight = sprite.getHeight();
		clickCircle = new dImage(getX(),getY(), circle);
		clickCircle.setColor(.8f,.8f,.8f,0.25f);
//		clickCircle.setOriginCenter();
		clickCircle.setDimensions(0, 0);
		circleAnimation = new ExpandAnimation(clickCircle, duration, this, CIRCLE_ANIM_ID, clickCircle.getColor(), getWidth()*2f);
	}
	
	public dButton(float x, float y, Texture texture, dText text) {
		super(x, y, texture);
		buttonText = text;
		bWidth = texture.getWidth();
		bHeight = texture.getHeight();
	}
	
	
	public dButton(float x, float y, Texture texture, String text) {
		this(x, y, texture, new dText(0, 0, 18f, text));
	}
	
	public dButton(float x, float y, Texture texture, dText text, Texture circle, float duration) {
		this(x, y, texture, text);
		clickCircle = new dImage(getX(),getY(), circle);
		clickCircle.setColor(.8f,.8f,.8f,0.25f);
		clickCircle.setDimensions(0, 0);
		circleAnimation = new ExpandAnimation(clickCircle, duration, this, CIRCLE_ANIM_ID, clickCircle.getColor(), getWidth()*2f);
	}
	
	public dButton(float x, float y, Texture texture, String text, Texture circle, float duration) {
		this(x, y, texture, new dText(0, 0, 18f, text), circle, duration);
	}



	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	@Override
	public void update(float delta) {
		if(isEnabled())
		{
			checkClicked();
		}
		if(circleAnimation != null && circleAnimation.isActive())
		{
			circleAnimation.update(delta);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if(clickCircle != null)
		{
			batch.flush();
			clip.set(getX(), getY(), getWidth(), getHeight());
			ScissorStack.calculateScissors(dValues.camera, batch.getTransformMatrix(), clip,  scissors);
			pushedScissors = ScissorStack.pushScissors(scissors);
			getSprite().draw(batch);
			buttonText.render(batch);
			clickCircle.render(batch);
			batch.flush();
			if(pushedScissors)
			{
				ScissorStack.popScissors();
			}
		}
		else
		{
			getSprite().draw(batch);
			buttonText.render(batch);
		}
	}
	
	private void checkClicked()
	{
		if(getBoundingRectangle().contains(dUtils.getVirtualMouseX(), dUtils.getVirtualMouseY()) && Gdx.input.justTouched())
		{
			if(circleAnimation != null && circleAnimation.isActive() == false)
			{
				circleAnimation.start();
			}
			clicked = true;
		//	setAlpha(.6f);
		}
		else
		{
			clicked = false;
		//	setAlpha(1f);
		}
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	@Override
	protected void onPositionChanged(float x, float y)
	{
		position.set(x, y);
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	@Override
	public void setAlpha(float a)
	{
		super.setAlpha(a);
		buttonText.setAlpha(a);
	}
	
	public void setText(String text)
	{
		buttonText.setText(text);
	}
	
	public void setTextSize(float size)
	{
		buttonText.setScale(size);
	}
	
	public void setTextColor(Color c)
	{
		buttonText.setColor(c);
	}
	
	public void setWidth(float w)
	{
		bWidth = w;
		this.setScaleX(w/getSprite().getRegionWidth());
	}
	
	public void setHeight(float h)
	{
		bHeight = h;
		setScaleY(h/getSprite().getRegionHeight());
	}
	
	public void setDimensions(float w, float h)
	{
		setWidth(w);
		setHeight(h);
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/
	
	public boolean isClicked()
	{
		return clicked;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	@Override
	public float getWidth()
	{
		return bWidth;
	}
	
	@Override
	public float getHeight()
	{
		return bHeight;
	}
	
	public String getText()
	{
		return buttonText.getText();
	}
	
	/*===========================================================================
	*						 	ANIMATION LISTENER								|
	*===========================================================================*/

	@Override
	public void onAnimationStart(int ID, float duration) {
		if(ID == CIRCLE_ANIM_ID)
		{
			clickCircle.setPos(dUtils.getVirtualMouseX() - getWidth() / 2f, dUtils.getVirtualMouseY() - getHeight());
		}
	}

	@Override
	public void whileAnimating(int ID, float time, float duration, float delta) {
		if(ID == CIRCLE_ANIM_ID)
		{
			if(time < duration/2f)
			{
				clickCircle.setAlpha(dTweener.LinearEase(time, .25f, -.25f, duration/2f));
			}
		}
	}

	@Override
	public void onAnimationFinish(int ID) {
	}
	
}
