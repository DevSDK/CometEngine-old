package com.CometEngine.Event;

import java.util.EventListener;

import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEKeyBoard.InputStatus;

public class CEEventListenerKeyboard extends CEEventListener {
	public interface KeyBoardEventCallBack {
		public void KeyBoardEvent(CEEventKeyboard event);
	}

	private static KeyBoardEventCallBack DefaultMethod = new KeyBoardEventCallBack() {

		@Override
		public void KeyBoardEvent(CEEventKeyboard event) {
		}
	};
	public KeyBoardEventCallBack CallBack;

	private CEScene TargetScene = null;

	private CEEventListenerKeyboard() {
	};

	public static CEEventListenerKeyboard CreateWithScene(CEScene scene) {
		CEEventListenerKeyboard listener = new CEEventListenerKeyboard();
		listener.CallBack = DefaultMethod;
		listener.TargetScene = scene;

		return listener;
	}

	public static CEEventListenerKeyboard Create(KeyBoardEventCallBack CallBack, CEScene scene) {
		CEEventListenerKeyboard listener = CreateWithScene(scene);
		listener.CallBack = CallBack;
		return listener;

	}
	public CEScene getTargetScene()
	{
		return TargetScene;
	}

	public void ListenKeyBoardEvent(CEEventKeyboard event) {
		CallBack.KeyBoardEvent(event);
	}

}
