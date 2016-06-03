package com.CometEngine.Event.Manager;

import java.util.LinkedList;

import com.CometEngine.Event.CEEVent;

public class CEEventManager {
	private static final  CEEventManager m_Instence  = new CEEventManager();
	private static LinkedList<CEEVent> EVENT_COMMAND_QUEUE = new LinkedList<CEEVent>();
	public static CEEventManager getInstence()
	{
			return m_Instence;
	}
	public void PollAllEvent()
	{
		VisitiorCall();
		
		if(EVENT_COMMAND_QUEUE.isEmpty() == false)
		{
			
		for(CEEVent event : EVENT_COMMAND_QUEUE)
		{
			event.invoke();
		}
		EVENT_COMMAND_QUEUE.clear();
		}
	}
	public void VisitiorCall()
	{
		
	}
	public void addEvent(CEEVent event)
	{
		EVENT_COMMAND_QUEUE .add(event);
	}
	
}
