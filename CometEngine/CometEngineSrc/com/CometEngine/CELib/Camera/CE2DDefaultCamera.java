package com.CometEngine.CELib.Camera;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CESize;
import com.CometEngine.Util.Meth.jglm.Mat4;
import com.CometEngine.Util.Meth.jglm.Matrices;

public class CE2DDefaultCamera extends  CECamera2D{
	
	@Override
	public Mat4 getPorjection() 	{
		CESize viewsize = CometEngine.getInstece().getRenderer().getViewSize();
			return Matrices.ortho2d(0, viewsize.getWidth(), 0, viewsize.getHight());
		}
	
	
}
