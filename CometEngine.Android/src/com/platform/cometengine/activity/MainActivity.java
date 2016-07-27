package com.platform.cometengine.activity;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import android.app.Activity;
import android.app.NativeActivity;
import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngineInitObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.Renderer.CEGL;
import com.platform.cometengine.android.CEAndroid;
import com.platform.cometengine.android.gl.CEAndroidGL;
import com.platform.cometengine.android.gl.CEAndroidGLSurface;
import com.platform.cometengine.io.CEAndroidAsyncFileIO;
import com.platform.cometengine.io.CEAndroidEvnetTask;
import com.platform.cometengine.io.CEAndroidFilePath;
import com.platform.cometengine.io.CEAndroidFileUtil;
import com.platform.cometengine.io.CEAndroidSyncFileIO;

import TESTSCENES.TESTSCENE2;

public class MainActivity extends Activity {

	private CEAndroid android = CEAndroid.getInstance();

	@Override
	protected void onPause() {
		super.onPause();
		android.Pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		android.Resume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		android.INIT(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		android.DESTORY();

	}

}