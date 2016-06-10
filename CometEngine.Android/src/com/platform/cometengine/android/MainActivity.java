package com.platform.cometengine.android;

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
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.PNGImageData;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngineInitObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.Event.Manager.CEEventManager;
import com.CometEngine.Renderer.CEGL;
import com.platform.cometengine.android.gl.CEAndroidGL;
import com.platform.cometengine.io.CEAndroidAsyncFileIO;
import com.platform.cometengine.io.CEAndroidFilePath;
import com.platform.cometengine.io.CEAndroidFileUtil;
import com.platform.cometengine.io.CEAndroidSyncFileIO;




public class MainActivity extends Activity {

	private	EVENT_TASK task = null;
	
	private Renderer glview =null;
	
	
	GLSurfaceView surfaceView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		CEAndroidFilePath.InitFileSysten(getResources(),this);
		
		 surfaceView = new GLSurfaceView(this);
		 surfaceView.setEGLContextClientVersion(3);
		
        surfaceView.setRenderer(glview = new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
            	
            	System.out.println(" Width : " + width + " Height " + height);
            	GLES30.glViewport(0, 0, width, height);
            	CometEngine.getInstece().getRenderer().setViewSize(width, height);
            }
            
            @Override
            public void onDrawFrame(GL10 gl) {
            	CometEngine.getInstece().getRenderer().RenderingCommands();
            }
 
			@Override
			public void onSurfaceCreated(GL10 arg0, javax.microedition.khronos.egl.EGLConfig arg1) {
		
				
				
				CometEngineInitObject init = new CometEngineInitObject();
				init.GL =  new CEAndroidGL();
				init.platformFileUtil = new CEAndroidFileUtil();
				CometEngine.getInstece().Run(PLATFORM.CE_ANDROID, init);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				task = new EVENT_TASK();	
				task.execute();
				
			}
        });
        
       
        setContentView(surfaceView);

        
	}
	
	

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		CometEngine.getInstece().EXIT(0);
		moveTaskToBack(true); 
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		android.os.Process.killProcess(android.os.Process.myPid());

	}
	
	
	
	   public class EVENT_TASK extends AsyncTask<Void,Void,Void> {

		   
			boolean flag = true;
			String []paths = { "1"};
			public void run()
			{
			
				init();
				loop();
			}
			
			public void init()
			{
				CESceneManager.getInstence().setScene(new CEScene());
				
			}
			
			int n = 0 ;
			public void loop()
			{
				while(CometEngine.getInstece().isRun() && flag)
				{		
					
					System.out.println("STILL ALIVE : " + n ++);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						break;
					}
					CEEventManager.getInstence().PollAllEvent();
				}
		
			}
				
		   
			 
				
			   @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            
		        }
		         
		   
		         
		        @Override
		        protected void onPostExecute(Void r) {
		            super.onPostExecute(null);
		         
		        }
		         
		        @Override
		        protected void onCancelled() {
		            super.onCancelled();
		            flag = false;
		        }

				@Override
				protected Void doInBackground(Void... params) {
				
					run();
					return null;
				}
		         
		   
	   
	    }
	
}