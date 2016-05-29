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
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.Event.Manager.CEEventManager;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Tester._RenderingTester;
import com.platform.cometengine.android.gl.CEAndroidGL;
import com.platform.cometengine.io.CEAndroidAsyncFileIO;
import com.platform.cometengine.io.CEAndroidFilePath;
import com.platform.cometengine.io.CEAndroidFileUtil;
import com.platform.cometengine.io.CEAndroidSyncFileIO;




public class MainActivity extends Activity {

	private	MyAsyncTask task = null;
	
	private Renderer glview =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		CEAndroidFilePath.InitFileSysten(getResources(),this);
		
		GLSurfaceView surfaceView = new GLSurfaceView(this);
		surfaceView.setEGLContextClientVersion(2);
		
        surfaceView.setRenderer(glview = new CEGLSurfaceView.Renderer() {
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
				CometEngineInitObject init = new CometEngineInitObject();
				init.GL =  new CEAndroidGL();
				init.platformFileUtil = new CEAndroidFileUtil();
				CometEngine.getInstece().Run(PLATFORM.CE_ANDROID, init);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				task = new MyAsyncTask();	
				task.execute
			 ();
				
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
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		android.os.Process.killProcess(android.os.Process.myPid());

	}
	
	
	
	   public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

		   
			_RenderingTester tester = null ;
			boolean flag = true;
			String []paths = { "1"};
			public void run()
			{
			
				init();
				loop();
			}
			
			public void init()
			{

				tester = new _RenderingTester("1" + ".png");			
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