package com.CometEngine;

import com.CometEngine.Event.Manager.CEEventThread;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.CEPlatformFileInterface;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLInterface;
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

	public void Run(final PLATFORM TargetPlatForm, CEGLInterface gl, CEPlatformFileInterface fileinterface)
	{
		if(IsRun == false)
		{
			
			IsRun  = true;
			m_PlatForm = TargetPlatForm;
			initEngine(gl,fileinterface);
			initResource();
			EventCall();
		}
		else
		{
			System.err.println(" Still runing. ");			
		}
	}
	public void ExitCometEngine()
	{
		
		
		IsRun = false;
		
		
	}
	public boolean isRun()
	{
		return IsRun;
	}
	private void initEngine(CEGLInterface gl, CEPlatformFileInterface fileInterface)
	{
		if(CEFileUtil.FileSystemInit(m_PlatForm, fileInterface) == false)
			System.err.println("INIT FILESYSTEM ERROR : FileSystemInit return false");
			
		if(m_PlatForm == PLATFORM.CE_WIN32 || m_PlatForm == PLATFORM.CE_MAC)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GL, gl);
		else if(m_PlatForm == PLATFORM.CE_ANDROID || m_PlatForm == PLATFORM.CE_IOS)
			renderer = new CERenderer(CERenderer.RENDERER_TYPE.CE_RENDERER_GLES, gl);
	
		if(renderer != null)
			renderer.init ();
		
		
		eventthread = new CEEventThread();
	
		eventthread.start();
	
	}

	private void initResource()
	{
		
	}
	
	private void EventCall()
	{
		
	}
	
	public PLATFORM getTargetPlatForm()
	{
		return m_PlatForm;
	}
	public void UpDataEngine(float delta)
	{
		renderer.RenderingCommands();
		
	}
	

	
	
	private CERenderer renderer = null;	
	private CEEventThread eventthread = null;
	private static CometEngine m_Instence = null;
	private  boolean IsRun = false;
	public enum PLATFORM {	CE_NULL, CE_WIN32, CE_ANDROID, CE_IOS, CE_MAC }
	private PLATFORM m_PlatForm = PLATFORM.CE_NULL;
	
}
