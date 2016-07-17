package com.CometEngine.Device;

import java.util.ArrayList;

import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Event.CEEventDispatcher;

public class CETouchPad extends CEDevice {
	public static final int CE_TOUCH_DOWN = 0;
	public static final int CE_TOUCH_MOVED = 1;
	public static final int CE_TOUCH_UP = 2;

	private int TouchCounter = 0;
	private boolean isTouched = false;

	private int status = 0;
	private float x;
	private float y;
	private int index = 0;

	public void TouchUpdate(float x, float y, int index, int status) {

		this.x = x;
		this.y = y;
		this.index = index;
		this.status = status;
		CEEventDispatcher.getInstance().UpdateTouchEvent(index);
	}

	public int getStatus() {
		return status;
	}

	public float getXpos() {
		return x;
	}

	public float getYpos() {
		return y;
	}

	@Override
	CEScene getActiveScene(Object Key) {
		// TODO Auto-generated method stub
		return null;
	}

}
