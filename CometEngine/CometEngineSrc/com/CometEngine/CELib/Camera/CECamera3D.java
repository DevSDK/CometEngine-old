package com.CometEngine.CELib.Camera;

import com.CometEngine.CometEngine;
import com.CometEngine.Util.Meth.CEAngle;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CECamera3D extends CECamera {
	private CEMatrix4f ProjectionMatrix = new CEMatrix4f();
	private CEMatrix4f CameraMovemenetMatrix = new CEMatrix4f();
	private CEAngle angle = new CEAngle();

	public CEAngle getAngle() {
		return angle;
	}

	public CECamera3D() {
		super(CECameraType.CE_CAM3D);
		setDefault();
	}

	private void setDefault() {
		ProjectionMatrix.resetIDENTITY();
		CEMatrix4f.perspective(ProjectionMatrix, 90, CometEngine.getInstance().getRenderer().getRenderWidth()
				/ CometEngine.getInstance().getRenderer().getRenderHeight(), 1, 5000);
	}

	@Override
	public CEMatrix4f getPorjection() {
		return ProjectionMatrix;
	}

	@Override
	public CEMatrix4f getMovementMatrix() {
		return CameraMovemenetMatrix;
	}

}
