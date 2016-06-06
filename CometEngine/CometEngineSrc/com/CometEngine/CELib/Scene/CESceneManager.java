package com.CometEngine.CELib.Scene;

import com.CometEngine.CELib.Node.CECamera;

public class CESceneManager {
	private static final CESceneManager instence = new CESceneManager();
	public static CESceneManager getInstence()
	{
		return instence;
	}
	
	public void setScene(CEScene scene)
	{
		nowScene = scene;
	}
	
	public CEScene getScene()
	{
		return nowScene;
	}
	public CECamera nowRenderCamera = null;
	//test
	private CEScene nowScene = null;
	
}
