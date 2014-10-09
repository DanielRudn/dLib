package com.DR.dLib;

import com.badlogic.gdx.graphics.Camera;

public class dValues {

	public static float VW = 0, VH = 0;
	public static Camera camera;
	
	public void setVirtualDimensions(float w, float h)
	{
		VW = w;
		VH = h;
	}
	
	public void setCamera(Camera cam)
	{
		camera = cam;
	}
}
