package com.platform.cometengine.android;

import com.CometEngine.CometEngine;
import com.platform.cometengine.android.gl.CEAndroidGLSurface;
import com.platform.cometengine.io.CEAndroidFilePath;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class CEAndroid {
	private static CEAndroid Instance = new CEAndroid();

	GLSurfaceView surfaceView = null;

	private CEAndroid() {

	}

	public static CEAndroid getInstance() {
		return Instance;
	}

	public void INIT(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		CEAndroidFilePath.InitFileSysten(activity.getResources(), activity);

		surfaceView = new CEAndroidGLSurface(activity);
		activity.setContentView(surfaceView);

	}

	public void Pause() {
		surfaceView.onPause();
	}

	public void Resume() {
		surfaceView.onResume();
	}

	public void DESTORY() {
		CometEngine.getInstance().EXIT(0);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		android.os.Process.killProcess(android.os.Process.myPid());

	}
}
