package com.CometEngine.CELib.Scene;

import java.util.LinkedList;

import com.CometEngine.CELib.Camera.CECamera;

public class CESceneManager {

	private CESceneManager() {

	}

	private static final CESceneManager Instance = new CESceneManager();

	public static CESceneManager getInstance() {
		return Instance;
	}

	public void setScene(CEScene scene) {
		ExitCurrentScene();
		nowScene = scene;
		nowScene.MANAGER_CALL_ENTER();
	}

	public void ExitCurrentScene() {
		if (nowScene == null)
			return;
		nowScene.MANAGER_CALL_EXIT();
		nowScene = null;
	}

	public CEScene getCurrentScene() {
		return nowScene;
	}

	public CECamera nowRender2DCamera = null;

	private CEScene nowScene = null;

	private LinkedList<CECamera> Camera_List = new LinkedList<CECamera>();
	public CEScene testScene;

}
