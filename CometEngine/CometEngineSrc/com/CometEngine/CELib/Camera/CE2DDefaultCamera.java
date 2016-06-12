package com.CometEngine.CELib.Camera;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CESize;
import com.CometEngine.Util.Meth.jglm.Mat4;
import com.CometEngine.Util.Meth.jglm.Matrices;

public class CE2DDefaultCamera extends  CECamera2D{
	

	Mat4 matrix = null;
	public CE2DDefaultCamera()
	{
		CESize viewsize = CometEngine.getInstece().getRenderer().getViewSize();
		
		matrix = Matrices.ortho2d(0, viewsize.getWidth(), 0, viewsize.getHight());
	}
	
	public void UpdateCameraMatrix()
	{
		CESize viewsize = CometEngine.getInstece().getRenderer().getViewSize();
		
		matrix = Matrices.ortho2d(0, viewsize.getWidth(), 0, viewsize.getHight());
	}
	@Override
	public Mat4 getPorjection() 	{
		return matrix;
	}
	
	
}
