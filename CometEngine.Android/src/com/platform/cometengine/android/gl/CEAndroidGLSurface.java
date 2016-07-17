package com.platform.cometengine.android.gl;

import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CETouchPad;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class CEAndroidGLSurface extends GLSurfaceView {
	CETouchPad TouchDevice;

	public CEAndroidGLSurface(Context context) {
		super(context);
		CEDeviceManager.getInstance().addDevice("TouchPad", TouchDevice = new CETouchPad());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int count = event.getPointerCount();
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int index = event.getActionIndex();
		int id = event.getPointerId(index);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			System.out.println("Down To " + id);
			TouchDevice.TouchUpdate(event.getX(index), event.getY(index), id, CETouchPad.CE_TOUCH_DOWN);

			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_POINTER_UP:
			System.out.println("Up TO " + id);
			TouchDevice.TouchUpdate(event.getX(index), event.getY(index), id, CETouchPad.CE_TOUCH_UP);
			break;
		case MotionEvent.ACTION_MOVE:
			TouchDevice.TouchUpdate(event.getX(index), event.getY(index), id, CETouchPad.CE_TOUCH_MOVED);
			break;
		default:
			break;
		}

		return true;
	}
}
