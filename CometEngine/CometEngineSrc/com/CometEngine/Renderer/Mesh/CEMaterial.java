package com.CometEngine.Renderer.Mesh;

import java.util.HashMap;

import com.CometEngine.Renderer.Texture.CETexture2D;
import com.CometEngine.Util.Meth.CEFloat3D;

public class CEMaterial {

	public final CEFloat3D Ambient = new CEFloat3D();
	public final CEFloat3D Diffuse = new CEFloat3D();
	public final CEFloat3D specular = new CEFloat3D();

	public void AddNormalTexture(String Key, CETexture2D texture) {
		if(NormalTextures.containsKey(Key))
			return;
		
		NormalTextures.put(Key, texture);
	}

	public void AddColorTexture(String Key, CETexture2D texture) {
		if(ColorTextures.containsKey(texture))
			return;
		ColorTextures.put(Key, texture);
	}

	public CETexture2D getColorTexture(String Key) {
		if(ColorTextures.containsKey(Key)==false)
			return null;
		return ColorTextures.get(Key);
	}

	public CETexture2D getNormalTexture(String Key) {
		if(NormalTextures.containsKey(Key) == false) 
			return null;
		return NormalTextures.get(Key);
	}

	private final HashMap<String, CETexture2D> ColorTextures = new HashMap<String, CETexture2D>();
	private final HashMap<String, CETexture2D> NormalTextures = new HashMap<String, CETexture2D>();
}
