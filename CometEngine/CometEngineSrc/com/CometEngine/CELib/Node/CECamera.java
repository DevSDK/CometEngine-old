package com.CometEngine.CELib.Node;

import com.CometEngine.Util.Meth.jglm.Mat4;
import com.CometEngine.Util.Meth.jglm.Matrices;

public class CECamera {
	public Mat4 getPorjection()
	{
		return Matrices.ortho2d(0, 1000, 0, 1000);
	}
}
