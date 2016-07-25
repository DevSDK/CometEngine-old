package com.CometEngine.CELib.Button;

import com.CometEngine.CELib.BoundBox.CEBound2D;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Device.CETouchPad;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Event.CEEventListenerTouch;
import com.CometEngine.Event.CEEventMouse;
import com.CometEngine.Event.CEEventTouch;

public class CEPickHandler {
	private CEEventListenerMouse mouseListener = null;
	private CEEventListenerTouch touchlistener = null;

	private boolean isPicked = false;
	private CEEventListenerMouse.CEMouseClick MouseClick = new CEEventListenerMouse.CEMouseClick() {
		@Override
		public void invoke(int Button, int Status, double XPos, double YPos, CEEventMouse event) {
			if (event.getTargetObject() instanceof CEBound2D) {
				CEBound2D bound = (CEBound2D) event.getTargetObject();
				if (Status == 1)
					if (bound.getBoundingBox().getAABB().isContainPoint((float) XPos, (float) YPos)) {
						((CEPickableObject) event.getTargetObject()).getCallBack().invoke(1);
						isPicked = true;
					}
				if (Status == 0) {
					if (bound.getBoundingBox().getAABB().isContainPoint((float) XPos, (float) YPos)) {
						if (isPicked == true)
							((CEPickableObject) event.getTargetObject()).getCallBack().invoke(0);
					}
					isPicked = false;

				}

			}
		}
	};
	private CEEventListenerTouch.CESingleTouchCallBack TouchOnce = new CEEventListenerTouch.CESingleTouchCallBack() {

		@Override
		public void invoke(int status, float x, float y, CEEventTouch event) {

			if (event.getTargetObject() instanceof CEBound2D) {
				CEBound2D bound = (CEBound2D) event.getTargetObject();
				if (status == CETouchPad.CE_TOUCH_DOWN)
					if (bound.getBoundingBox().getAABB().isContainPoint(x, y)) {
						((CEPickableObject) event.getTargetObject()).getCallBack().invoke(1);
						isPicked = true;
					}
				if (status == CETouchPad.CE_TOUCH_UP) {
					if (bound.getBoundingBox().getAABB().isContainPoint(x, y)) {
						if (isPicked == true) {
							((CEPickableObject) event.getTargetObject()).getCallBack().invoke(0);
						}
					}
					isPicked = false;

				}
			}
		}
	};

	public boolean isPicked() {
		return isPicked;
	}

	protected void unPick() {
		isPicked = false;
	}

	public CEPickHandler(CEScene scene, CEObject target) {

		touchlistener = CEEventListenerTouch.Create(target);
		touchlistener.SINGLETOUCH_CALLBACK = TouchOnce;
		CEEventDispatcher.getInstance().addEventListener(touchlistener, scene);
		mouseListener = CEEventListenerMouse.Create(target);
		mouseListener.MouseClickCallBack = MouseClick;
		CEEventDispatcher.getInstance().addEventListener(mouseListener, scene);
	}

}
