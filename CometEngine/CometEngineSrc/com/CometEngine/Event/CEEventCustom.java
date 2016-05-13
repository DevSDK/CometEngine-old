package com.CometEngine.Event;

import com.CometEngine.Event.Manager.CEEventManager;

public class CEEventCustom extends CEEVent{
	private CEEventCustomMethod method;
	CEEventCustom(CEEventCustomMethod method)
	{
		this.method = method;
	}
	@Override
	public void invoke() {
		method.invoke();
	}

}
