package com.DR.dLib.utils;

import com.DR.dLib.dValues;
import com.badlogic.gdx.Gdx;

public class dUtils {
	
	public static float normalize(float value, float min, float max)
	{
		return (value - min) / (max - min);
	}

	public static float getVirtualMouseX()
	{
		return dValues.camera.position.x - dValues.VW / 2f + (Gdx.input.getX() / (Gdx.graphics.getWidth() / dValues.VW));
	}
	
	public static float getVirtualMouseY()
	{
		return dValues.camera.position.y - dValues.VH / 2f + Gdx.input.getY() / (Gdx.graphics.getHeight() / dValues.VH);
	}
	
}
