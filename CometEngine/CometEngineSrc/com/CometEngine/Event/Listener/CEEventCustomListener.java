package com.CometEngine.Event.Listener;

public class CEEventCustomListener extends CEEventListener{
	
	public interface EventCustomHandler{
		public void invoke(Object arg0);
	}
	private EventCustomHandler EventHandeler;
	private String EventName = "";
	
	
	private CEEventCustomListener(String EventName, EventCustomHandler handler){
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
