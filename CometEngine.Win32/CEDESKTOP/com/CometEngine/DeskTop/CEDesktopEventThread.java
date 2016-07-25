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
import com.CometEngine.Renderer.Shader.Default2DShader;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Resrouce.CEResourceManager;
import com.CometEngine.Util.Buffer.CEBufferUtils;

//TODO: Must Remove Test Code
public class CEDesktopEventThread extends Thread {

	public void run() {
		init();
		loop();
	}

	public void init() {

	}

	public void loop() {

		while (CometEngine.getInstance().isRun()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			CometEngine.getInstance().UpdateEvent();

		}

		System.out.println("CLOSE");
		CometEngine.getInstance().EXIT(0);
	}
}
