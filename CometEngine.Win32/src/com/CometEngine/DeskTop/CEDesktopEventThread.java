package com.CometEngine.DeskTop;

import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Event.Manager.CEEventManager;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEResourceManager;
import com.CometEngine.Tester.Tester;
import com.CometEngine.Tester.TesterShader;
import com.CometEngine.Tester._RenderingTester;
//TODO: Must Remove Test Code
public class CEDesktopEventThread extends Thread{
	_RenderingTester tester ;

	public void run()
	{
		init();
		loop();
	}
	
	public void init()
	{
		CESceneManager.getInstence().setScene(new CEScene());
		
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
	