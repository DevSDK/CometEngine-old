package com.CometEngine.CELib.BoundBox;

public class CEBoundBox {
	public static CEAABB2D getBoundingBoxAABB(CEBound2D appir) {
		return appir.getBoundingBox().getAABB();
	}
}
