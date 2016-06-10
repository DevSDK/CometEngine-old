package com.CometEngine.CELib.Node;

import java.util.LinkedList;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Util.Meth.CEPosition2D;

public abstract class CERenderableNode  extends CENode{
	public abstract CERenderCommand genRenderCommand();
	

	public CERenderableNode()
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
