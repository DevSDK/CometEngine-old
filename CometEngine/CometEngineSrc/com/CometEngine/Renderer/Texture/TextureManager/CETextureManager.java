package com.CometEngine.Renderer.Texture.TextureManager;

import java.util.Hashtable;
import java.util.LinkedList;

import com.CometEngine.Renderer.Texture.Textures.CEImage;
import com.CometEngine.Renderer.Texture.Textures.CETexture;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class CETextureManager {
	private  static final Hashtable<String, CETexture> TextureTable = new Hashtable<String, CETexture>();
	private static CETextureManager instence = null;
	private static Object sync = new Object();
	public static CETextureManager getInstence()
	{
		if(instence ==null)
		{
			return instence = new CETextureManager();
		}
		return instence;
	}
	public void addLoadedTexture(String filepath, CETexture texture)
	{	
		synchronized (sync) {			
			TextureTable.put(filepath, texture);	
		}
	}
	protected boolean isLoadedTexture(String path)
	{
		synchronized (sync) {		
			return TextureTable.containsKey(path);
		}
	}
	
}

