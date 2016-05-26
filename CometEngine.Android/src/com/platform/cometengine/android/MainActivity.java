package com.platform.cometengine.android;

import java.nio.ByteBuffer;

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
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.PNGImageData;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngineInitObject;
import com.CometEngine.CometEngine.PLATFORM;
import com.platform.cometengine.android.gl.CEAndroidGL;
import com.platform.cometengine.io.CEAndroidAsyncFileIO;
import com.platform.cometengine.io.CEAndroidFilePath;
import com.platform.cometengine.io.CEAndroidSyncFileIO;




public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		CEAndroidFilePath.InitFileSysten(getResources(),this);
		
		GLSurfaceView surfaceView = new GLSurfaceView(this);
		surfaceView.setEGLContextClientVersion(2);
        surfaceView.setRenderer(new CEGLSurfaceView.Renderer() {
       
            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
            	System.out.println(" Width : " + width + " Height " + height);
            	PNGImageData r = new PNGImageData();
            	System.out.println(r);
            }
 
            @Override
            public void onDrawFrame(GL10 gl) {
            	CometEngine.getInstece().getRenderer().RenderingCommands();
            	
            }
 
			@Override
			public void onSurfaceCreated(GL10 arg0, javax.microedition.khronos.egl.EGLConfig arg1) {
				CometEngineInitObject init = new CometEngineInitObject();
				init.fileInterface =  new CEAndroidFilePath();
				init.GL =  new CEAndroidGL();
				init.ASyncFileInterface = new CEAndroidAsyncFileIO();
				init.SyncFileInterface = new CEAndroidSyncFileIO();
				CometEngine.getInstece().Run(PLATFORM.CE_ANDROID, init);
			}
        });
        
       
        setContentView(surfaceView);
	}
	
	@Override
	protected void onDestroy() {
		CometEngine.getInstece().ExitCometEngine();
		super.onDestroy();
	}
	
}