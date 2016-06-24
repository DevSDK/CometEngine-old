package com.CometEngine.DeskTop;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Font.BMPFont.FntFile;
import com.CometEngine.Font.BMPFont.character;
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
		CometEngine.getInstance().getSceneManager().setScene(new CEScene());
		
	}
	public void loop()
	{
		while(CometEngine.getInstance().isRun())
		{		
			CometEngine.getInstance().getSceneManager().getScene().tick();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		}
		
		
		System.out.println("CLOSE");
		CometEngine.getInstance().EXIT(0);
	}
}
	