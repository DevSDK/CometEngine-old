package com.CometEngine;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CERenderer;

public class CometEngine {
	

	
	public static CometEngine getInstece()
	{
		if(m_Instence == null)
			return m_Instence = new CometEngine();
		else
			return m_Instence;
	}
	public void RunErrorCheck() //If Not Running Engine Access The Member Exception
	{
		if (IsRun)
		{
			return;
		}
		else 
		{
			System.err.println("CometEngine did't Running");
			System.err.println("If Want to the cometengine service you must run engine");

			System.err.println(" CometEngine.getInstence().run() method first invoke ");
			System.exit(-1);
		}
	}
	public CERenderer getRenderer()
	{
		RunErrorCheck();
		return renderer;
	}

	public void Run(final PLATFORM TargetPlatForm, CEGL gl)
	{
		if(IsRun == false)
		{
			m_PlatForm = TargetPlatForm;
			initEngine(gl);
			initResource();
			StartEventThread();
			IsRun  = true;
		}
		else
		{
			System.err.println(" Still runing. ");			
		}
	}
	public boolean isRun()
	{
		return IsRun;
	}
	private void initEngine(CEGL gl)
	{
		if(m_PlatForm == PLATFORM.CE_WIN32 || m_PlatForm == PLATFORM.CE_MAC)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GL, gl);
		else if(m_PlatForm == PLATFORM.CE_ANDROID || m_PlatForm == PLATFORM.CE_IOS)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GLES, gl);
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
	
	protected void finalize() throws Throwable {
        try{
         IsRun = false;
        }catch(Throwable t){
            throw t;
        }finally{
            System.out.println("Calling finalize of Super Class");
            super.finalize();
        }
	}
	

	
	
	private CERenderer renderer = null;	
	private static CometEngine m_Instence = null;
	private  boolean IsRun = false;
	public enum PLATFORM {	CE_NULL, CE_WIN32, CE_ANDROID, CE_IOS, CE_MAC }
	private PLATFORM m_PlatForm = PLATFORM.CE_NULL;
	
}
