package com.CometEngine.Renderer.Texture.Textures;

import java.nio.IntBuffer;

public abstract class CETexture {
	protected int TextureID = 0;
	protected boolean isloaded = false;
	protected String HashKey = "";
	public void setTextureID(int texture)
	{
		TextureID = texture;
	}
	public void setHashKey(String key)
	{
		HashKey = key;
	}
	public String getHashKey()
	{
		return HashKey;
	}
	public int getTextureID()
	{
		return TextureID;
	}
	public abstract void glLoadTexture();
	public abstract boolean isloadup();
	public abstract String getKey();
}
