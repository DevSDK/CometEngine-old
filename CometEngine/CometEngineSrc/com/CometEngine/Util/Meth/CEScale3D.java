package com.CometEngine.Util.Meth;

public class CEScale3D {
	public float x = 0;
	public float y = 0;
	public float z = 0;
	
	public CEScale3D (){}
	public CEScale3D (float x,float y , float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public CEScale3D Clone()
	{
		return new CEScale3D(x, y, z);
	}
}
