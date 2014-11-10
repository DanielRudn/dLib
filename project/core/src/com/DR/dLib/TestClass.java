package com.DR.dLib;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestClass extends ApplicationAdapter{
	SpriteBatch batch;
	static Camera camera;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		((OrthographicCamera) camera).setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		dValues.VW = 480;
		dValues.VH = 640;
		dValues.camera = camera;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(236f/256f, 240f/256f, 241f/256f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.end();
	}

}
