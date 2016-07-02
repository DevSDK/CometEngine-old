package com.CometEngine.CELib.Camera;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CEMatrix4f;
import com.CometEngine.Util.Meth.CESize;

public class CE2DDefaultCamera extends CECamera2D {

	CEMatrix4f matrix = new CEMatrix4f();

	public CE2DDefaultCamera() {
		CERenderer renderer = CometEngine.getInstance().getRenderer();
		CEMatrix4f.ortho2d(matrix, 0, renderer.getRenderWidth(), 0, renderer.getRenderHeight());
	}

	public void UpdateCameraMatrix() {
		CESize viewsize = CometEngine.getInstance().getRenderer().getViewSize();

		matrix.resetIDENTITY();
		CERenderer renderer = CometEngine.getInstance().getRenderer();
		CEMatrix4f.ortho2d(matrix, 0, renderer.getRenderWidth(), 0, renderer.getRenderHeight());
	}

	@Override
	public CEMatrix4f getPorjection() {
		return matrix;
	}

}
