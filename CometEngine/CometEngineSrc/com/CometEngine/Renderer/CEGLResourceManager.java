package com.CometEngine.Renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class CEGLResourceManager {
	
	public interface CEGLResourceComplite
	{
		public void invoke(CEGLResource resource);
	}
	
	private static CEGLResourceManager mInstence = new CEGLResourceManager();
	public static CEGLResourceManager getInstence()
	{
			return mInstence;
	}
	
	public synchronized void putGLResrouce(CEGLResource res)
	{
		if(res.isGLLoaded()== false)
			mLoadingList.add(res);
	}
	protected boolean isLoadeingListEmpty()
	{
		return mLoadingList.isEmpty();
	}
	
	protected synchronized void LoadUPGLResrouce()
	{
		if(mLoadingList.isEmpty() == false)
		{
			Iterator<CEGLResource> itor = mLoadingList.iterator();
			while(itor.hasNext())
			{
				CEGLResource res = itor.next();
				
				if(res.isGLLoaded() == true)
				{
					itor.remove();
					continue;
				}
				
				if(res.isloaded() )
				{
					res.glLoad();
					itor.remove();
				}
			}
		}
	}
	private static final  List<CEGLResource> mLoadingList = new ArrayList<CEGLResource>();
 }