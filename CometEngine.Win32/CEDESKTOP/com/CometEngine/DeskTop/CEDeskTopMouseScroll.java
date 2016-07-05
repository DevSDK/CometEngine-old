package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFWScrollCallback;

import com.CometEngine.Device.CEDevice;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEMouse;

public class CEDeskTopMouseScroll extends GLFWScrollCallback {

	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		CEMouse mouse = (CEMouse) CEDeviceManager.getInstance().getDevice("Mouse");
		mouse.updateScroll(arg2);

	}

}
