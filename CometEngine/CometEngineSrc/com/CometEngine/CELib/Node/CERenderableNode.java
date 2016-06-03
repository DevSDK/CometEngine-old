package com.CometEngine.CELib.Node;

import java.util.LinkedList;

import com.CometEngine.Renderer.Commend.CERenderCommand;

public abstract class CERenderableNode  extends CENode{
	public abstract CERenderCommand genRenderCommand();
	public CERenderableNode(NODE_TYPE type)
	{
		super(type);
		onInit();
	}
	
	public abstract void onInit();
	public abstract void onDraw();
	

	
	
	public void Drawing() 
	{
			onDraw(); 
	}

	
}
