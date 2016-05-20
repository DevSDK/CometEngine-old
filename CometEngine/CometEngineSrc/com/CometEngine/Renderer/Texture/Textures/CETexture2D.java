package com.CometEngine.Renderer.Texture.Textures;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.CEFileFormat;
import com.CometEngine.FileUtil.CEFileFormat.FILE_FORMAT;
import com.CometEngine.Renderer.CEGL;

public class CETexture2D extends CETexture {
	private int width = 0;
	private int hight = 0;
	

	CEFileFormat.FILE_FORMAT format = FILE_FORMAT.CE_NULL;
	private int WRAP_S = CEGL.GL_CLAMP_TO_EDGE;
	private int WRAP_T = CEGL.GL_CLAMP_TO_EDGE;
	private int MIN_FILTER = CEGL.GL_NEAREST;
	private int MAG_FILTER = CEGL.GL_NEAREST;
	
	CEImage image = null;
	
	public CETexture2D(String file)
	{
		try{
		if(new File(file).isFile())
		{
			throw new IOException("No File In " + file);
		}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
		image = new CEImage(file);	
	}
	
	
	
	public void SettingTextureFile(boolean succes, int textureID, int Width, int Height )
	{
		
	}
	
}

