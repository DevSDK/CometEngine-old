package com.CometEngine;

import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Event.CEEvent;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLInterface;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CESize;

public class CometEngine {

	private boolean isPauseEvent = false;
	private static CESceneManager sceneManager = CESceneManager.getInstance();
	private CEEventDispatcher EventDispatcher = CEEventDispatcher.getInstance();
	private CESize ViewPortSize = new CESize();

	public void setViewPortSize(int width, int height) {
		ViewPortSize.setSize(width, height);
	}

	public CESize getViewPortSize() {
		return ViewPortSize;
	}

	public CESceneManager getSceneManager() {
		return sceneManager;
	}

	public boolean isPauseEvent() {
		return isPauseEvent;
	}

	public void setPauseEvent(boolean p) {
		this.isPauseEvent = p;
	}

	public void UpdateEvent() {
		EventDispatcher.PullEvents();
	}

	public static CometEngine getInstance() {

		return m_Instance;
	}

	public void RunErrorCheck() // If Not Running Engine Access The Member
								// Exception
	{
		if (IsRun) {
			return;
		} else {
			System.err.println("CometEngine did't Running");
			System.err.println("If Want to the cometengine service you must run engine");

			System.err.println(" CometEngine.getInstence().run() method first invoke ");
			System.exit(-1);
		}
	}

	public CERenderer getRenderer() {

		return renderer;
	}

	public void Initalize(final PLATFORM TargetPlatForm, CometEngineInitObject initdata) {
		if (initdata == null || initdata.GL == null || initdata.platformFileUtil == null) {
			System.out.println("Init Object data have null");
			return;
		}

		if (IsRun == false) {

			IsRun = true;
			m_PlatForm = TargetPlatForm;
			initEngine(initdata);
			initResource();
			PullEvent();
		} else {
			System.err.println(" Still runing. ");
		}
	}

	public void ExitCometEngine() {
		IsRun = false;
		CometEngine.getInstance().getSceneManager().ExitCurrentScene();

	}

	public boolean isRun() {
		return IsRun;
	}

	private void initEngine(CometEngineInitObject object) {
		if (CEFileUtil.FileSystemInit(m_PlatForm, object.platformFileUtil) == false) {
			System.err.println("INIT FILESYSTEM ERROR : FileSystemInit return false");
			CometEngine.getInstance().EXIT(-1);
		}

		if (m_PlatForm == PLATFORM.CE_WIN32 || m_PlatForm == PLATFORM.CE_MAC)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GL, object.GL);
		else if (m_PlatForm == PLATFORM.CE_ANDROID || m_PlatForm == PLATFORM.CE_IOS)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GLES, object.GL);

		if (renderer != null)
			renderer.init();

	}

	private void initResource() {

	}

	private void PullEvent() {
		EventDispatcher.PullEvents();
	}

	public PLATFORM getTargetPlatForm() {
		return m_PlatForm;
	}

	public void UpDataEngine(float delta) {
		renderer.RenderingCommands();

	}

	public void EXIT(int status) {

		IsRun = false;
		CometEngine.getInstance().getSceneManager().ExitCurrentScene();

	}

	private CERenderer renderer = null;
	private static final CometEngine m_Instance = new CometEngine();
	private boolean IsRun = false;

	public enum PLATFORM {
		CE_NULL, CE_WIN32, CE_ANDROID, CE_IOS, CE_MAC
	}

	private PLATFORM m_PlatForm = PLATFORM.CE_NULL;

}
