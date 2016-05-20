package com.CometEngine.Renderer.Texture.TextureCacheManager;

import java.util.LinkedList;

import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CEImage;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class CERenderingTextureCacheManager {
	private static final LinkedList<CEImage> notloaded_texture = new LinkedList<CEImage>();
	private static final Object sync = new Object();
	public void addTexture(CEImage texture)
	{
		synchronized (sync) {			
			notloaded_texture.add(texture);
		}
	}
	public void deleteTexture(CEImage texture)
	{
		synchronized (sync) {			
			notloaded_texture.remove(texture);
		}
	}
	public boolean isWaitFileContain(String filepath)
	{
		for(CEImage image : notloaded_texture)
		{
			if(image.getFilePath() == filepath)
				return true;
		}
		return false;
	}
	public void LoadUpGLTexture()
	{
		synchronized(sync)
		{
			for(CEImage texture : notloaded_texture)
			{
				
			}
		}
	}
	
}
