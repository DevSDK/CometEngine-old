package com.CometEngine.Renderer.Commend;

public class CERenderCommandCustom extends CERenderCommand {
	public CERenderCommandCustom(CERenderCustomCommandInvoker method) {
		super(CERenderCommand.COMMENDTYPE.CE_COMMEND_GL_CUSTIOM);
		this.method = method;
	}

	@Override
	public void execute() {
		method.invoke();
	}

	CERenderCustomCommandInvoker method = null;;
}
