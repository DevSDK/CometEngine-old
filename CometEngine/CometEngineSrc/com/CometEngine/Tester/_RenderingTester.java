package com.CometEngine.Tester;

import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class _RenderingTester {
	CETexture2D texture = null;
	public String filepath;
	public _RenderingTester(String filepath) {
	init(filepath);
	this.filepath = filepath;
	}
	public void init(String filepath)
	{
		
			if(CETextureManager.getInstence().isHaveTexture(filepath))
				texture = CETextureManager.getInstence().getTexture2D(filepath);
			else
			{
				texture = new CETexture2D(filepath);
				CETextureManager.getInstence().addTexture(filepath, texture);				
			}
	}
	public void logging()
	{
		System.out.println(texture.getData().isSetting());
	}
	

}
