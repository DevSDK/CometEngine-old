package com.CometEngine.Renderer.Texture.Textures;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import com.CometEngine.FileUtil.CEFileFormat;
import com.CometEngine.FileUtil.CEFileFormat.FILE_FORMAT;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResourceManager;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEImageResrouceLoader;
import com.CometEngine.Resrouce.CEImageResrouce;
import com.CometEngine.Resrouce.CEResource;
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

	public CEImageResrouce getData() {
		return image;
	}

	private CETexture2D(String file) {
		try {
			if (new File(file).isFile()) {
				throw new IOException("No File In " + file);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public static CETexture2D CreateTexture2D(String FilePath, int WRAP) {

		CETexture2D texture = CreateTexture2D(FilePath);
		texture.WRAP_S = WRAP;
		texture.WRAP_T = WRAP;
		return texture;
	}

	public static CETexture2D CreateTexture2D(String FilePath) {
		if (CETextureManager.getInstence().isHaveTexture(FilePath)) {
			return CETextureManager.getInstence().getTexture2D(FilePath);
		}

		CETexture2D texture = new CETexture2D(FilePath);
		CEImageResrouce image = null;

		CETextureManager.getInstence().addTexture(FilePath, texture);

		if (CEResourceManager.getInstence().isData(FilePath)) {
			image = CEResourceManager.getInstence().getResoruce(FilePath);
			texture.setImageReousrce(image);
			CEGLResourceManager.getInstence().putGLResrouce(texture);
		} else {
			image = new CEImageResrouce(FilePath);
			CEResourceManager.getInstence().putResoruceData(image);
			texture.setImageReousrce(image);
			CEImageResrouceLoader.getInstence().LoadImage(FilePath, image);
			CEGLResourceManager.getInstence().putGLResrouce(texture);

		}

		texture.setHashKey(FilePath);
		return texture;

	}

	public void setImageReousrce(CEImageResrouce resource) {
		image = resource;
	}

	@Override
	public void onGLLoad() {
		if (image.isSetting() == false)
			return;
		TextureID = CEGL.GenTextures();

		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, TextureID);

		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_S, WRAP_S);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_WRAP_T, WRAP_T);

		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_MIN_FILTER, MIN_FILTER);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_2D, CEGL.GL_TEXTURE_MAG_FILTER, MAG_FILTER);

		CEGL.TexImage2D(CEGL.GL_TEXTURE_2D, 0, image.getRGBType(), image.getWidth(), image.getHeight(), 0,
				image.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image.getData());
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
