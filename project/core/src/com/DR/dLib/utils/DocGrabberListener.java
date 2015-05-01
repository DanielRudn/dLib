package com.DR.dLib.utils;

public interface DocGrabberListener {

	public void onConnected(String docURL, String docText);
	
	public void onConnectionFailed(Throwable t);
	
	public void onConnectionCancelled();
	
}
