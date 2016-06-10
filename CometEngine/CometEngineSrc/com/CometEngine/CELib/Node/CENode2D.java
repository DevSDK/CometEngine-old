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
		return scale.Clone();
	}
	public void setScale(float x, float y)
	{
		if(x < 0)
			x=0;
		if(y < 0)
			y = 0;
		
		scale.x = x;
		scale.y = y;
	}
	public CEPosition2D getPosition()
	{
		return mPosition.Clone();
	}
	public void setAngle(float angle)
	{
		this.angle = angle;
	}
	public float getAngle()
	{
		return angle;
	}
	public void setPosition(float x, float y)
	{
		mPosition.x = x;
		mPosition.y = y;
	}
	public void setPosition(CEPosition2D position)
	{
		mPosition.x = position.x;
		mPosition.y = position.y;
	}
	
	

}
