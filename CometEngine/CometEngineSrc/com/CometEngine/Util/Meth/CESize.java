package com.CometEngine.Util.Meth;

public class CESize {
	private int Width = 0;
	private int Height = 0;
	
	public int getWidth()
	{
		return Width;
	}
	public int getHight()
	{
		return Height;
	}
	public void setSize(int width, int hight)
	{
		this.Width = width;
		this.Height = hight;
	}
	public CESize(int width, int hight)
	{
		this.Width = width;
		this.Height = hight;
	}

	public CESize()
	{
		
	}
	public CESize Clone()
	{
		return new CESize(Width, Height);
	}
}
