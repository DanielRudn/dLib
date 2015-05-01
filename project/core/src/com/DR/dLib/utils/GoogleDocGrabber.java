package com.DR.dLib.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;

/** 
 * Connects to a PUBLIC google doc page and grabs the text
 * @author Daniel
 */
public class GoogleDocGrabber implements HttpResponseListener {
	
	private final String LESS_THAN = "\\u003c", GREATER_THAN = "\\u003e";
	private final String[] symbols = new String[]{"\\u003c", "\\u003e", "\\u003d", "’","\\n", "\\t"};
	private final String[] actual = new String[]{"<", ">","=","'","\n","\t"};

	private String docURL = "";
	private String docText = "";
	
	private boolean connected = false;

	private HttpRequest request = null;
	private DocGrabberListener listener = null;
	
	public GoogleDocGrabber(String url, DocGrabberListener listener)
	{
		this.docURL = url;
		this.listener = listener;
	}
	
	public void connect(int timeout)
	{
		connected = false;
		request = new HttpRequest(HttpMethods.GET);
		request.setUrl(docURL);
		request.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/21.0");
		request.setTimeOut(timeout);
		Gdx.net.sendHttpRequest(request, this);
	}
	
	public void connect()
	{
		connect(5000);
	}
	
	public String getURL()
	{
		return docURL;
	}
	
	public String getText()
	{
		return docText;
	}
	
	public boolean isConnected()
	{
		return connected;
	}
	
	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		docText = httpResponse.getResultAsString();
		docText = docText.substring(docText.indexOf(LESS_THAN + "Shop" + GREATER_THAN), docText.indexOf(LESS_THAN + "/Shop" + GREATER_THAN) + (LESS_THAN + "/Shop" + GREATER_THAN).length());
		for(int x = 0; x < symbols.length; x++)
		{
			docText = docText.replace(symbols[x], actual[x]);
		}
		if(listener != null)
		{
			listener.onConnected(this.docURL, this.docText);
		}
		Gdx.net.cancelHttpRequest(request);
		connected = true;
	}
	
	@Override
	public void failed(Throwable t) {
		if(listener != null)
		{
			listener.onConnectionFailed(t);
		}
	}

	@Override
	public void cancelled() {
		if(listener != null)
		{
			listener.onConnectionCancelled();
		}
	}
}
