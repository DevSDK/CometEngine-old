package com.cometengine.android;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.EGLConfig;
import android.opengl.GLES11;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        

GLSurfaceView surfaceView = new GLSurfaceView(this);
        surfaceView.setRenderer(new GLSurfaceView.Renderer() {
        
            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
            	System.out.println(" Width : " + width + " Height " + height);
                GLES20.glClearColor(0f, 0f, 0f, 1f);
            }
 
            @Override
            public void onDrawFrame(GL10 gl) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
                GLES20.glClearColor(1, 0, 0, 1);
                
            }

			@Override
			public void onSurfaceCreated(GL10 arg0, javax.microedition.khronos.egl.EGLConfig arg1) {
				
			}
        });
 
        setContentView(surfaceView);
	}
}