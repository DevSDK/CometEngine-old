package com.CometEngine.CELib.Button;

public interface CEPickableObject {

	public CEPickCallBack getCallBack();

	public void UnPick();

	public interface CEPickCallBack {
		public void invoke(int status);
	}
}
