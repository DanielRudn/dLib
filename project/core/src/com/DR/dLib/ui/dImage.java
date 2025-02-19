package com.DR.dLib.ui;

import com.DR.dLib.dObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class dImage extends dObject {
	
	// class for images in ui cards, etc..
	
	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/
	
	private float imageWidth, imageHeight;
	private boolean hasShadow = false;
	
	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/

	
	public dImage(Vector2 pos, Texture texture)
	{
		super(pos,texture);
		setUpdatable(false);
		imageWidth = texture.getWidth();
		imageHeight = texture.getHeight();
	}
	
	
	public dImage(float x, float y, Texture texture)
	{
		super(x, y, texture);
		setUpdatable(false);
		imageWidth = texture.getWidth();
		imageHeight = texture.getHeight();
	}
	
	public dImage(float x, float y, TextureRegion region)
	{
		super(x, y, new Sprite(region));
		getSprite().flip(false, true);
		setUpdatable(false);
		imageWidth = region.getRegionWidth();
		imageHeight = region.getRegionHeight();
	}
	
	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	@Override
	public void update(float delta) {}

	@Override
	public void render(SpriteBatch batch) {
		if(hasShadow)
		{
			getSprite().setColor(0,0,0,.25f);
			getSprite().setPosition(getX()+1, getY()+2);
			getSprite().draw(batch);
			getSprite().setColor(getColor());
			getSprite().setPosition(getX(), getY());
			getSprite().draw(batch);
		}
		else
		{
			getSprite().draw(batch);
		}
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setImage(Texture t)
	{
		getSprite().setTexture(t);
	}
	
	public void setImage(TextureRegion t)
	{
		getSprite().setRegion(t);
	}
	
	public void setImage(Sprite t)
	{
		getSprite().set(t);
	}
	
	public void setWidth(float width)
	{
		imageWidth = width;
		setScaleX(width/getSprite().getRegionWidth());
	}
	
	public void setHeight(float height)
	{
		imageHeight = height;
		setScaleY(height/getSprite().getRegionHeight());
	}
	
	public void setDimensions(float width, float height)
	{
		setWidth(width);
		setHeight(height);
	}
	
	public void setHasShadow(boolean shadow)
	{
		hasShadow = shadow;
	}
	
	@Override
	public void setAlpha(float a)
	{
		getSprite().setAlpha(a);
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/
	
	@Override
	public float getWidth()
	{
		return imageWidth;
	}
	
	@Override
	public float getHeight()
	{
		return imageHeight;
	}
	
	public boolean hasShadow()
	{
		return hasShadow;
	}
}
