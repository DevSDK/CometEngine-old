package com.CometEngine.Resrouce;

public class CERawImageResrouce extends CERawResrouce{

	private int Width = 0;
	private int Height = 0;
	public CERawImageResrouce(String filepath) {
		super(filepath);
		
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int hight) {
		Height = hight;
	}
	
	
	
}
