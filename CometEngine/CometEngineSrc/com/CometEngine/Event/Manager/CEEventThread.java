package com.CometEngine.Event.Manager;

import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEResourceManager;
import com.CometEngine.Tester.Tester;
import com.CometEngine.Tester._RenderingTester;
//TODO: Must Remove Test Code
public class CEEventThread extends Thread{
	_RenderingTester tester ;

	String []paths = { "1", "2" , "3", "4" };
	_RenderingTester [] testers = new _RenderingTester[6];
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
			int i = 0 ;
			for(String path : paths)
			{ 
				testers[i] = new _RenderingTester(path + ".png");
				System.out.println(testers[i].filepath);
				testers[i++].logging();

				}
			CEResourceManager.getInstence().ShowLog();
			CETextureManager.getInstence().showLog();
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
