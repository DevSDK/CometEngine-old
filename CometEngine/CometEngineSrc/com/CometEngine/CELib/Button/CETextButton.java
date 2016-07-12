package com.CometEngine.CELib.Button;

import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Renderer.Commend.CERenderCommand;

public class CETextButton extends CEButton {
	private CETextLabel TextLabel;

	private CETextButton() {
	}

	public static CETextButton Create(CETextLabel text, CEButtonCallBack callback) {
		CETextButton button = new CETextButton();
		button.TextLabel = text;
		button.CallBack = callback;
		button.add(text);
		return button;
	}

	@Override
	public CEBoundBox2D getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UnPick() {
		// TODO Auto-generated method stub

	}

	@Override
	public CERenderCommand genRenderCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void CleanUp() {
		// TODO Auto-generated method stub

	}

}
