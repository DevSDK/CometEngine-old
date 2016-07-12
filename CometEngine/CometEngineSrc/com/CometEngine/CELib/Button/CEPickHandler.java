package com.CometEngine.CELib.Button;

import com.CometEngine.CELib.BoundBox.CEBound2D;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Event.CEEventMouse;

public class CEPickHandler {
	private CEEventListenerMouse mouseListener = null;
	private boolean isPicked = false;
	private CEEventListenerMouse.CEMouseClick MouseClick = new CEEventListenerMouse.CEMouseClick() {
		@Override
		public void invoke(int Button, int Status, double XPos, double YPos, CEEventMouse event) {

			System.out.println("Disptacher Status " + event.getTargetObject());
			if (event.getTargetObject() instanceof CEBound2D) {
				CEBound2D bound = (CEBound2D) event.getTargetObject();
				if (Status == 1)
					if (bound.getBoundingBox().getAABB().isContainPoint((float) XPos, (float) YPos)) {
						((CEPickableObject) event.getTargetObject()).getCallBack().invoke(Status);
						isPicked = true;
					}
				if (Status == 0) {
					if (bound.getBoundingBox().getAABB().isContainPoint((float) XPos, (float) YPos)) {
						if (isPicked == true)
							((CEPickableObject) event.getTargetObject()).getCallBack().invoke(Status);
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
		mouseListener = CEEventListenerMouse.Create(target);
		mouseListener.MouseClickCallBack = MouseClick;
		CEEventDispatcher.getInstance().addEventListener(mouseListener, scene);
	}

}
