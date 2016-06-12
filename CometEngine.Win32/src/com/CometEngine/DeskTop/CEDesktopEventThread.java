package com.CometEngine.DeskTop;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Event.Manager.CEEventManager;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEResourceManager;
import com.CometEngine.Tester.Tester;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Tester.Default2DShader;
//TODO: Must Remove Test Code
public class CEDesktopEventThread extends Thread{


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
		long time = 0 ;
		while(CometEngine.getInstece().isRun())
		{		
		
			
			CESceneManager.getInstence().getScene().tick();
			CEEventManager.getInstence().PollAllEvent();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		
		System.out.println("CLOSE");
		CometEngine.getInstece().EXIT(0);
	}
}
	