package com.CometEngine.CELib.Node;

import java.util.LinkedList;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Util.Meth.CEPosition2D;

public abstract class CERenderableObject  extends CEObject{
	public abstract CERenderCommand genRenderCommand();
	

	public CERenderableObject()
	{
		onInit();
	}
	
	public abstract void onInit();
	public abstract void onDraw();
	 

	
	
	public void Drawing() 
	{
			onDraw(); 
	}

	
}
