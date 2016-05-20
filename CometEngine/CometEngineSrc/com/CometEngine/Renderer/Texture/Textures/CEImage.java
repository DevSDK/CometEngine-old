package com.CometEngine.Renderer.Texture.Textures;

import java.nio.ByteBuffer;

public class CEImage {

	private String FilePath = "" ;
	private boolean isSeted = false;
	//Resource POLL »ý°¢Áß...
	private ByteBuffer TextureData = null;
	private static Object sync = new Object();
	
	public String getFilePath()
	{
		return FilePath;
	}
	public boolean isSetting()
	{
		return isSeted;
	}
	public void onTheFlagForSetting()
	{
		synchronized (sync) {
			isSeted = true;			
		}
	}
	public CEImage(String filepath)
	{
		this.FilePath = filepath;
	}
	public void setTextureData(ByteBuffer buffer)
	{
		synchronized(sync){
			TextureData = buffer;			
		}
	}
}
