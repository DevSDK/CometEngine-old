package com.CometEngine.Renderer;

import com.CometEngine.Renderer.CEGLResourceManager.CEGLResourceComplite;


public abstract class CEGLResource {
	protected CEGLResourceComplite LoadCompliteCallBack = null;
	protected CEGLResourceComplite DeleteCompliteCallBack = null;
	private boolean isloaded = false;
	protected void glLoad()
	{
		onGLLoad();
		isloaded = true;
		
		if(DeleteCompliteCallBack != null)
			DeleteCompliteCallBack.invoke(this);

	}
	protected boolean isGLLoaded()
	{
		return isloaded;
	}
	protected void glDelete()
	{
		onGLDelete();
		isloaded = false;
		
		if(LoadCompliteCallBack != null)
			LoadCompliteCallBack.invoke(this);
		
	}
	
	public CEGLResource()
	{
	
	}
	
	public CEGLResource(CEGLResourceComplite onLoadCompliteCallBack, CEGLResourceComplite onDeleteCompliteCallBack)
	{
		this.LoadCompliteCallBack = onDeleteCompliteCallBack;
		this.DeleteCompliteCallBack = onDeleteCompliteCallBack;
	}
	
	protected abstract void onGLLoad();
	protected abstract void onGLDelete();
	public abstract boolean isloaded();

	
	
}
