package com.CometEngine.CELib.Button;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Renderer.CEMatrixStack;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CETextButton extends CEButton {
	private CETextLabel TextLabel;

	protected CEPickHandler handler;
	private final CEMatrix4f BoundingBoxMatrix = new CEMatrix4f();

	private CETextButton() {
	}

	public static CETextButton Create(CETextLabel text, CEButtonCallBack callback) {
		CETextButton button = new CETextButton();
		button.TextLabel = text;
		button.CallBack = callback;
		button.BoundingBox.setCamera((CECamera2D) CometEngine.getInstance().getSceneManager().NowRender2DCamera);
		return button;
	}

	private CEBoundBox2D BoundingBox = new CEBoundBox2D(0, 0, null);

	@Override
	public CEBoundBox2D getBoundingBox() {
		BoundingBox.updateBoundingBoxSize(TextLabel.getWidth(), TextLabel.getHeight());
		BoundingBox.updateBoundingBoxTranslate(BoundingBoxMatrix);
		return BoundingBox;
	}

	@Override
	public void UnPick() {
		if (handler != null)
			handler.unPick();
	}

	private final CERenderCommandCustom RENDERCOMMAND = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {

		@Override
		public void invoke() {
			ModelViewMatrix.resetIDENTITY();
			CEMatrixStack.getInstance().GetTopOfStackMatrix(ModelViewMatrix);
			ModelViewMatrix.translate(mPosition.x, mPosition.y, 0).rotate(angle, 0, 0, 1);
			if (handler.isPicked()) {
				TextLabel.getScale().x = scale.x * 1.2f;
				TextLabel.getScale().x = scale.y * 1.2f;
			} else {
				TextLabel.getScale().x = scale.x;
				TextLabel.getScale().x = scale.y;
			}
			CEMatrixStack.getInstance().Push(ModelViewMatrix);
			TextLabel.Drawing();
			BoundingBoxMatrix.setMatrix(ModelViewMatrix);
			CEMatrixStack.getInstance().Pop();
		}
	});

	@Override
	public CERenderCommand genRenderCommand() {
		if (handler == null) {
			CEObject scene = getRoot();
			if (scene instanceof CEScene) {
				if (handler == null)
					handler = new CEPickHandler((CEScene) scene, this);
			}
		}
		return RENDERCOMMAND;
	}

	@Override
	public void onInit() {
	}

	@Override
	public void onDraw() {
	}

	@Override
	public void CleanUp() {

	}

	private final CEFloat2D SizeProxy = new CEFloat2D();

	@Override
	public CEFloat2D getSize() {
		SizeProxy.x = TextLabel.getWidth();
		SizeProxy.y = TextLabel.getHeight();
		return SizeProxy;
	}

}
