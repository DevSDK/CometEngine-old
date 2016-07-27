package com.CometEngine.CELib.Camera;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CEAngle;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CECamera3D extends CECamera {
	private CEMatrix4f ProjectionMatrix = new CEMatrix4f();
	private CEMatrix4f CameraMovemenetMatrix = new CEMatrix4f();
	private CEAngle angle = new CEAngle();
	private float FOV = 70;
	private float FAR = 10000;

	public void setFar(float far) {
		this.FAR = far;
	}

	public float getFar() {
		return FAR;
	}

	public CEAngle getAngle() {
		return angle;
	}

	public static CECamera3D Create() {
		CECamera3D cam =new CECamera3D();
		return cam;
	}

	private CECamera3D() {
		super(CECameraType.CE_CAM3D);
		setDefault();
	}

	public void setFov(float fov) {
		this.FOV = fov;

	}

	private float getFov() {
		return FOV;
	}

	private void setDefault() {
		ProjectionMatrix.resetIDENTITY();
		CERenderer rnederer = CometEngine.getInstance().getRenderer();
		CEMatrix4f.perspective(ProjectionMatrix, FOV, rnederer.getRenderWidth() / rnederer.getRenderHeight(), 1, FAR);
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
