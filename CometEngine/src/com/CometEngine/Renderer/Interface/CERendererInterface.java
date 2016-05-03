package com.CometEngine.Renderer.Interface;

import com.CometEngine.Renderer.Commend.CERenderCommand;

public abstract class CERendererInterface {
	public enum RENDERER_TYPE
	{
		CE_GLRENDERER_NULL ,CE_GLRENDERER_GL, CE_GLRENDERER_GLES
	}
	private RENDERER_TYPE m_RenderType = RENDERER_TYPE.CE_GLRENDERER_NULL;
	public abstract void rendercommand(CERenderCommand commands);
	
	public CERendererInterface(RENDERER_TYPE render)
	{
		this.m_RenderType = render;
	}
	public RENDERER_TYPE getRendererType()
	{
		return m_RenderType;
	}
}
