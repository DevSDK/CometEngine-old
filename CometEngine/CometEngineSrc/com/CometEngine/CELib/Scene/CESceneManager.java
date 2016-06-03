package com.CometEngine.CELib.Scene;

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
	
	private CEScene nowScene = null;
	
}
