package com.DR.dLib;

import com.DR.dLib.ui.dText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class dValues {

	public static float VW = 0, VH = 0;
	public static Camera camera;
	
	/**
	 * WHEN USING dLIB THIS METHOD MUST BE CALLED IN THE MAIN CLASS onCreate() METHOD IN ORDER TO REINITIALIZE THE FONT.
	 * @param virtualWidth width of the virtual screen.
	 * @param virtualHeight height of the virtual screen.
	 * @param cam games camera.
	 */
	public static void init(float virtualWidth, float virtualHeight, Camera cam)
	{
		setVirtualDimensions(virtualWidth, virtualHeight);
		setCamera(cam);
		dText.GAME_FONT = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font_0.tga"),true);
	}
	
	private static void setVirtualDimensions(float w, float h)
	{
		VW = w;
		VH = h;
	}
	
	private static void setCamera(Camera cam)
	{
		camera = cam;
	}
}
