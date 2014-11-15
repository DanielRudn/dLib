package com.DR.dLib;

import java.util.ArrayList;

import com.DR.dLib.ui.dGraph;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class TestClass extends ApplicationAdapter{
	SpriteBatch batch;
	static Camera camera;
	private dGraph test;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		((OrthographicCamera) camera).setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		dValues.VW = 480;
		dValues.VH = 640;
		dValues.camera = camera;
		Texture axis = new Texture(Gdx.files.internal("card.png"));
		axis.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	//	test = new dGraph(180,360, axis,720f,480f, "Score Last 5 Games");
		test = new dGraph(100,100, axis,256f,128f, "Score Last 5 Games");
		ArrayList<Vector2> points = new ArrayList<Vector2>();
		points.add(new Vector2(0,MathUtils.random(0, 10000000)));
		points.add(new Vector2(1,MathUtils.random(0, 10000000)));
		points.add(new Vector2(2,MathUtils.random(0, 10000000)));
		points.add(new Vector2(3,MathUtils.random(0, 10000000)));
		points.add(new Vector2(4,MathUtils.random(0, 10000000)));  
		test.setPoints(points);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(41f/256f, 128f/256f, 185f/256f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		test.render(batch);
		batch.end();
	}

}
