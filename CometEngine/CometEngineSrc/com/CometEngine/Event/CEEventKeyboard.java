package com.CometEngine.Event;

import java.util.HashMap;
import java.util.Map;

import com.CometEngine.Device.CEKeyBoard;

public class CEEventKeyboard extends CEEvent {

	protected Map<Integer, CEKeyBoard.InputStatus> KEYDATA = null;

	private void UpDataKeyStat(int key) {

	}

	public boolean isKeyPush(int key, int stat)
	{
		return KEYDATA.get(key).getStat() == stat;
	}

	protected int Mode = 0;

	public int getMode() {
		return Mode;
	}

	

}
