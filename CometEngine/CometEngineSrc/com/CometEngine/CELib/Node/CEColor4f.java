package com.CometEngine.CELib.Node;

public class CEColor4f {
	public float Red = 0;
	public float Green = 0;
	public float Blue = 0;
	public float Alpha = 0;
	
	public CEColor4f(float Red, float Green, float Blue, float alpha)
	{
		this.Red = Red;
		this.Green = Green;
		this.Blue = Blue;
		this.Alpha = alpha;
	}	 
	
	public CEColor4f Clone()
	{
		return new CEColor4f(Red, Green, Blue, Alpha);
	}
	
}
