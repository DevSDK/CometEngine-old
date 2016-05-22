package com.CometEngine.Renderer.Texture.Textures;

public abstract class CETexture {
	protected int TextureID = 0;
	protected boolean isloaded = false;
	public void setTextureID(int texture)
	{
		TextureID = texture;
	}
	
	public int getTextureID()
	{
	
		return TextureID;
	}
	public abstract void glLoadTexture();
	public abstract boolean isloadup();
	
}
