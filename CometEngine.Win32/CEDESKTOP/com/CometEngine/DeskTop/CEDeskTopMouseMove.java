package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import com.CometEngine.CometEngine;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Renderer.CERenderer;

public class CEDeskTopMouseMove extends GLFWCursorPosCallback {

	private double CursorPosX = 0;
	private double CursorPosY = 0;

	public double getXPos() {
		return CursorPosX;
	}

	public double getYPos() {
		return CursorPosY;
	}

	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		CERenderer renderer = CometEngine.getInstance().getRenderer();
		CEMouse mouse = (CEMouse) CEDeviceManager.getInstance().getDevice("Mouse");

		CursorPosX = (((double) renderer.getRenderWidth() / (double) CEDeskTop.getFrameWidth()) * arg1);
		CursorPosY = ((double)renderer.getRenderHeight()) - (((double) renderer.getRenderHeight() / (double) CEDeskTop.getFrameHeight()) * arg2);
		mouse.setCursorPos(CursorPosX, CursorPosY);
	}

}
