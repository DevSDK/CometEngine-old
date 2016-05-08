package com.CometEngine.Renderer;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import com.CometEngine.Renderer.Commend.CERenderCommand;

public class CERenderer {
	public enum RENDERER_TYPE { CE_RENDERER_NULL ,CE_RENDERER_GL, CE_RENDERER_GLES }
	public CERenderer(RENDERER_TYPE target)
	{
		m_RendererType = target;
	}
	
	public void AddRenderCommend(CERenderCommand render)
	{
		m_RenderingQue.add(render);
	}
	public RENDERER_TYPE getType()
	{
		return m_RendererType;
	}
	public void Sorting()
	{

	}
	
	public void CallRender()
	{
		
	}

	public void TESTER()
	{	
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
	}
	
	private int Renderer_Height = 100;
	private int Renderer_Weidth = 100;
	private RENDERER_TYPE m_RendererType = RENDERER_TYPE.CE_RENDERER_NULL;
	private LinkedList<CERenderCommand> m_RenderingQue = new LinkedList<CERenderCommand>();
}
