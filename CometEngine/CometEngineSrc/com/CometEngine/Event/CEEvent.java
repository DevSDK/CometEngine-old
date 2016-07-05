package com.CometEngine.Event;

import com.CometEngine.CELib.Object.CEObject;

public abstract class CEEvent {
	enum EVENTTYPE
	{
		CE_EVENT_NULL,
		CE_EVENT_KEYBORD,
		CE_EVENT_TOUCH,
		CE_EVENT_MOUSE,
		CE_EVENT_CUSTOM
	}
	private EVENTTYPE m_Event_Type = EVENTTYPE.CE_EVENT_NULL;
	

	protected CEObject TargetObject = null;
	
	public CEObject getTargetObject() {
		return TargetObject;
	}

	public EVENTTYPE getType()
	{
		return m_Event_Type;
	}
	

}
