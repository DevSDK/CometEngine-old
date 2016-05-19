package com.CometEngine.Renderer.Texture.Textures;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.CEFileFormat;
import com.CometEngine.FileUtil.CEFileFormat.FILE_FORMAT;
import com.CometEngine.Renderer.CEGL;

public class CETexture2D {
	private int width = 0;
	private int hight = 0;
	
	private int TextureID = 0;
	private String FilePath = "" ;
	private boolean isSeted = false;
	CEFileFormat.FILE_FORMAT format = FILE_FORMAT.CE_NULL;
	private int WRAP_S = CEGL.GL_CLAMP_TO_EDGE;
	private int WRAP_T = CEGL.GL_CLAMP_TO_EDGE;
	private int MIN_FILTER = CEGL.GL_NEAREST;
	private int MAG_FILTER = CEGL.GL_NEAREST;
	
	//Resource POLL »ý°¢Áß...
	private ByteBuffer TextureData = null;
	
	public void setTextureData(ByteBuffer buffer)
	{
		TextureData = buffer;
	}
	public CETexture2D(String FilePath)
	{
		try{
		if(new File(FilePath).isFile())
		{
			throw new IOException("No File In " + FilePath);
		}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
			
		}
		this.FilePath = FilePath;
		
	}
	public String getFilePath()
	{
		return FilePath;
	}
	public boolean isSetting()
	{
		return isSeted;
	}
	
	public void SettingTextureFile(boolean succes, int textureID, int Width, int Height )
	{
		isSeted = succes;
		
	}
	
}

