package com.CometEngine.CELib.Camera;

import com.CometEngine.Util.Meth.jglm.Mat4;
import com.CometEngine.Util.Meth.jglm.Matrices;

public abstract class CECamera {
	public enum CECameraType{
		CE_NULL, CE_CAM2D, CE_CAM3D
	}
	public CECamera(CECameraType type) {
		this.TYPE = type;
	}
	private CECameraType TYPE = CECameraType.CE_NULL;
	
	public abstract Mat4 getPorjection();
	
	
}
