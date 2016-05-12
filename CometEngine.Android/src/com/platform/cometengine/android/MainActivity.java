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
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWNativeGLX;
import org.lwjgl.glfw.GLFWNativeX11;
import org.lwjgl.opengles.*;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.platform.cometengine.android.gl.CEAndroidGL;

import org.lwjgl.egl.*;
import org.lwjgl.*;




public class MainActivity extends Activity {

	@Override
	

	protected void onCreate(Bundle savedInstanceState) {
		
	
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        super.onCreate(savedInstanceState);
        
        CometEngine.getInstece().Run(PLATFORM.CE_ANDROID, new CEAndroidGL());
		
		GLSurfaceView surfaceView = new GLSurfaceView(this);
		
        surfaceView.setRenderer(new GLSurfaceView.Renderer() {
        
            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
            	System.out.println(" Width : " + width + " Height " + height);
            
            }
 
            @Override
            public void onDrawFrame(GL10 gl) {
            	CometEngine.getInstece().getRenderer().RenderingCommands();
            	
            }
 
			@Override
			public void onSurfaceCreated(GL10 arg0, javax.microedition.khronos.egl.EGLConfig arg1) {

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