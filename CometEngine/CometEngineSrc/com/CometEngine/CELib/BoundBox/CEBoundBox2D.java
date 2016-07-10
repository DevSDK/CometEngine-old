package com.CometEngine.CELib.BoundBox;

import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEFloat3D;
import com.CometEngine.Util.Meth.CEFloat4f;
import com.CometEngine.Util.Meth.CEMatrix4f;
import com.CometEngine.Util.Meth.CEPosition2D;

public class CEBoundBox2D extends CEBoundBox {
	private CEAABB2D AABB;
	private float width;
	private float height;

	private CECamera2D Camera;

	public void updateBoundingBox(CEMatrix4f trnalation) {
		AABB.update(trnalation);
	}

	public CEAABB2D getAABB() {
		return AABB;
	}

	public CEBoundBox2D(float xsize, float ysize, CECamera2D camera) {
		this.width = xsize;
		this.height = ysize;
		this.Camera = camera;
		AABB = new CEAABB2D(xsize, ysize, Camera);
	}
}
