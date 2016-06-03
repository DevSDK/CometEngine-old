package com.CometEngine.Renderer.Texture.Textures;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.newdawn.slick.opengl.PNGDecoder;

import com.CometEngine.FileUtil.CEFileFormat;
import com.CometEngine.FileUtil.CEFileFormat.FILE_FORMAT;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResourceManager;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEImageResrouceLoader;
import com.CometEngine.Resrouce.CEImageResrouce;
import com.CometEngine.Resrouce.CEResrouce;
import com.CometEngine.Resrouce.CEResourceManager;


public class CETexture2D extends CETexture {
	private int width = 0;
	private int hight = 0;
	

	CEFileFormat.FILE_FORMAT format = FILE_FORMAT.CE_NULL;
	private int WRAP_S = CEGL.GL_CLAMP_TO_EDGE;
	private int WRAP_T = CEGL.GL_CLAMP_TO_EDGE;
	private int MIN_FILTER = CEGL.GL_NEAREST;
	private int MAG_FILTER = CEGL.GL_NEAREST;
	
	CEImageResrouce image = null;
	
	public CEImageResrouce getData()
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
		CEImageResrouce image = null;
		
		CETextureManager.getInstence().addTexture(FilePath, texture);
		
		if(CEResourceManager.getInstence().isData(FilePath))
		{
			System.out.println("IS LOADED");
			image = CEResourceManager.getInstence().getResoruce(FilePath);
			texture.setImageReousrce(image);
			CEGLResourceManager.getInstence().putGLResrouce(texture);
		}
		else
		{
			System.out.println("IS UNLOADED");
			image = new CEImageResrouce(FilePath);
			CEResourceManager.getInstence().putResoruceData(image);			
			texture.setImageReousrce(image);
			CEImageResrouceLoader.getInstence().LoadImage(FilePath, image);
			CEGLResourceManager.getInstence().putGLResrouce(texture);

		}

		texture.setHashKey(FilePath);
		return texture;
		
	}

	public void setImageReousrce(CEImageResrouce resource)
	{
		image = resource;
	}
	@Override
	public void onGLLoad() {
		if( image.isSetting() == false)
			return;
		TextureID = CEGL.GenTextures();
		
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, TextureID);
		
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_S, WRAP_S);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_T, WRAP_T);			
		
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_MIN_FILTER, MIN_FILTER);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_MAG_FILTER, MAG_FILTER);
	
		byte[] array = image.getData().array().clone();
		ByteArrayInputStream stream;
		PNGDecoder decoder;
		ByteBuffer imbuf= null;
		try {
			decoder = new PNGDecoder(stream = new ByteArrayInputStream(array));
		
			imbuf = ByteBuffer.allocateDirect(4 * decoder.getHeight() * decoder.getWidth());						
			decoder.decode(imbuf, decoder.getWidth() * 4 , PNGDecoder.RGBA);
			imbuf.flip();
			image.setWidth(decoder.getWidth());
			image.setHeight(decoder.getHeight());
		
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
		CEGL.TexImage2D(CEGL.GL_TEXTURE_2D, 0, CEGL.GL_RGBA, image.getWidth(), image.getHeight(), 0, CEGL.GL_RGBA, CEGL.GL_UNSIGNED_BYTE, imbuf);
		System.out.println(image.getFilePath() + " : " + " Width: "+image.getWidth()+ " Hight: " + image.getHeight()) ;
		System.out.println("TEXTURE ID "  + TextureID);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, 0);
	}
	@Override
	public boolean isloaded() {
		return image.isSetting();
	}
	@Override
	public String getKey() {
		return HashKey;
	}
	@Override
	public void onGLDelete() {
		
	}
	
}

