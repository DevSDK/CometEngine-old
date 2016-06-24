package com.CometEngine.Event;

public abstract class CEEVent {
	enum EVENTTYPE
	{
		CE_EVENT_NULL,
		CE_EVENT_KEYBORD,
		CE_EVENT_TOUCH,
		CE_EVENT_MOUSE,
		CE_EVENT_CUSTOM
	}
	private EVENTTYPE m_Event_Type = EVENTTYPE.CE_EVENT_NULL;
	
	
	
	public EVENTTYPE getType()
	{
		return m_Event_Type;
	}
	

}
