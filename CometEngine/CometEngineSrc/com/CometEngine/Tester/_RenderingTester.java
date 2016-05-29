package com.CometEngine.Tester;

import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class _RenderingTester {
	CETexture2D texture = null;
	public String filepath;
	public CETexture2D getTexrure()
	{
		return texture;
	}
	public _RenderingTester(String filepath) {
		this.filepath = filepath;
		init();
		
	}
	public void init()
	{
		texture = CETexture2D.CreateTexture2D(filepath);
	}
	public void logging()
	{
		System.out.println(texture.getData().isSetting());
	}
	

}
