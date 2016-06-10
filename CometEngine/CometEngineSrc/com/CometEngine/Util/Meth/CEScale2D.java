package com.CometEngine.Util.Meth;

public class CEScale2D {
	public float x = 0;
	public float y = 0;
	public CEScale2D(){}
	public CEScale2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public CEScale2D Clone()
	{
		return new CEScale2D(x, y);
	}
}
