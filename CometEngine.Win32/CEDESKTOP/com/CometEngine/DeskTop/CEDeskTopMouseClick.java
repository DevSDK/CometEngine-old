package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEMouse;

public class CEDeskTopMouseClick extends GLFWMouseButtonCallback {

	@Override
	public void invoke(long arg0, int arg1, int arg2, int arg3) {
		CEMouse Mouse = (CEMouse) CEDeviceManager.getInstance().getDevice("Mouse"); 
		Mouse.updateClick(arg1, arg2);
	}

}
