package com.DR.dLib.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class dToggleCard extends dUICard {
	
	private dToggle toggle;
	private dText text;

	public dToggleCard(float x, float y, Texture texture, Texture toggleBack, Texture toggleSlider, String text) {
		super(x, y, texture);
		this.setDimensions(256f, 64f);
		toggle = new dToggle(0, 0, toggleBack, toggleSlider, true);
		this.text = new dText(0, 0, 48f, text);
		this.text.setColor(Color.BLACK);
		
		this.addObject(this.text, dUICard.LEFT, dUICard.CENTER);
		this.addObject(this.toggle, dUICard.RIGHT, dUICard.CENTER);
	}
	
	// don't call other constructor since we set the dimensions BEFORE adding the objects
	public dToggleCard(float x, float y, Texture texture, Texture toggleBack, Texture toggleSlider, String text, float width, float height) {
		super(x, y, texture);
		this.setDimensions(width, height);
		this.setPadding(16f);
		toggle = new dToggle(0, 0, toggleBack, toggleSlider, true);
		this.text = new dText(0, 0, 48f, text);
		this.text.setColor(Color.BLACK);
		
		this.addObject(this.text, dUICard.LEFT, dUICard.CENTER);
		this.addObject(this.toggle, dUICard.RIGHT, dUICard.CENTER);
	}
	
	public void setToggleColor(Color c)
	{
		toggle.setColor(c);
	}
	
	public void setToggleColor(float r, float g, float b, float a)
	{
		toggle.setColor(r, g, b, a);
	}
	
	public boolean isEnabled()
	{
		return toggle.isEnabled();
	}

}
