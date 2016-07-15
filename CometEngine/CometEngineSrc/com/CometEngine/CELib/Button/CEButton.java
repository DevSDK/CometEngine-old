package com.CometEngine.CELib.Button;

import com.CometEngine.CELib.BoundBox.CEBound2D;
import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Button.CEPickableObject.CEPickCallBack;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.Renderer.Commend.CERenderCommand;

public abstract class CEButton extends CERenderableObject implements CEBound2D, CEPickableObject {

	CEPickCallBack PickCallBack = new CEPickCallBack() {
		@Override
		public void invoke(int status) {
			CallBack.invoke(status);
		}
	};

	@Override
	public CEPickCallBack getCallBack() {
		return PickCallBack;
	}

	CEButtonCallBack CallBack = new CEButtonCallBack() {

		@Override
		public void invoke(int status) {

		}
	};

	public interface CEButtonCallBack {
		public void invoke(int status);
	}
}
