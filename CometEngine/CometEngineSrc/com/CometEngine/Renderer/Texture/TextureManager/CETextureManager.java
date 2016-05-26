package com.CometEngine.Renderer.Texture.TextureManager;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Texture.Textures.CETexture;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Resrouce.CERawResrouce;
import com.CometEngine.Resrouce.CEResourceManager;

public class CETextureManager {
	private  static final Hashtable<String, CETexture> TextureTable = new Hashtable<String, CETexture>();
	private static final LinkedList<CETexture> glUnLoadedTexture = new LinkedList<CETexture>();
	private static final LinkedList<CETexture> glRemoverTexture = new LinkedList<CETexture>();
	private static CETextureManager instence = null;
	private static Object sync = new Object();
	
	
	public void putUnloadedTexture(CETexture texture)
	{
		synchronized (sync) {
			
			if(texture.getTextureID() == 0)
				glUnLoadedTexture.add(texture);
			
		}
	}

	public void DeleteTexture(CETexture texture)
	{
		synchronized(sync) {	
		
			if(glRemoverTexture.contains(texture) == false)
			{
				glRemoverTexture.add(texture);
			}
			
		}
	}
	
	

	
	public void LoadUP_GL_AllLoadUP()
	{

		synchronized (sync) {		
			LoadUPTextures();
			RemoveUPTextures();
	  }
	}
	private void LoadUPTextures()
	{
		if(glUnLoadedTexture.isEmpty() == false)
		{ 	
			CETexture tex  = null;
			Iterator<CETexture> iter = glUnLoadedTexture.iterator();
				while(iter.hasNext())
				{
					tex = iter.next();
					if(tex.isloadup())
					{
						iter.remove();
						tex.glLoadTexture();
					}	
				}
		}
	}
	private void RemoveUPTextures()
	{
		if(glRemoverTexture.isEmpty() == false)
		{
			CETexture tex = null;
			Iterator<CETexture> iter = glRemoverTexture.iterator();
			
			while(iter.hasNext())
			{
				tex = iter.next();
				if(tex.isloadup())
				{
					if(tex.getTextureID() > 0)
					{	
						TextureTable.remove(tex.getHashKey());
						CEGL.DeleteTextures(tex.getTextureID());
						
						iter.remove();
						
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
	
	
	public synchronized void showLog()
	{
		System.out.println("Texture Manager : Counter " + TextureTable.size() );
		for(CETexture tex : TextureTable.values())
		{
			System.out.println(tex.getTextureID());
		}
	}

	public void addTexture(String filepath, CETexture texture)
	{	
		synchronized (sync) {
			
			TextureTable.put(filepath, texture);	
		}
		
	}
	public synchronized boolean isHaveTexture(String path)
	{		
		return TextureTable.containsKey(path) ;
	}
	public int getSize()
	{
		return instence.getSize(); 
	}
	public CETexture2D getTexture2D(String filepath)
	{
		synchronized (sync) {
			if(TextureTable.containsKey(filepath))
				return (CETexture2D)TextureTable.get(filepath);
			
			    return CETexture2D.CreateTexture2D(filepath);
		}
			
	}
	
}

