package com.CometEngine.Event;

public class CEEventListenerCustom extends CEEventListener{
	
	public interface EventCustomHandler{
		public void invoke(Object arg0);
	}
	private EventCustomHandler EventHandeler;
	private String EventName = "";
	
	
	private CEEventListenerCustom(String EventName, EventCustomHandler handler){
		this.EventName = EventName;
		this.EventHandeler = handler;
	}
	
	
	protected void invoke(Object O)
	{
		EventHandeler.invoke(O);
	}
	
	public String getEventName()
	{
		return EventName;
	}

}
