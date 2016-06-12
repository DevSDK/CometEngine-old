package com.CometEngine.CELib.Node;

import java.nio.FloatBuffer;

import com.CometEngine.Util.Buffer.CEBufferUtils;

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
	

	public void getBuffer(FloatBuffer buffer)
	{
		buffer.put(Red);
		buffer.put(Green);
		buffer.put(Blue);
		buffer.put(Alpha);
		buffer.flip();
	}
}
