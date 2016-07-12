package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.newdawn.slick.command.ControllerButtonControl;

import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEKeyBoard;

public class CEDeskTopKeyboard extends GLFWKeyCallback {

	public CEDeskTopKeyboard() {
	}

	@Override
	public void invoke(long window, int key, int idonknow, int status, int mode) {
		CEKeyBoard keybord = (CEKeyBoard) CEDeviceManager.getInstance().getDevice("KeyBoard");

		if (status == 1) {
			keybord.PushKey(key, 1, mode);
		} else if (status == 0) {
			keybord.RelaseKey(key, mode);
		}

	}

}
