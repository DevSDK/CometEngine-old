package com.CometEngine.Resrouce;

import com.CometEngine.Renderer.CEGL;

public class CEImageResrouce extends CEResource {

	private int Width = 0;
	private int Height = 0;
	private int RGBTYPE = CEGL.GL_RGBA;

	public void setRGBType(int type) {
		RGBTYPE = type;
	}

	public int getRGBType() {
		return RGBTYPE;
	}

	public CEImageResrouce(String filepath) {
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
