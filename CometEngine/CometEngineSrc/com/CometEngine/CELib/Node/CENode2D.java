package com.CometEngine.CELib.Node;

import java.util.LinkedList;

import com.CometEngine.Util.Meth.CEPosition2D;
import com.CometEngine.Util.Meth.CEScale2D;
import com.CometEngine.Util.Meth.CEScale3D;
import com.CometEngine.Util.Meth.CESize;

public abstract class CENode2D extends CERenderableNode {
	protected final CEPosition2D mPosition = new CEPosition2D();;
	protected float angle = 0;
	protected final CEScale2D  scale = new CEScale2D(1, 1);
	
	
	public CEScale2D getScale()
	{
		return scale;
	}

	public CEPosition2D getPosition()
	{
		return mPosition;
	}
	public void setAngle(float angle)
	{
		this.angle = angle;
	}
	public float getAngle()
	{
		return angle;
	}

	
	

}
