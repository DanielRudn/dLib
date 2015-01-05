package com.DR.dLib;

import java.util.ArrayList;

import com.DR.dLib.ui.dText;
import com.DR.dLib.ui.dUICard;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class TestClass extends ApplicationAdapter implements HttpResponseListener{
	SpriteBatch batch;
	static Camera camera;
	HttpRequest request;
	ArrayList<dUICard> shopItems;
	Texture card;
	Sprite button;
	dUICard itemCard;
	Element shop;
	dText MOTD;
	String response = "";
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		((OrthographicCamera) camera).setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		dValues.VW = 480;
		dValues.VH = 640;
		dValues.camera = camera;
		
	//	final String url = "https://www.dropbox.com/s/efqbnp267p9zfj9/shop.xml?dl=0";
		final String url ="https://docs.google.com/document/d/1fapoD_xnTPEAYMpJUGr9zWHy1vKzDvybvoDcWkcHQOU/edit?usp=sharing";
		
		request = new HttpRequest(HttpMethods.GET);
		request.setUrl(url);
		request.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/21.0");
		Gdx.net.sendHttpRequest(request, this);
		
		shopItems = new ArrayList<dUICard>();
		
		card = new Texture("card.png");
		button = new Sprite(card);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(41f/256f, 128f/256f, 185f/256f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for(int x = 0; x < shopItems.size(); x++)
		{
			shopItems.get(x).render(batch);
			shopItems.get(x).update(Gdx.graphics.getDeltaTime());
		}
		if(itemCard != null)
		{
			//itemCard.render(batch);
			//itemCard.update(Gdx.graphics.getDeltaTime());
		}
		if(MOTD != null)
		{
			MOTD.render(batch);
		}
		batch.end();
	}

	@Override
	public void handleHttpResponse(HttpResponse httpResponse)
	{
		final String LESS_THAN = "\\u003c", GREATER_THAN = "\\u003e";
		final String[] symbols = new String[]{"\\u003c", "\\u003e", "\\u003d", "’","\\n", "\\t"};
		final String[] actual = new String[]{"<", ">","=","'","\n","\t"};
		
		//System.out.println("handle called");
		response = httpResponse.getResultAsString();
		response = response.substring(response.indexOf(LESS_THAN + "Shop" + GREATER_THAN), response.indexOf(LESS_THAN + "/Shop" + GREATER_THAN) + (LESS_THAN + "/Shop" + GREATER_THAN).length());
		for(int x = 0; x < symbols.length; x++)
		{
			response = response.replace(symbols[x], actual[x]);
		}
	//	System.out.print(response);
	//	debugText += "\n" + response;
		Gdx.net.cancelHttpRequest(request);

		// System.out.println("MOTD: " + shop.get("MOTD"));
		
	}
	
	private void autobotsAssemble()
	{
	//	dUICard itemCard = null;
	//	System.out.println("size: " + shop.getChildrenByName("Item").size);
		
		XmlReader reader = new XmlReader();
		
		shop = reader.parse(response);
		Gdx.app.postRunnable(new Runnable()
			{
				@Override
				public void run() {
					MOTD = new dText(25,25,64f,shop.get("MOTD"));
				}
			});
	//	for(int x = 0; x < shop.getChildrenByName("Item").size; x++)
		for(int x = 0; x < 2; x++)
		{
			final Element e = shop.getChildrenByName("Item").get(x);
			final int currentIndex = x;
		//	System.out.println("Item - Name: " + e.get("name") + " ID: " + e.get("id") + " price: " + e.get("price"));
			Gdx.app.postRunnable(new Runnable()
			{
				@Override
				public void run() {
					itemCard = new dUICard(0,0,card);
					itemCard.setClickable(true, new Texture("circle.png"));
					itemCard.setDimensions(128, 128);
					itemCard.setPos(50,150 + currentIndex*150);
					dText id = new dText(0,0,48f,e.get("id"));
					itemCard.addObject(id, dUICard.CENTER, dUICard.TOP);
					dText itemName = new dText(0,0,18f,e.get("name"));
					itemCard.addObject(itemName, dUICard.CENTER, dUICard.CENTER);
					dText price = new dText(0,0,12f,e.get("price"));
					itemCard.addObject(price, dUICard.RIGHT, dUICard.BOTTOM);
					shopItems.add(itemCard); 
				}
				
			});
		//	itemCard.addObject(itemName, dUICard.LEFT, dUICard.TOP);
		}
	
	}

	@Override
	public void failed(Throwable t) {
		// TODO Auto-generated method stub
		System.out.println("failed called  " + t);
	//	debugText+="\nfailed " + t;
	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub
		System.out.println("cancelled called");
		autobotsAssemble();
	//	debugText+="\ncancelled";
	}

}
