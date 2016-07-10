package com.CometEngine.Event;

import com.CometEngine.CELib.Object.CEObject;

public class CEEventListenerMouse extends CEEventListener {

	public CEMouseClick MouseClickCallBack;
	public CEMouseMove MouseMoveCallBack;
	public CEMouseScroll MouseScrollCallBack;

	public interface CEMouseScroll {
		void invoke(double delta);
	}

	public interface CEMouseClick {
		void invoke(int Button, int Status, double XPos, double YPos);
	}

	public interface CEMouseMove {
		void invoke(double XPos, double YPos);
	}

	private CEEventListenerMouse() {

	}

	public static CEEventListenerMouse Create(CEObject Target) {
		CEEventListenerMouse mouse = new CEEventListenerMouse();
		mouse.TargetObject = Target;
		mouse.MouseClickCallBack = CEEventListenerMouse.DEFAULT_CLICKMETHOD;
		mouse.MouseMoveCallBack = CEEventListenerMouse.DEFAULT_MOVEMETHOD;
		mouse.MouseScrollCallBack = CEEventListenerMouse.DEFAULT_SCROLLMETHOD;

		return mouse;
	}

	public static CEEventListenerMouse Create(CEMouseMove MoveCallBack, CEMouseClick ClickCallBack,
			CEMouseScroll ScrollCallBack, CEObject target) {
		CEEventListenerMouse mouse = CEEventListenerMouse.Create(target);
		mouse.MouseClickCallBack = ClickCallBack;
		mouse.MouseMoveCallBack = MoveCallBack;
		mouse.MouseScrollCallBack = ScrollCallBack;
		return mouse;
	}

	public void ListenClickEvent(CEEventMouse event) {
		
		MouseClickCallBack.invoke(event.ActiveButton, event.Status, event.XPos, event.YPos);
		event.isActived = true;
	}

	public void ListenMoveEvent(CEEventMouse event) {
		MouseMoveCallBack.invoke(event.XPos, event.YPos);
	}

	public void ListenScrollEvent(CEEventMouse event) {
		MouseScrollCallBack.invoke(event.scroll);
	}

	private static final CEMouseScroll DEFAULT_SCROLLMETHOD = new CEMouseScroll() {
		@Override
		public void invoke(double delta) {
		}
	};
	private static final CEMouseMove DEFAULT_MOVEMETHOD = new CEMouseMove() {
		@Override
		public void invoke(double XPos, double YPos) {
		}
	};
	private static final CEMouseClick DEFAULT_CLICKMETHOD = new CEMouseClick() {
		@Override
		public void invoke(int Button, int Status, double XPos, double YPos) {
		}
	};

}
