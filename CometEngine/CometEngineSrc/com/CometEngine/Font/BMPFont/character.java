package com.CometEngine.Font.BMPFont;

public class character {

	private int id;
	private double xTextureCoord;
	private double yTextureCoord;
	private double xMaxTextureCoord;
	private double yMaxTextureCoord;
	private double xOffset;
	private double yOffset;
	private double sizeX;
	private double sizeY;
	private double xAdvance;

	protected character(int id, double xTextureCoord, double yTextureCoord, double xTexSize, double yTexSize,
			double xOffset, double yOffset, double sizeX, double sizeY, double xAdvance) {
		this.id = id;
		this.xTextureCoord = xTextureCoord;
		this.yTextureCoord = yTextureCoord;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.xMaxTextureCoord = xTexSize + xTextureCoord;
		this.yMaxTextureCoord = yTexSize + yTextureCoord;
		this.xAdvance = xAdvance;
	}

	public int getId() {
		return id;
	}

	public double getxTextureCoord() {
		return xTextureCoord;
	}

	public double getyTextureCoord() {
		return yTextureCoord;
	}

	public double getXMaxTextureCoord() {
		return xMaxTextureCoord;
	}

	public double getYMaxTextureCoord() {
		return yMaxTextureCoord;
	}

	public float getxOffset() {
		return (float)xOffset;
	}

	public float getyOffset() {
		return  (float)yOffset;
	}

	public float getSizeX() {
		return (float) sizeX;
	}

	public float getSizeY() {
		return (float)sizeY;
	}

	public float getxAdvance() {
		return (float)xAdvance;
	}
	@Override
	public String toString()
	{
		return "Size X :"+ getSizeX() +" Size Y "+ getSizeY() + 
				" Advence "+getxAdvance()+" TexCod X " + getxTextureCoord() +" TexCod Y  "+getyTextureCoord()+ " MaxTexX " + getXMaxTextureCoord()+" MaxTexY "+getYMaxTextureCoord()
				+" Offset X "+getxOffset()+" Offset Y " +getyOffset()  ;
		
	}

}
