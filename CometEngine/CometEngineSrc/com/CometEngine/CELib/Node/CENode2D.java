package com.CometEngine.CELib.Node;

import java.util.LinkedList;

import com.CometEngine.Util.Meth.CEPosition2D;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEScale3D;
import com.CometEngine.Util.Meth.CESize;

public abstract class CENode2D extends CERenderableNode {
	protected final CEPosition2D mPosition = new CEPosition2D();;
	protected float angle = 0;
	protected final CEFloat2D scale = new CEFloat2D(1, 1);
	protected final CEFloat2D control_point  = new CEFloat2D(0.5f, 0.5f); 
	
	
	public CEFloat2D getControlPoint()
	{
		return control_point;
	}
	
	public CEFloat2D getScale()
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
