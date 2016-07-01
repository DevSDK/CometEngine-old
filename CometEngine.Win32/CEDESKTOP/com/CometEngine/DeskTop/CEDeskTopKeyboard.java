package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.newdawn.slick.command.ControllerButtonControl;

import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEKeyBoard;

public class CEDeskTopKeyboard extends GLFWKeyCallback{
	
	public CEDeskTopKeyboard() {
		CEDeviceManager.getInstance().addDevice("KeyBoard", new CEKeyBoard());
	}
	
	
	
	@Override
	public void invoke(long window, int key, int idonknow, int status, int mode) {
		CEKeyBoard keybord = (CEKeyBoard) CEDeviceManager.getInstance().getDevice("KeyBoard");
		
		if(status > 0)
		{
			keybord.PushKey(key, status, mode);
			keybord.Active();
		}
		else if(status == 0)
		{
			keybord.stop();
			keybord.RelaseKey(key,mode);	
		}
		
	}

}
