package com.CometEngine.Event;

import java.util.ArrayList;

import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.Device.CETouchPad;
import com.CometEngine.Event.CEEventTouch.TouchData;

public class CEEventListenerTouch extends CEEventListener {
	public CEMultiTouchCallBack MULTITOUCH_CALLBACK = defaultmulti;
	public CESingleTouchCallBack SINGLETOUCH_CALLBACK = defaultsingle;

	public static CEEventListenerTouch Create(CEObject target) {
		CEEventListenerTouch listener = new CEEventListenerTouch();
		listener.TargetObject = target;
		return listener;
	}

	public void ListenEvent(CEEventTouch event) {

		for (int i = 0; i < event.dataList.size(); i++) {
			TouchData data = event.dataList.get(i);
			if (data.ID == 0) {
				SINGLETOUCH_CALLBACK.invoke(data.status, data.x, data.y, event);
			}
		}
		MULTITOUCH_CALLBACK.invoke(event.dataList);
		
	

	}

	private static final CEMultiTouchCallBack defaultmulti = new CEMultiTouchCallBack() {
		@Override
		public void invoke(ArrayList<TouchData> touchs) {
		}
	};
	private static final CESingleTouchCallBack defaultsingle = new CESingleTouchCallBack() {
		@Override
		public void invoke(int status, float x, float y, CEEventTouch event) {
		}
	};

	public static interface CEMultiTouchCallBack {
		public void invoke(ArrayList<TouchData> touchs);
	}

	public static interface CESingleTouchCallBack {
		public void invoke(int status, float x, float y, CEEventTouch event);
	}
}
