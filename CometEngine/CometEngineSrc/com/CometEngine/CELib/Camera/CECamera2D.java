package com.CometEngine.CELib.Camera;

import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEMatrix4f;

public abstract class CECamera2D extends CECamera {
	protected CEFloat2D mPosition = new CEFloat2D();
	protected float Rotation = 0;
	protected CEMatrix4f ProjectionMatrix = new CEMatrix4f();
	protected CEMatrix4f MovementMatrix = new CEMatrix4f();
	private CEMatrix4f projectionmatrixProxy = new CEMatrix4f();

	@Override
	public CEMatrix4f getPorjection() {
		projectionmatrixProxy.setMatrix(ProjectionMatrix);

		projectionmatrixProxy.translate(-mPosition.x, -mPosition.y, 0);
		// projectionmatrixProxy.rotate(0.1f, 0, 0, 1);
		System.out.println(" TranslationMatrix * RotationMatrix = " + projectionmatrixProxy);
		return projectionmatrixProxy;
	}

	@Override
	public CEMatrix4f getMovementMatrix() {
		MovementMatrix.resetIDENTITY();
		MovementMatrix.translate(-mPosition.x, -mPosition.y, 0);

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
	}

	public CEFloat2D Position() {
		return mPosition;

	}

}
