package com.CometEngine.Renderer.VBO;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResourceManager;

public class CEIndexBufferObject extends CEBufferObject {
	IntBuffer buffer = null;
	
	private CEIndexBufferObject(IntBuffer vertex)
	{
		 buffer = vertex;
	}
	
	public static CEIndexBufferObject Create(IntBuffer vertex)
	{
		
		CEIndexBufferObject bob = new CEIndexBufferObject(vertex);
		
		CEGLResourceManager.getInstence().putGLResrouce(bob);
		
		return bob;
	}
	
	@Override
	protected void onGLLoad() {
		ID = CEGL.GenBuffers();
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, ID);
		CEGL.BufferData(CEGL.GL_ELEMENT_ARRAY_BUFFER, buffer, CEGL.GL_STATIC_DRAW);
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	@Override
	protected void onGLDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isloaded() {
		return true;
	}

}
