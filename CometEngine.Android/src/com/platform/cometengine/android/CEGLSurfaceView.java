package com.platform.cometengine.android;
import android.content.Context;
import android.opengl.GLSurfaceView;

public class CEGLSurfaceView extends GLSurfaceView {

	public CEGLSurfaceView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
	}

}
