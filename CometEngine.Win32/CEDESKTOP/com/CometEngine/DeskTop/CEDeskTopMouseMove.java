package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.CERenderer;

public class CEDeskTopMouseMove extends GLFWCursorPosCallback {

	private int CursorPosX = 0;
	private int CursorPosY = 0;

	public int getXPos() {
		return CursorPosX;
	}

	public int getYPos() {
		return CursorPosY;
	}

	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		CERenderer renderer = CometEngine.getInstance().getRenderer();

		CursorPosX = (int) (((double)renderer.getRenderWidth() / (double)CEDeskTop.getFrameWidth()) * arg1);
		CursorPosY = (int) (((double)renderer.getRenderHeight() / (double)CEDeskTop.getFrameHeight()) * arg2);

		System.out.println("arg0: " + arg0 + " arg1: " + CursorPosX + " arg2: " + CursorPosY);
	}

}
