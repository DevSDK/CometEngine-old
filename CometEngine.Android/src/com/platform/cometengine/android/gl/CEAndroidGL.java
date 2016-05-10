package com.platform.cometengine.android.gl;

import android.opengl.*;

import com.CometEngine.Renderer.CEGL;

public class CEAndroidGL implements CEGL{

	@Override
	public void Clear(int mask) {
		GLES20.glClear(mask);
		
	}

	@Override
	public void ClearColor(float red, float green, float blue, float alpha) {
		GLES20.glClearColor(red, green, blue, alpha);
	}

}
