package com.CometEngine.Util.Meth;

public class CEPosition2D {
	public float x = 0;
	public float y = 0;
	public CEPosition2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public CEPosition2D(){}
	public CEPosition2D Clone()
	{
		return new CEPosition2D(x,y);
	}
}
