package com.CometEngine.Renderer.Texture.TextureManager;

import java.util.Hashtable;
import java.util.LinkedList;

import com.CometEngine.Renderer.Texture.Textures.CETexture;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Resrouce.CERawResrouce;

public class CETextureManager {
	private  static final Hashtable<String, CETexture> TextureTable = new Hashtable<String, CETexture>();
	private static final LinkedList<CETexture> glUnLoadedTexture = new LinkedList<CETexture>();
	private static CETextureManager instence = null;
	private static Object sync = new Object();
	
	
	public void putUnloadedTexture(CETexture texture)
	{

		synchronized (sync) {			
		if(texture.getTextureID() == 0)
			glUnLoadedTexture.add(texture);
		}
	}
	
	public void LoadUP_GL_UnloadedTexture()
	{

		synchronized (sync) {		
		if(glUnLoadedTexture.isEmpty() == false)
		{
			for(CETexture texture : glUnLoadedTexture)
			{
			if(texture.isloadup())
			{
				glUnLoadedTexture.remove(texture);
				texture.glLoadTexture();
			}
		}
		}
	
		}
	
	}
	public static CETextureManager getInstence()
	{	
		if(instence ==null)
		{
			return instence = new CETextureManager();
		}
		
		
		return instence;
	}
	
	
	public void showLog()
	{
		System.out.println("Texture Manager : Counter " + TextureTable.size() );
	}

	public void addTexture(String filepath, CETexture texture)
	{	
			TextureTable.put(filepath, texture);	
		
	}
	public boolean isHaveTexture(String path)
	{		
		return TextureTable.containsKey(path);
	}
	public int getSize()
	{
		return instence.getSize(); 
	}
	public CETexture2D getTexture2D(String filepath)
	{
		if(TextureTable.contains(filepath))
			return (CETexture2D)TextureTable.get(filepath);
		else
		{
			CETexture texture  = new CETexture2D(filepath);
			CETextureManager.getInstence().addTexture(filepath, texture);
			return (CETexture2D)texture;
		}
	}
	
}

