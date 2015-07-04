package com.DR.dLib.ui;

import com.DR.dLib.dObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class dText extends dObject {
	
	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/
	
	public static BitmapFont GAME_FONT = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font_0.tga"),true);
	private static final float FONT_SIZE = 128f; // size of the bitmap font that was decided on creation of the font.fnt file.
	private String text;
	private float fontScale = FONT_SIZE;
	private boolean shadow = false;
	private boolean multiline = false;
	private Color shadowColor = Color.BLACK;

	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/
	
	public dText(float x, float y, String text) {
		super(x, y);
		this.text = text;
		setColor(Color.BLACK);//default color
		setUpdatable(false);
		GAME_FONT.setMarkupEnabled(true);
		GAME_FONT.setOwnsTexture(true);
		GAME_FONT.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	public dText(float x, float y, float size, String text) {
		this(x, y, text);
		fontScale = size;
	}
	
	public dText(float x, float y, String text, Color c) {
		this(x, y, text);
		setColor(c);
	}
	
	public dText(float x, float y, float size, String text, Color c) {
		this(x, y, size, text);
		setColor(c);
	}
	
	
	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	@Override
	public void update(float delta) {}//this class does not need updating, i know it's bad practice but oh well..

	@Override
	public void render(SpriteBatch batch) {
		GAME_FONT.setScale(fontScale / FONT_SIZE);// ******* might need to change this
		if(multiline)
		{
			if(shadow)
			{
				GAME_FONT.setColor(shadowColor);
				GAME_FONT.drawMultiLine(batch, text, getX()+1, getY()+1);
				GAME_FONT.setColor(getColor());
				GAME_FONT.drawMultiLine(batch, text, getX(), getY());
			}
			else
			{
				GAME_FONT.setColor(getColor());
				GAME_FONT.drawMultiLine(batch, text, getX(), getY());
			}
		}
		else
		{
			if(shadow)
			{
				GAME_FONT.setColor(shadowColor);
				GAME_FONT.draw(batch, text, getX()+1, getY()+1);
				GAME_FONT.setColor(getColor());
				GAME_FONT.draw(batch, text, getX(), getY());
			}
			else
			{
				GAME_FONT.setColor(getColor());
				GAME_FONT.draw(batch, text, getX(), getY());
			}
		}
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	
	public void setShadow(boolean shadow)
	{
		this.shadow = shadow;
	}
	
	public void setMultiline(boolean line)
	{
		multiline = line;
	}
	
	@Override
	public void setScale(float XYScale)
	{
		fontScale = XYScale;
	}
	
	public void setSize(float size)// different name for setScale method..
	{
		setScale(size);
	}
	
	@Override
	public void setPosition(Vector2 pos)
	{
		position = pos;
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		position.set(x, y);
	}
	
	@Override
	public void setPos(Vector2 pos)
	{
		position = pos;
	}
	
	@Override
	public void setPos(float x, float y)
	{
		position.set(x,y);
	}
	
	@Override
	public void setX(float x)
	{
		position.set(x, position.y);
	}
	
	@Override
	public void setY(float y)
	{
		position.set(position.x, y);
	}
	
	@Override
	public void setColor(Color c)
	{
		objColor = c;
	}
	
	@Override
	public void setColor(float r, float g, float b, float a)
	{
		objColor = new Color(r,g,b,a);
	}
	
	@Override
	public void setAlpha(float a)
	{
		objColor.set(objColor.r, objColor.g, objColor.b, a);
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/
	
	@Override
	public float getWidth()
	{
		GAME_FONT.setScale(fontScale / FONT_SIZE);// ******* might need to change this
		if(multiline)
		{
			return 	GAME_FONT.getMultiLineBounds(text).width;
		}
		return GAME_FONT.getBounds(text).width;
	}
	
	@Override
	public float getHeight()
	{
		GAME_FONT.setScale(fontScale / FONT_SIZE);// ******* might need to change this
		if(multiline)
		{
			return 	GAME_FONT.getMultiLineBounds(text).height;
		}
		return GAME_FONT.getBounds(text).height;
	}
	
	public String getText()
	{
		return text;
	}

}
