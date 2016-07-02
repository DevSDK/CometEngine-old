package com.CometEngine.Event;

public class CEEventTouch extends CEEvent {
	protected int[] touchXPos = new int[30];
	protected int[] touchYPos = new int[30];
	protected int TouchPosSize = 0;

	public int getXPos() {
		return touchXPos[0];
	}

	public int getYPos() {
		return touchYPos[0];
	}

	public int geTouchSize() {
		return TouchPosSize;
	}

	public int getXPos(int index) {
		if (index < TouchPosSize - 1 && 0 <= index)
			return touchXPos[index];
		return touchXPos[0];
	}

	public int getYPos(int index) {
		if (index < TouchPosSize - 1 && 0 <= index)
			return touchYPos[index];
		return touchYPos[0];
	}
}
