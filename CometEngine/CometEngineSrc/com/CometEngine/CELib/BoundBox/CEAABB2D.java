package com.CometEngine.CELib.BoundBox;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Camera.CE2DDefaultCamera;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEFloat3D;
import com.CometEngine.Util.Meth.CEFloat4f;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CEAABB2D {
	private final CEFloat2D BottomLeft = new CEFloat2D();
	private final CEFloat2D TopLeft = new CEFloat2D();
	private final CEFloat2D TopRight = new CEFloat2D();
	private final CEFloat2D BottomRight = new CEFloat2D();

	private CEFloat2D Updated_BottomLeft = new CEFloat2D();
	private CEFloat2D Updated_TopLeft = new CEFloat2D();
	private CEFloat2D Updated_TopRight = new CEFloat2D();
	private CEFloat2D Updated_BottomRight = new CEFloat2D();

	private CEMatrix4f translationMatrix;

	private CECamera2D Camera;

	public CEAABB2D(float width, float height, CECamera2D camera) {

		BottomLeft.x = -width / 2.0f;
		BottomLeft.y = -height / 2.0f;
		TopLeft.x = -width / 2.0f;
		TopLeft.y = height / 2.0f;
		TopRight.x = width / 2.0f;
		TopRight.y = height / 2.0f;
		BottomRight.x = width / 2.0f;
		BottomRight.y = -height / 2.0f;
		Camera = camera;
	}

	public CEFloat4f calculateBuffer = new CEFloat4f();
	public CEFloat4f calculateBuffer2 = new CEFloat4f();

	public void setCamera(CECamera2D camera) {
		this.Camera = camera;
	}

	public void updateBox(float width, float height) {
		BottomLeft.x = -width / 2.0f;
		BottomLeft.y = -height / 2.0f;
		TopLeft.x = -width / 2.0f;
		TopLeft.y = height / 2.0f;
		TopRight.x = width / 2.0f;
		TopRight.y = height / 2.0f;
		BottomRight.x = width / 2.0f;
		BottomRight.y = -height / 2.0f;
	}

	public void multiplyMatrix(CEMatrix4f SoruceMatrix, CEFloat2D operand, CEFloat2D result) {
		calculateBuffer.x = operand.x;
		calculateBuffer.y = operand.y;

		SoruceMatrix.multiply(calculateBuffer, calculateBuffer2);
		result.x = calculateBuffer2.x;
		result.y = calculateBuffer2.y;
	}

	private CEFloat2D Max = new CEFloat2D();
	private CEFloat2D Min = new CEFloat2D();

	private void setMax(CEFloat2D a, CEFloat2D b, CEFloat2D c, CEFloat2D d) {

		Max.x = Math.max(a.x, Math.max(b.x, Math.max(c.x, d.x)));
		Max.y = Math.max(a.y, Math.max(b.y, Math.max(c.y, d.y)));

	}

	private void setMin(CEFloat2D a, CEFloat2D b, CEFloat2D c, CEFloat2D d) {
		Min.x = Math.min(a.x, Math.min(b.x, Math.min(c.x, d.x)));
		Min.y = Math.min(a.y, Math.min(b.y, Math.min(c.y, d.y)));

	}

	public void update(CEMatrix4f translationMatrix) {

		this.translationMatrix = translationMatrix;

		if (Camera != null) {
			CECamera2D cam = (CECamera2D) Camera;
			multiplyMatrix(translationMatrix, BottomLeft, Updated_BottomLeft);
			multiplyMatrix(cam.getMovementMatrix(), Updated_BottomLeft, Updated_BottomLeft);

			multiplyMatrix(translationMatrix, TopLeft, Updated_TopLeft);
			multiplyMatrix(cam.getMovementMatrix(), Updated_TopLeft, Updated_TopLeft);

			multiplyMatrix(translationMatrix, TopRight, Updated_TopRight);
			multiplyMatrix(cam.getMovementMatrix(), Updated_TopRight, Updated_TopRight);

			multiplyMatrix(translationMatrix, BottomRight, Updated_BottomRight);
			multiplyMatrix(cam.getMovementMatrix(), Updated_BottomRight, Updated_BottomRight);
		}
	}

	private final CEFloat2D coordpos = new CEFloat2D();

	public boolean isContainPoint(float x, float y) {
		coordpos.x = x;
		coordpos.y = y;
		return isContainPoint(coordpos);
	}

	public boolean isContainBoundingBox(CEAABB2D AABB) {
		return isContainPoint(AABB.Updated_BottomLeft) || isContainPoint(AABB.Updated_BottomRight)
				|| isContainPoint(AABB.Updated_TopLeft) || isContainPoint(AABB.Updated_TopRight);
	}

	public boolean isContainPoint(CEFloat2D point) {
		update(translationMatrix);
		setMax(Updated_BottomLeft, Updated_BottomRight, Updated_TopLeft, Updated_TopRight);
		setMin(Updated_BottomLeft, Updated_BottomRight, Updated_TopLeft, Updated_TopRight);

		if (Min.x <= point.x && point.x <= Max.x && Min.y <= point.y && point.y <= Max.y)
			return true;
		return false;

	}
}
