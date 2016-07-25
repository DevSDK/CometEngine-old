package com.CometEngine.CELib.Camera;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEMatrix4f;
import com.CometEngine.Util.Meth.CEScale3D;

public class CECamera2D extends CECamera {
	protected CEFloat2D mPosition = new CEFloat2D();
	protected float Rotation = 0;
	protected CEMatrix4f ProjectionMatrix = new CEMatrix4f();
	protected CEMatrix4f MovementMatrix = new CEMatrix4f();
	private CEMatrix4f projectionmatrixProxy = new CEMatrix4f();
	private CEScale3D scale = new CEScale3D();

	public CEScale3D Scale() {
		return scale;
	}

	@Override
	public CEMatrix4f getPorjection() {
		projectionmatrixProxy.setMatrix(ProjectionMatrix);

		return projectionmatrixProxy;
	}

	@Override
	public CEMatrix4f getMovementMatrix() {
		MovementMatrix.resetIDENTITY();
		MovementMatrix.rotate(Rotation, 0, 0, 1).translate(-mPosition.x, -mPosition.y, 0).scale(scale.x, scale.y,
				scale.z);
		return MovementMatrix;
	}

	public void setRotation(float rotation) {
		System.out.println(" Rotation " + Rotation);
		this.Rotation = rotation;
	}

	public float getRotation() {

		return Rotation;
	}

	public CECamera2D() {
		super(CECamera.CECameraType.CE_CAM2D);
		UpdateCameraMatrix();
	}

	public CEFloat2D Position() {
		return mPosition;

	}

	public void UpdateCameraMatrix() {
		ProjectionMatrix.resetIDENTITY();
		CERenderer renderer = CometEngine.getInstance().getRenderer();
		CEMatrix4f.ortho2d(ProjectionMatrix, 0, renderer.getRenderWidth(), 0, renderer.getRenderHeight());
	}
}
