package com.CometEngine.Renderer.Texture.TextureCacheManager;

import java.util.LinkedList;

import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class CERenderingTextureCacheManager {
	private static final LinkedList<CETexture2D> notloaded_texture = new LinkedList<CETexture2D>();
	private static final Object sync = new Object();
	public void addTexture(CETexture2D texture)
	{
		synchronized (sync) {			
			notloaded_texture.add(texture);
		}
	}
	public void deleteTexture(CETexture2D texture)
	{
		synchronized (sync) {			
			notloaded_texture.remove(texture);
		}
	}
	public void LoadUpGLTexture()
	{
		synchronized(sync)
		{
			for(CETexture2D texture : notloaded_texture)
			{
				if(CETextureManager.getInstence().isLoadedTexture(texture.getFilePath()));
				 	continue;
				
			}
		}
	}
	
}
