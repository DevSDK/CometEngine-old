package com.CometEngine.Event;

import java.util.HashMap;
import java.util.Map;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.Device.CEKeyBoard;

public class CEEventKeyboard extends CEEvent {

	protected Map<Integer, CEKeyBoard.InputStatus> KEYDATA = null;

	private void UpDataKeyStat(int key) {

	}

	public boolean isKeyPush(int key, int stat) {
		if (KEYDATA.get(key).getActivedScene() == CometEngine.getInstance().getSceneManager().getCurrentScene())
			return KEYDATA.get(key).getStat() == stat;
		return false;
	}

	protected int Mode = 0;

	public int getMode() {
		return Mode;
	}

}
