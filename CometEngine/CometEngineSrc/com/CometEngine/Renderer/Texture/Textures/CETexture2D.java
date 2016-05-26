package com.CometEngine.Renderer.Texture.Textures;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.newdawn.slick.opengl.PNGDecoder;

import com.CometEngine.FileUtil.CEFileFormat;
import com.CometEngine.FileUtil.CEFileFormat.FILE_FORMAT;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEImageResrouceLoader;
import com.CometEngine.Resrouce.CERawImageResrouce;
import com.CometEngine.Resrouce.CERawResrouce;
import com.CometEngine.Resrouce.CEResourceManager;


public class CETexture2D extends CETexture {
	private int width = 0;
	private int hight = 0;
	

	CEFileFormat.FILE_FORMAT format = FILE_FORMAT.CE_NULL;
	private int WRAP_S = CEGL.GL_CLAMP_TO_EDGE;
	private int WRAP_T = CEGL.GL_CLAMP_TO_EDGE;
	private int MIN_FILTER = CEGL.GL_NEAREST;
	private int MAG_FILTER = CEGL.GL_NEAREST;
	
	CERawImageResrouce image = null;
	
	public CERawImageResrouce getData()
	{
		return image;
	}
	private CETexture2D(String file)
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
	}
	
	public static CETexture2D CreateTexture2D(String FilePath)
	{	
		if(CETextureManager.getInstence().isHaveTexture(FilePath))
		{
			return CETextureManager.getInstence().getTexture2D(FilePath);
		}
		
		CETexture2D texture = new CETexture2D(FilePath);
		CERawImageResrouce image = null;
		CETextureManager.getInstence().addTexture(FilePath, texture);
		
		if(CEResourceManager.getInstence().isData(FilePath))
		{
			System.out.println("IS LOADED");
			image = CEResourceManager.getInstence().getResoruce(FilePath);
			texture.setImageReousrce(image);
			CETextureManager.getInstence().putUnloadedTexture(texture);
		}
		else
		{
			System.out.println("IS UNLOADED");
			image = new CERawImageResrouce(FilePath);
			CEResourceManager.getInstence().putResoruceData(image);			
			CEImageResrouceLoader.getInstence().LoadImage(FilePath, image);
			CETextureManager.getInstence().putUnloadedTexture(texture);
			texture.setImageReousrce(image);
		}

		texture.setHashKey(FilePath);
		
		return texture;
		
	}

	public void setImageReousrce(CERawImageResrouce resource)
	{
		image = resource;
	}
	@Override
	public void glLoadTexture() {
		if(image.isSetting() == false)
			return;
		TextureID = CEGL.GenTextures();
		
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, TextureID);
		
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_S, WRAP_S);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_T, WRAP_T);			
		
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_MIN_FILTER, MIN_FILTER);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_MAG_FILTER, MAG_FILTER);
		
		int c = 0 ;
		CEGL.TexImage2D(CEGL.GL_TEXTURE_2D, 0, CEGL.GL_RGBA, image.getWidth(), image.getHeight(), 0,
				CEGL.GL_RGBA, CEGL.GL_UNSIGNED_BYTE, image.getData());
		
		System.out.println(image.getFilePath() + " : " + " Width: "+image.getWidth()+ " Hight: " + image.getHeight()) ;
		System.out.println("TEXTURE ID "  + TextureID);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, 0);
		
	}
	@Override
	public boolean isloadup() {
		return image.isSetting();
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

