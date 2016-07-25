package com.platform.cometengine.android.gl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngineInitObject;
import com.CometEngine.CometEngine.PLATFORM;
import com.platform.cometengine.io.CEAndroidEvnetTask;
import com.platform.cometengine.io.CEAndroidFileUtil;

import TESTSCENES.TESTSCENE2;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import cometengine.config.CELunchConfig;

public class CEAndroidRenderer implements GLSurfaceView.Renderer {
	int width = 0;
	int height = 0;
	private CEAndroidEvnetTask task = null;

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {

		CometEngine.getInstance().getRenderer().setViewSize(CELunchConfig.ViewPortWidth, CELunchConfig.ViewPortHeight);
		GLES30.glViewport(0, 0, width, height);
		this.width = width;
		this.height = height;
		CometEngine.getInstance().getSceneManager().setScene(CELunchConfig.CurrentScene);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		CometEngine.getInstance().getRenderer().RenderingCommands();

	}

	@Override
	public void onSurfaceCreated(GL10 arg0, javax.microedition.khronos.egl.EGLConfig arg1) {
		System.out.println(Thread.currentThread());
		CometEngineInitObject init = new CometEngineInitObject();
		init.GL = new CEAndroidGL();
		init.platformFileUtil = new CEAndroidFileUtil();
		CometEngine.getInstance().Initalize(PLATFORM.CE_ANDROID, init);

		task = new CEAndroidEvnetTask();
		task.execute();
	}
}