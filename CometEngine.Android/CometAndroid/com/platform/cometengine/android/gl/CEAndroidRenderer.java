package com.platform.cometengine.android.gl;

import javax.microedition.khronos.opengles.GL10;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.CometEngineConfig;
import com.CometEngine.CometEngineInit;
import com.platform.cometengine.io.CEAndroidEvnetTask;
import com.platform.cometengine.io.CEAndroidFileUtil;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import cometengine.config.CELaunchConfig;

public class CEAndroidRenderer implements GLSurfaceView.Renderer {
	int width = 0;
	int height = 0;
	private CEAndroidEvnetTask task = null;

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {

		CometEngine.getInstance().getRenderer().setViewSize(CometEngineConfig.getInstance().getCoordWidth(),
				CometEngineConfig.getInstance().getCoordHeight());

		GLES30.glViewport(0, 0, width, height);
		this.width = width;
		this.height = height;
		CometEngine.getInstance().getSceneManager().setScene(CELaunchConfig.CurrentScene);

	}

	@Override
	public void onDrawFrame(GL10 gl) {
		CometEngine.getInstance().getRenderer().RenderingCommands();
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, javax.microedition.khronos.egl.EGLConfig arg1) {

		CometEngineInit init = new CometEngineInit();
		init.GL = new CEAndroidGL();
		init.platformFileUtil = new CEAndroidFileUtil();

		CometEngine.getInstance().Initalize(PLATFORM.CE_ANDROID, init);

		CometEngineConfig.getInstance().Load("CometEngineLaunchConfig.cecfg");
		task = new CEAndroidEvnetTask();
		task.execute();
	}
}