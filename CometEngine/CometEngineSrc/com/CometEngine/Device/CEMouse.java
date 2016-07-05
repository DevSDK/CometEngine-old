package com.CometEngine.Device;

import java.util.HashMap;

import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventMouse;
import com.CometEngine.Event.CEEventMouse.EVENT_TYPE;

public class CEMouse extends CEDevice {
	private double CursorXPos = 0;
	private double CursorYPos = 0;
	
	
	private int Button;
	private int Status;
	private double scroll;
	public static final int MOUSE_DOWN = 1;
	public static final int MOUSE_UP = 0;
	
	public void setCursorPos(double XPos, double YPos) {
		CursorXPos = XPos;
		CursorYPos = YPos;
		updateMouse(CEEventMouse.EVENT_TYPE.MOUSE_MOVE);
	}

	public void updateClick(int button, int status) {
		Button = button;
		Status = status;
		updateMouse(CEEventMouse.EVENT_TYPE.MOUSE_CLICK);
	}

	public void updateScroll(double delta) {
		scroll = delta;
		updateMouse(EVENT_TYPE.MOUSE_SCROLL);
	}

	public double getScroll() {
		return scroll;
	}

	public double getXPos() {
		return CursorXPos;
	}

	public double getYPos() {
		return CursorYPos;
	}

	public int getActiveButton() {
		return Button;
	}

	public int getStatus() {
		return Status;
	}

	public void updateMouse(CEEventMouse.EVENT_TYPE click) {
		CEEventDispatcher.getInstance().MouseEventActive(click);
	}

}