package com.CometEngine;

import com.CometEngine.Renderer.CERenderer;
import com.sun.media.jfxmediaimpl.platform.Platform;
import com.sun.scenario.effect.impl.Renderer.RendererState;

public class CometEngine {
	
	private CERenderer renderer = null;	
	
	public static CometEngine getInstece()
	{
		if(m_Instence == null)
			return m_Instence = new CometEngine();
		else
			return m_Instence;
	}
	
	public void TESTER_RENDERER_SHOUD_BE_DISTORY()
	{
		renderer.TESTER();	
	}
	public void Run(final PLATFORM TargetPlatForm)
	{
		if(IsRun == false)
		{
			m_PlatForm = TargetPlatForm;
			initEngine();
			initResource();
			StartEventThread();
		}
		else
		{
			System.out.println(" Still runing. ");			
			IsRun  = true;
		}
	}
	private void initEngine()
	{
		if(m_PlatForm == PLATFORM.CE_WIN32 || m_PlatForm == PLATFORM.CE_MAC)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GL);
		else if(m_PlatForm == PLATFORM.CE_ANDROID || m_PlatForm == PLATFORM.CE_IOS)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GLES);
	}
	private void initResource()
	{
		
	}
	
	private void StartEventThread()
	{
		System.out.println("Renderer" + renderer.getType());
	}
	
	public PLATFORM getTargetPlatForm()
	{
		return m_PlatForm;
	}
	
	
	
	private static CometEngine m_Instence = null;
	private static boolean IsRun = false;
	public enum PLATFORM {	CE_NULL, CE_WIN32, CE_ANDROID, CE_IOS, CE_MAC }
	private PLATFORM m_PlatForm = PLATFORM.CE_NULL;
	
}
