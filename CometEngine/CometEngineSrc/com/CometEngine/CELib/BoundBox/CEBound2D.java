package com.CometEngine.CELib.BoundBox;

import com.CometEngine.Util.Meth.CEFloat2D;

public interface CEBound2D {
	CEFloat2D ContentSize = new CEFloat2D();

	public CEFloat2D getSize();

	public CEBoundBox2D getBoundingBox();
}
