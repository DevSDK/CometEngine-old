package com.CometEngine.Renderer;

import java.util.LinkedList;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Interface.CERendererInterface;
import com.CometEngine.Renderer.Interface.CERendererInterface.RENDERER_TYPE;
import com.CometEngine.Renderer.Interface.CERendererOpenGL;
import com.CometEngine.Renderer.Interface.CERendererOpenGLES;

public class CERenderer {
	private CERendererInterface m_Render = null;
	private LinkedList<CERenderCommand> m_RenderingQue = new LinkedList<>();
	public CERenderer(CERendererInterface.RENDERER_TYPE target)
	{
		if( RENDERER_TYPE.CE_GLRENDERER_NULL == target)
		{
			m_Render = null;
		}
		else if(RENDERER_TYPE.CE_GLRENDERER_GL == target)
		{
			m_Render = new CERendererOpenGL();
		}
		else if(RENDERER_TYPE.CE_GLRENDERER_GLES == target)
		{
			m_Render = new CERendererOpenGLES();
		}
	}
	
	public void AddRenderCommend(CERenderCommand render)
	{
		m_RenderingQue.add(render);
	}
	public RENDERER_TYPE getType()
	{
		return m_Render.getRendererType();
	}
	public void Sorting()
	{

	}
	public void Render()
	{
		
	}
}
