package com.CometEngine.CELib.Object;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Util.Meth.CEMatrix4f;
import com.CometEngine.Util.Meth.CEPosition2D;

public abstract class CERenderableObject extends CEObject {

	protected CEMatrix4f modelviewmatrix = new CEMatrix4f();

	private void VisitAndGenChildCommands() {
		for (CEObject child : ChildList) {
			if (child instanceof CERenderableObject) {
				RenderingList.add((CERenderableObject) child);
			}
		}
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
			for (int i = 0; i < RenderingList.size(); i++) {
				CERenderCommand command = RenderingList.get(i).genRenderCommand();
				if (command != null) {
					command.execute();
				}
			}
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
