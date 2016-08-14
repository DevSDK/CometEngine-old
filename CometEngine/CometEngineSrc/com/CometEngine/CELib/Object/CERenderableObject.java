package com.CometEngine.CELib.Object;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Renderer.CEMatrixStack;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEFloat3D;
import com.CometEngine.Util.Meth.CEMatrix4f;
import com.CometEngine.Util.Meth.CEPosition2D;

public abstract class CERenderableObject extends CEObject {

	protected CEMatrix4f ModelViewMatrix = new CEMatrix4f();
	protected CECamera mCamera = CESceneManager.getInstance().NowRender2DCamera;

	protected CEFloat3D ContentSize = new CEFloat3D();

	protected float Opacity = 1.0f;
	protected CEColor4f Color = new CEColor4f(1, 1, 1, 1);

	public CECamera get2DCamera() {
		return mCamera;
	}

	public void setCamera(CECamera camera) {
		this.mCamera = camera;
	}

	public void setOpacity(float opacity) {
		this.Opacity = opacity;
	}

	public float getOpacity() {
		return Opacity;
	}

	private void VisitAndGenChildCommands() {
		for (CEObject child : ChildList) {
			if (child instanceof CERenderableObject) {
				RenderingList.add((CERenderableObject) child);
			}
		}
	}

	private CEColor4f colorproxy = new CEColor4f(1, 1, 1, 1);

	public CEColor4f getColor() {
		colorproxy.Alpha = Color.Alpha;
		colorproxy.Green = Color.Green;
		colorproxy.Blue = Color.Blue;
		colorproxy.Red = Color.Red;

		return colorproxy;
	}

	public void setColor(float r, float g, float b) {
		Color.Red = r;
		Color.Blue = b;
		Color.Green = g;
	}

	public void setColor(CEColor4f color) {
		Color.Red = color.Red;
		Color.Green = color.Green;
		Color.Blue = color.Blue;
	}

	private void UpdateRenderList() {
		RenderingList.clear();
		VisitAndGenChildCommands();
	}

	protected final List<CERenderableObject> RenderingList = new ArrayList<CERenderableObject>();
	private CERenderCommandCustom SpriteRenderCommand = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
		@Override
		public void invoke() {
			if (isChildUpdated == true) {
				UpdateRenderList();
				isChildUpdated = false;
			}
			Drawing();
			CEMatrixStack.getInstanceFor2D().Push(ModelViewMatrix);
			for (int i = 0; i < RenderingList.size(); i++) {
				CERenderCommand command = RenderingList.get(i).genRenderCommand();
				if (command != null) {
					command.execute();
				}
			}
			CEMatrixStack.getInstanceFor2D().Pop();
		}
	});

	public CERenderCommand genRenderCommand() {
		return SpriteRenderCommand;
	}

	public CERenderableObject() {
		onInit();
	}

	public abstract void onInit();

	public abstract void onDraw();

	public void Drawing() {
		if (isActive)
			onDraw();
	}

}
