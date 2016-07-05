package com.CometEngine.Event;

import java.util.EventListener;

import com.CometEngine.CELib.Object.CEObject;
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

	private CEEventListenerKeyboard() {
	};

	public static CEEventListenerKeyboard Create(CEObject object) {
		CEEventListenerKeyboard listener = new CEEventListenerKeyboard();
		listener.CallBack = DefaultMethod;
		listener.TargetObject = object;

		return listener;
	}

	public static CEEventListenerKeyboard Create() {
		return Create(null);
	}

	public static CEEventListenerKeyboard Create(KeyBoardEventCallBack CallBack, CEObject scene) {
		CEEventListenerKeyboard listener = Create(scene);
		listener.CallBack = CallBack;
		return listener;
	}

	public CEObject getTargetScene() {
		return TargetScene;
	}

	public void ListenKeyBoardEvent(CEEventKeyboard event) {
		CallBack.KeyBoardEvent(event);
	}

}
