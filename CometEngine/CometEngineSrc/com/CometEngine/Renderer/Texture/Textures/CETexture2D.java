package com.CometEngine.Renderer.Texture.Textures;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
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
		
		
		if(!CEResourceManager.getInstence().isData(file))
		{
			image = new CERawImageResrouce(file);	
			CEImageResrouceLoader.getInstence().LoadImage(file, image);

			CETextureManager.getInstence().putUnloadedTexture(this);
			CEResourceManager.getInstence().putResoruceData(image);
		}
		else
		{
			image = CEResourceManager.getInstence().getResoruce(file);
		}
		
		
	}
	
	
	
	


	@Override
	public void glLoadTexture() {
		if(image.isSetting() == false)
			return;
		TextureID = CEGL.GenTextures();
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, TextureID);
		
		
		
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_S, WRAP_S);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_T, WRAP_T);			

		CEGL.TexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, MIN_FILTER);
		CEGL.TexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, MAG_FILTER);
		CEGL.TexImage2D(CEGL.GL_TEXTURE_2D, 0, CEGL.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image.getData());
		System.out.println("TEXTURE ID "  + TextureID);
	}
	@Override
	public boolean isloadup() {
		return image.isSetting();
	}
	
}

