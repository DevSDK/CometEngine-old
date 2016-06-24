package com.CometEngine.Event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Node.CENode;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Event.CEEventCustom;
import com.CometEngine.Event.CEEventKeyboard;

public class CEEventDispatcher {
	private static final CEEventDispatcher Instance = new CEEventDispatcher();
	private static final HashMap<String, ArrayList<CEEventCustomListener>> CustomEnventListenerTable = new HashMap<String, ArrayList<CEEventCustomListener>>();
	private static final HashMap<String, ArrayList<CEEventListener>> EventListenerList = new HashMap<String, ArrayList<CEEventListener>>();
	
	private static final String KEYBOARD_LISTENER = "KeyBoard";
	
	private ArrayList<CEEventCustomListener> getCustomEventContainer(CEEventCustomListener listener)
	{
		if(CustomEnventListenerTable.containsKey(listener.getEventName()))
		{
			return CustomEnventListenerTable.get(listener.getEventName());
		}
		else
		{
			ArrayList<CEEventCustomListener> list = new ArrayList<CEEventCustomListener>();
			CustomEnventListenerTable.put(listener.getEventName(), list);
			return list;
		}
	}
	private ArrayList<CEEventListener> getEventListenerContainer(String key)
	{
		if(EventListenerList.containsKey(key)){
			return EventListenerList.get(key);
		}
		else
		{
			ArrayList<CEEventListener> list = new ArrayList<CEEventListener>();
			EventListenerList.put(key, list);
			return list;
		}
	}
	
	public void CallKeyBoardEventByNameTag(String NameTag, int key, int status, int mode)
	{
		ArrayList<CEEventListener> array = getEventListenerContainer(NameTag);
		for(int i = 0 ; i < array.size() ; i ++)
		{
			if(array.get(i) instanceof CEEventKeyboardListener)
			{
				
			}
		}
	}
	
	private boolean StoreCustomEventListener(CEEventCustomListener listener)
	{
		ArrayList<CEEventCustomListener> array = getCustomEventContainer(listener);
		if(array.contains(listener))
		{
			return false;
		}
		else
		{
			array.add(listener);
			return true;
		}
	
	
	}
	
	
	
	public void addEventListener(CEEventListener event)
	{
		if(event instanceof CEEventCustomListener)
		{
			CEEventCustomListener listener = (CEEventCustomListener) event;
			if(StoreCustomEventListener(listener)){
				
				return;
			}
			else
			{
				System.err.println("Custom EVENT Listener \' " + listener + " \' was Registered." + " It didn't Allow! ");
				return;
			}
		}
		else
		{
			EventListenerShortingAndAdd(event);
		}
	}
	private boolean StoreEventListener(final String key, final CEEventListener eventlistener)
	{
				ArrayList<CEEventListener> listener = getEventListenerContainer(key);
				if(listener.contains(eventlistener))
				{
					listener.add(eventlistener);
					return true;
				}
				else
				{
					System.err.println("EventListener \' " + listener + " \' was Registered." );
					return false;
				}
	}
			
	private void EventListenerShortingAndAdd(CEEventListener listener)
	{
		if(listener instanceof CEEventKeyboardListener){
			StoreEventListener(KEYBOARD_LISTENER, listener);
		}
	}
	
	
	
	private void CEEventDispatcher()
	{
	}
	
	
	public static CEEventDispatcher getInstance()
	{
		return Instance;
	}
	
	
}
