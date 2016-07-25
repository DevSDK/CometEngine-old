package com.CometEngine.Util.Meth;

public class CEScale3D {
	public float x = 1;
	public float y = 1;
	public float z = 1;
	
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
