package com.platform.cometengine.android.gl;

import com.CometEngine.CometEngine;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CETouchPad;

import android.content.Context;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class CEAndroidGLSurface extends GLSurfaceView {
	CETouchPad TouchDevice;
	private static CEAndroidGLSurface Instance;
	private CEAndroidRenderer renderer;

	@Override
	public void setRenderer(Renderer renderer) {
		super.setRenderer(renderer);
		this.renderer = (CEAndroidRenderer) renderer;

	}

	public CEAndroidGLSurface(Context context) {
		super(context);
		Instance = this;

		this.setEGLContextClientVersion(3);

		this.setRenderer(renderer = new CEAndroidRenderer());
		CEDeviceManager.getInstance().addDevice("TouchPad", TouchDevice = new CETouchPad());
	}

	public static CEAndroidGLSurface getInstance() {
		return Instance;
	}

	@Override

	public void onPause() {

		super.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int count = event.getPointerCount();
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int index = event.getActionIndex();
		int framewidth = renderer.width;
		int frameheight = renderer.height;
		int height = CometEngine.getInstance().getRenderer().getRenderHeight();
		if (CometEngine.getInstance().getRenderer() == null)
			return false;
		float ratx = ((float) CometEngine.getInstance().getRenderer().getRenderWidth()) / (float) framewidth;

		float raty = ((float) CometEngine.getInstance().getRenderer().getRenderHeight()) / (float) frameheight;

		int id = event.getPointerId(index);
		float touchX = 0;
		float touchY = 0;

		touchX = ratx * (event.getX(index));
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			touchY = raty * (((float)this.getHeight() - event.getY(index)));
		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			touchY = raty * ((float)this.getHeight() - event.getY(index));
		}

		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			TouchDevice.TouchUpdate(ratx * (event.getX(index)), touchY, id, CETouchPad.CE_TOUCH_DOWN);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_POINTER_UP:
			System.out.println("Up TO " + id);
			TouchDevice.TouchUpdate(ratx * event.getX(index), touchY, id, CETouchPad.CE_TOUCH_UP);
			break;
		case MotionEvent.ACTION_MOVE:
			TouchDevice.TouchUpdate(ratx * event.getX(index), touchY, id, CETouchPad.CE_TOUCH_MOVED);
			break;
		default:
			break;
		}

		return true;
	}
}
