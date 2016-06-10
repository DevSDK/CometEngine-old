package com.CometEngine.CELib.Scene;

import java.util.LinkedList;

import com.CometEngine.CELib.Camera.CECamera;

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
	public CECamera nowRender2DCamera = null;
	//test
	private CEScene nowScene = null;
	
	private LinkedList<CECamera> Camera_List = new LinkedList<CECamera>();
	
}
