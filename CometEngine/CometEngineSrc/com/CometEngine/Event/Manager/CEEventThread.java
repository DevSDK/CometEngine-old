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
			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("CLOSE");
	}
}
