package com.CometEngine.Renderer.Texture.Textures;

import java.nio.IntBuffer;

import com.CometEngine.Renderer.CEGLResource;

public abstract class CETexture extends CEGLResource {
	protected int TextureID = 0;
	protected String HashKey = "";

	public void setTextureID(int texture) {
		TextureID = texture;
	}

	public void setHashKey(String key) {
		HashKey = key;
	}

	public String getHashKey() {
		return HashKey;
	}

	public int getTextureID() {
		return TextureID;
	}

	public abstract String getKey();
}
