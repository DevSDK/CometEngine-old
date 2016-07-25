
package com.CometEngine.CELib.model3d;

import com.CometEngine.CELib.Object.CERenderableObject;

public class CEObject3D extends CERenderableObject {

	public static CEObject3D CreateWithObjFile(String FIleName) {
		CEObject3D obj = new CEObject3D();

		return obj;
	}

	@Override
	public void onInit() {

	}

	@Override
	public void onDraw() {

	}

	@Override
	public void CleanUp() {

	}

	private CEObject3D() {

	}

}
