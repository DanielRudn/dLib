package com.DR.dLib.ui;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dValues;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class dUICard extends dObject {
	
	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/
	
	private float cardWidth, cardHeight;
	private float paddingTop = 8, paddingLeft = 8;
	private boolean hoverEnabled = false, hasShadow = true, isVisible = true, clickable = false;
	// true when the card is clicked
	private boolean isClicked = false;
	public static Color SHADOW_COLOR = new Color(0,0,0,.25f);
	public static final byte LEFT = 0, CENTER = 1, RIGHT = 2, TOP = 3, BOTTOM = 4, LEFT_NO_PADDING = 5, TOP_NO_PADDING = 6, RIGHT_NO_PADDING = 7, BOTTOM_NO_PADDING = 8;
	
	private float deltaX = 0, deltaY = 0;//distance moved from last position, used for changing pos of objects
	
	private ArrayList<dObject> objects = new ArrayList<dObject>();

	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/
	
	public dUICard(Vector2 pos, Texture texture)
	{
		super(pos,texture);
	}

	public dUICard(Vector2 pos, Sprite sprite)
	{
		super(pos,sprite);
	}
	
	public dUICard(Vector2 pos, Texture texture, float width, float height)
	{
		super(pos,texture);
		cardWidth = width;
		cardHeight = height;
		setDimensions(width,height);
	}
	
	public dUICard(Vector2 pos, Sprite sprite, float width, float height)
	{
		super(pos,sprite);
		cardWidth = width;
		cardHeight = height;
		setDimensions(width,height);
	}
	
	public dUICard(float x, float y, Texture texture)
	{
		super(x,y,texture);
	}
	
	public dUICard(float x, float y, Sprite sprite)
	{
		super(x,y,sprite);
	}
	
	public dUICard(float x, float y, Texture texture, float width, float height)
	{
		super(x,y,texture);
		cardWidth = width;
		cardHeight = height;
		setDimensions(width,height);
	}
	
	public dUICard(float x, float y, Sprite sprite, float width, float height)
	{
		super(x,y,sprite);
		cardWidth = width;
		cardHeight = height;
		setDimensions(width,height);
	}
	
	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	public void addObject(dObject object, byte xAlign, byte yAlign)
	{
		alignObject(object, xAlign, yAlign);
		objects.add(object);
	}
	
	public void addObjectOnTopOf(dObject object, int index)// places object on top of indexes object and aligns x 
	{
		if(index >= 0)
		{
			object.setY(objects.get(index).getY() - objects.get(index).getHeight() - paddingTop*2);
			object.setX(objects.get(index).getX());
			objects.add(object);
		}
	}
	
	public void addObjectUnder(dObject object, int index)// places object under indexes object 
	{
		if(index >= 0)
		{
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() + paddingTop*2);
			object.setX(objects.get(index).getX());
			objects.add(object);
		}
	}
	
	public void addObjectUnder(dObject object, byte xAlign, int index)// places object under indexes object and aligns x 
	{
		if(index >= 0)
		{
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() + paddingTop*2);
			alignObjectX(object, xAlign);
			objects.add(object);
		}
	}
	
	public void addObjectToRightOf(dObject object, int index)// places object to right of indexes object and aligns y
	{
		if(index >= 0)
		{
			object.setX(objects.get(index).getX() + paddingTop + objects.get(index).getWidth());
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() - object.getHeight());
			objects.add(object);
		}
	}
	
	public void addObjectToLeftOf(dObject object, int index)// places object to right of indexes object and aligns y
	{
		if(index >= 0)
		{
			object.setX(objects.get(index).getX() - paddingTop - objects.get(index).getWidth());
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() - object.getHeight());
			objects.add(object);
		}
	}
	
	public void removeObject(int index)
	{
		objects.remove(index);
	}

	@Override
	public void update(float delta) {
		if(isVisible)
		{
			if(hoverEnabled)
			{
				checkHover();
			}
			if(clickable)
			{
				checkClicked();
			}
			for(int x = 0; x < objects.size(); x++)
			{
				if(objects.get(x).isUpdatable())
				{
					objects.get(x).update(delta);
				}
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if(isVisible)
		{
			if(hasShadow)
			{
				getSprite().setColor(SHADOW_COLOR);
				getSprite().setPosition(getX()+4, getY()+4);
				getSprite().draw(batch);
			
				setColor(getColor());
				getSprite().setPosition(getX(),getY());
				getSprite().draw(batch);
			}
			else
			{
				getSprite().draw(batch);
			}
			//render objects contained in card
			for(int x = 0; x < objects.size(); x++)
			{
				objects.get(x).render(batch);
			}
		}
	}
	
	public int getIndexOf(dObject object)//returns index of object in the object list of this card, -1 if not found
	{
		for(int x = 0; x < objects.size(); x++)
		{
			if(object.equals(objects.get(x)))
			{
				return x;
			}
		}
		return -1;
	}
	
	protected void checkHover()
	{
		if(getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY()))
		{
			setAlpha(.25f);
		}
		else
		{
			setAlpha(1f);
		}
	}
	
	private void checkClicked()
	{
		if(getBoundingRectangle().contains(dValues.camera.position.x-dValues.VW/2f + (Gdx.input.getX() / (Gdx.graphics.getWidth() / dValues.VW)),dValues.camera.position.y-dValues.VH/2f + Gdx.input.getY() / (Gdx.graphics.getHeight() / dValues.VH)) && Gdx.input.justTouched())
		{
			isClicked = true;
			setAlpha(.6f);
		}
		else
		{
			isClicked = false;
			setAlpha(1f);
		}
	}
	
	private void alignObjectX(dObject object, byte xAlign)
	{
		//align x
		if(xAlign == LEFT)
		{
			object.setX(getX() + paddingLeft);
		}
		else if(xAlign == LEFT_NO_PADDING)
		{
			object.setX(getX());
		}
		else if(xAlign == CENTER)
		{
			object.setX(getX() + getWidth()/2f - object.getWidth()/2f);
		}
		else if(xAlign == RIGHT)
		{
			object.setX(getX() + getWidth() - object.getWidth() - paddingLeft);
		}
		else if(xAlign == RIGHT_NO_PADDING)
		{
			object.setX(getX() + getWidth() - object.getWidth());
		}
	}
	
	private void alignObjectY(dObject object, byte yAlign)
	{
		//align y 
		if(yAlign == TOP)
		{
			object.setY(getY() + paddingTop);
		}
		else if(yAlign == TOP_NO_PADDING)
		{
			object.setY(getY());
		}
		else if(yAlign == CENTER)
		{
			object.setY(getY() + getHeight()/2f - object.getHeight()/2f);
		}
		else if(yAlign == BOTTOM)
		{
			object.setY(getY() + getHeight() - object.getHeight() - paddingTop);
		}
		else if(yAlign == BOTTOM_NO_PADDING)
		{
			object.setY(getY() + getHeight() - object.getHeight());
		}
	}
	
	private void alignObject(dObject object, byte xAlign, byte yAlign)//gets an objects position relative to the card depending on align attributes
	{
		alignObjectX(object,xAlign);
		alignObjectY(object,yAlign);
	}
	
	protected void updateObjectPosition()
	{
		deltaX = position.x - getSprite().getX();
		deltaY = position.y - getSprite().getY();
		getSprite().setPosition(position.x, position.y);
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).setPos(objects.get(i).getX() + deltaX, objects.get(i).getY() + deltaY);
		}
	}
	
	protected void updateObjectPositionForScale(float width, float height)
	{
		deltaX = width - cardWidth;
		deltaY = height - cardHeight;
		for(int i = 0; i < objects.size(); i++)
		{
			if(objects.get(i).getY() > getHeight()/2f)
			{
				objects.get(i).setPos(objects.get(i).getX() + deltaX, objects.get(i).getY() + deltaY);
			}
		}
	}
	
	public void hide()
	{
		isVisible = false;
	}
	
	public void show()
	{
		isVisible = true;
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setRespondHover(boolean hover)
	{
		hoverEnabled = hover;
	}
	
	public void setHasShadow(boolean shadow)
	{
		hasShadow = shadow;
	}
	
	public void setClickable(boolean click)
	{
		clickable = click;
	}
	
	public void setWidth(float width)
	{
		updateObjectPositionForScale(width, getHeight());
		cardWidth = width;
		setScaleX(width/getSprite().getRegionWidth());
	}
	
	public void setHeight(float height)
	{
		updateObjectPositionForScale(getWidth(), height);
		cardHeight = height;
		setScaleY(height/getSprite().getRegionHeight());
	}
	
	public void setDimensions(float width, float height)
	{
		cardWidth = width;
		cardHeight = height;
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setPosition(Vector2 pos)
	{
		position = pos;
		updateObjectPosition();
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		position.set(x, y);
		updateObjectPosition();
	}
	
	@Override
	public void setPos(Vector2 pos)
	{
		position = pos;
		updateObjectPosition();
	}
	
	@Override
	public void setPos(float x, float y)
	{
		position.set(x,y);
		updateObjectPosition();
	}
	
	@Override
	public void setX(float x)
	{
		position.set(x, position.y);
		updateObjectPosition();
	}
	
	@Override
	public void setY(float y)
	{
		position.set(position.x, y);
		updateObjectPosition();
	}
	
	public void setPaddingTop(float pad)
	{
		paddingTop = pad;
		updateObjectPosition();
	}
	
	public void setPaddingLeft(float pad)
	{
		paddingLeft = pad;
		updateObjectPosition();
	}
	
	public void setPadding(float pad)
	{
		paddingLeft = pad;
		paddingTop = pad;
		updateObjectPosition();
	}
	
	public void setVisible(boolean v)
	{
		isVisible = v;
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/

	public boolean isHoverEnabled()
	{
		return hoverEnabled;
	}
	
	public boolean isClickable()
	{
		return clickable;
	}
	
	public boolean isClicked()
	{
		return isClicked;
	}
	
	@Override
	public float getWidth()
	{
		return cardWidth;
	}
	
	@Override 
	public float getHeight()
	{
		return cardHeight;
	}
	
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public dObject getObject(int index)
	{
		return objects.get(index);
	}
	
	public float getPadding()
	{
		return paddingLeft;
	}
}
