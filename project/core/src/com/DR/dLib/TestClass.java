package com.DR.dLib;


import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	dUICard testCard, playerPanel;
	static Camera camera;
	float currentTime = 0;
	Texture ball, card;
	boolean drawCircle = false;
	dImage circleCover;
	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera( Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		((OrthographicCamera) camera).setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		dValues.VW = 480;
		dValues.VH = 640;
		dValues.camera = camera;
		
		ball = new Texture("ball.png");
		ball.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		card = new Texture("card.png");
		
		testCard = new dUICard(0,0,card);
		testCard.setDimensions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		testCard.setHasShadow(false);
		
		circleCover = new dImage(0,0,ball);
		circleCover.setColor(new Color(46f/256f, 204f/256f, 113f/256f,1f));
		circleCover.setDimensions(0, 0);
		
		playerPanel = new dUICard(32f,32f, card);
		playerPanel.setDimensions(Gdx.graphics.getWidth() - 64f, 92f);
		playerPanel.setPadding(12f);
		dImage ball1 = new dImage(0,0,ball);
		ball1.setHasShadow(true);
		ball1.setDimensions(64f, 64f);
		ball1.setColor(Color.TEAL);
		dText playerName = new dText(0,0,32f,"First Last");
		playerName.setColor(Color.BLACK);
		playerPanel.addObject(ball1, dUICard.LEFT, dUICard.CENTER);
		playerPanel.addObject(playerName, dUICard.CENTER, dUICard.CENTER);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(236f/256f, 240f/256f, 241f/256f,1f);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
		testCard.update(Gdx.graphics.getDeltaTime());
/*
		if(Gdx.input.justTouched())
		{
			this.drawCircle = true;
			currentTime = 0;
			circleCover.setOriginCenter();
			circleCover.setScale(0.01f);
			circleCover.setPosition(Gdx.input.getX(), Gdx.input.getY());
		}
		
		if(drawCircle)
		{
			if(currentTime <= 2f)
			{
				currentTime+=Gdx.graphics.getDeltaTime();
				circleCover.setDimensions(dTweener.ExponentialEaseOut(currentTime, 0, Gdx.graphics.getHeight() * 2f, 2f), dTweener.ExponentialEaseOut(currentTime, 0, Gdx.graphics.getHeight() * 2f, 2f));
				circleCover.setOriginCenter();
			}
		}
*/		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		testCard.render(batch);
		circleCover.render(batch);
		playerPanel.render(batch);
		batch.end();
	}
}
