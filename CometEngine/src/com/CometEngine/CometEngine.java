package com.CometEngine;

import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Renderer.Interface.CERendererInterface;
import com.CometEngine.Renderer.Interface.CERendererInterface.RENDERER_TYPE;
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
	
	
	public void Run(PLATFORM TargetPlatForm)
	{
		if(IsRun == false)
		{
			m_PlatForm = TargetPlatForm;
			
			
			if(TargetPlatForm == PLATFORM.CE_WIN32 || TargetPlatForm == PLATFORM.CE_MAC)
				renderer = new CERenderer(RENDERER_TYPE.CE_GLRENDERER_GL);
			else if(TargetPlatForm == PLATFORM.CE_ANDROID || TargetPlatForm == PLATFORM.CE_IOS)
				renderer = new CERenderer(RENDERER_TYPE.CE_GLRENDERER_GLES);
			
			initResource();
			StartEventThread();
		}
		else
		{
			System.out.println(" Still runing. ");			
			IsRun  = true;
		}
	}
	
	private void initResource()
	{
		
	}
	
	private void StartEventThread()
	{
		System.out.println("Init HELLOWORLD");
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
