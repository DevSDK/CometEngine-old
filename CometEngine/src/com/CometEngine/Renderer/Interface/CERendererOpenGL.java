package com.CometEngine.Renderer.Interface;

import org.lwjgl.opengl.GL11;

import com.CometEngine.Renderer.Commend.CERenderCommand;

public class CERendererOpenGL extends CERendererInterface{

	public CERendererOpenGL() {
		super(RENDERER_TYPE.CE_GLRENDERER_GL);
		init();
	}
	private void init()
	{
	//	GL11.glEnable(GL11.GL_DEPTH_TEST);
	//	GL11.glEnable(GL11.GL_BLEND);
	}
	
	@Override
	public void rendercommand(CERenderCommand commands) {
		
	}

}
