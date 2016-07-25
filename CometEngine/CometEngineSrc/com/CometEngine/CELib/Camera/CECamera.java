package com.CometEngine.CELib.Camera;

import com.CometEngine.Util.Meth.CEMatrix4f;

public abstract class CECamera {
	private CEMatrix4f matrix = null;

	public enum CECameraType {
		CE_NULL, CE_CAM2D, CE_CAM3D
	}

	public CECamera(CECameraType type) {
		this.TYPE = type;
	}

	private CECameraType TYPE = CECameraType.CE_NULL;

	public abstract CEMatrix4f getPorjection();

	public abstract CEMatrix4f getMovementMatrix();
}
