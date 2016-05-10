package com.CometEngine.Renderer;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import com.CometEngine.CometEngine;
import com.CometEngine.Commend.Manager.CERenderCommandManager;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;

public class CERenderer {
	public enum RENDERER_TYPE { CE_RENDERER_NULL ,CE_RENDERER_GL, CE_RENDERER_GLES }
	private  CEGL GL = null;
	
	public CEGL getGL()
	{
			return GL;
	
	}
	
	public CERenderer(RENDERER_TYPE target, CEGL gl)
	{
		m_RendererType = target;
		this.GL = gl;
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
	
	public void VisitRenderTarget()
	{
		CERenderCommandCustom command = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
			
			@Override
			public void invoke() {	
				GL.Clear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				GL.ClearColor(1, 0, 0, 1);
			}
		});
		
		CERenderCommandManager.getInstence().AddCommand(command);
	}
	public void RenderingCommands()
	{
		VisitRenderTarget();
		CERenderCommandManager.getInstence().InvokeAllCommands();
	}


	private int Renderer_Height = 100;
	private int Renderer_Weidth = 100;
	private RENDERER_TYPE m_RendererType = RENDERER_TYPE.CE_RENDERER_NULL;
	private LinkedList<CERenderCommand> m_RenderingQue = new LinkedList<CERenderCommand>();
}
