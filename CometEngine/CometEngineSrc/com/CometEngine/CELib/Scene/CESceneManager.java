package com.CometEngine.CELib.Scene;

import java.util.LinkedList;

import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Camera.CECamera3D;

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
		NowRender3DCamera = scene.get3DCamera();
		NowRender2DCamera = scene.get2DCamera();
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

	public CECamera3D NowRender3DCamera;

	public CECamera NowRender2DCamera;

	private CEScene nowScene = null;

	public CEScene testScene;

}
