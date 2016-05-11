package com.CometEngine.Event.Manager;

import com.CometEngine.CometEngine;

public class CEEventThread extends Thread{
	public void run()
	{
		init();
		loop();
	}
	public void init()
	{
		
	}
	public void loop()
	{
		while(CometEngine.getInstece().isRun())
		{
			CEEventManager.getInstence().PollAllEvent();
		}
		System.out.println("CLOSE");
	}
}
