package com.CometEngine.Renderer.Texture;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Resrouce.CEResource;
import com.CometEngine.Resrouce.CEResourceManager;

public class CETextureManager {
	private static final Hashtable<String, CETexture> TextureTable = new Hashtable<String, CETexture>();
	private static final CETextureManager instence = new CETextureManager();

	public static CETextureManager getInstence() {
		return instence;
	}

	public void showLog() {
		System.out.println("Texture Manager : Counter " + TextureTable.size());
		for (CETexture tex : TextureTable.values()) {
			System.out.println(tex.getTextureID());
		}
	}

	protected void addTexture(String filepath, CETexture texture) {

		TextureTable.put(filepath, texture);

	}

	public boolean isHaveTexture(String path) {
		return TextureTable.containsKey(path);
	}

	public int getSize() {
		return instence.getSize();
	}

	public CETextureCubeMap getTextureCubeMap(String FileKey) {
		if (TextureTable.containsKey(FileKey)) {
			return (CETextureCubeMap) TextureTable.get(FileKey);
		}
		return null;

	}

	public CETexture2D getTexture2D(String filepath) {
		if (TextureTable.containsKey(filepath))
			return (CETexture2D) TextureTable.get(filepath);
		return null;

	}

}