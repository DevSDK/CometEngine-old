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
	
	

	
	public CERenderer(RENDERER_TYPE target, CEGLInterface gl)
	{
		CEGL.initCEGL(gl);
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
	
	public void VisitRenderTarget()
	{
		
		// TESTER
		CERenderCommandCustom command = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
			@Override
			public void invoke() {	
				CEGL.Clear(CEGL.GL_COLOR_BUFFER_BIT | CEGL.GL_DEPTH_BUFFER_BIT);
				CEGL.ClearColor(0, 1, 1, 1);
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
