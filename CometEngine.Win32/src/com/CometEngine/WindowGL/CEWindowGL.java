package com.CometEngine.WindowGL;

import org.lwjgl.opengl.GL11;

import com.CometEngine.Renderer.CEGL;

public class CEWindowGL implements CEGL{

	@Override
	public void Clear(int mask) {
		GL11.glClear(mask);	
	}

	@Override
	public void ClearColor(float red, float green, float blue, float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
	}
	
}
