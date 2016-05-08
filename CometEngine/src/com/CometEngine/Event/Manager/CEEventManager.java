package com.CometEngine.Event.Manager;

public class CEEventManager {
	private CEEventManager m_Instence  = null;
	
	public CEEventManager getInstence()
	{
		if(m_Instence == null)
			return m_Instence = new CEEventManager();
		else
			return m_Instence;
	}
	
	
}
