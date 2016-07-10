package com.CometEngine.Event;

public class CEEventMouse extends CEEvent {
	public enum EVENT_TYPE {
		NULL, MOUSE_CLICK, MOUSE_MOVE, MOUSE_SCROLL
	}
	protected boolean isActived = false;
	protected String ListenerKey = "";
	protected double scroll = 0;
	protected double XPos = 0;
	protected double YPos = 0;
	protected int ActiveButton = 0;
	protected int Status= 0;
	
	protected EVENT_TYPE type;

	public EVENT_TYPE getEventType() {
		return type;
	}
}
