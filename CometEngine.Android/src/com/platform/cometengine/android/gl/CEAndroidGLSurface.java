package com.platform.cometengine.android.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class CEAndroidGLSurface extends GLSurfaceView{

	public CEAndroidGLSurface(Context context) {
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int count = event.getPointerCount();
		System.out.println("Counter " + count);
		System.out.println("Action "+event.getAction());
		for(int i = 0 ; i < count; i++)
		{
			int id  = event.getPointerId(i);
			int index = event.findPointerIndex(id);
			System.out.println("ID : "+ id +" Index "+index +" X " + event.getX(index) + " Y " +event.getY(index));
		}
		
		
	
		
		return true;
	}

}
