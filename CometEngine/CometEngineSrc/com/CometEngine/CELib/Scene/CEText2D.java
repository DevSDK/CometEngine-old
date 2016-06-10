package com.CometEngine.CELib.Scene;

import com.CometEngine.CELib.Node.CENode2D;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;

public class CEText2D extends CENode2D	{
	
	private String Text = "" ;
	private CERenderCommand command= new CERenderCommandCustom(new CERenderCustomCommandInvoker() {

		@Override
		public void invoke() {
			Drawing();
		}
	});
	@Override
	public CERenderCommand genRenderCommand() {
		return command;
	}

	@Override
	public void onInit() {
		
	}

	@Override
	public void onDraw() {
		
		
	}
	
}
