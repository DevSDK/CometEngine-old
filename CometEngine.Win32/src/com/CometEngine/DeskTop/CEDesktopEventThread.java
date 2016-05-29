package com.CometEngine.DeskTop;

import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.Event.Manager.CEEventManager;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEResourceManager;
import com.CometEngine.Tester.Tester;
import com.CometEngine.Tester._RenderingTester;
//TODO: Must Remove Test Code
public class CEDesktopEventThread extends Thread{
	_RenderingTester tester ;

	String []paths = { "1"};
	_RenderingTester [] testers = new _RenderingTester[6];
	public void run()
	{
		init();
		loop();
	}
	
	public void init()
	{

		testers[0] = new _RenderingTester("1" + ".png");			
	}
	public void loop()
	{
		while(CometEngine.getInstece().isRun())
		{		
			
		
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			CEEventManager.getInstence().PollAllEvent();
		}
		
		
		System.out.println("CLOSE");
		CometEngine.getInstece().EXIT(0);
	}
}
	