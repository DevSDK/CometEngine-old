package com.CometEngine.Renderer.VAO;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResource;
import com.CometEngine.Renderer.CEGLResourceManager;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CEVAO extends CEGLResource{
	
	public static class CEVboObject
	{
		public CEVboObject(int index, int coordsize, float[] vertexs)
		{
			FloatBuffer buffer = CEBufferUtils.ArrayToBuffer(vertexs);
			this.vertexs = buffer;
			this.index = index;
			this.coordsize = coordsize;
		}
		
		protected FloatBuffer vertexs =null;
		protected int index = -1;
		protected int coordsize = -1;
		
	
	}
	
	
	private int ID = 0;
	
	private IntBuffer IboData = null;
	private CEVboObject [] VboData = null; 
	
	
	public static CEVAO Create(int[] ibo, CEVboObject [] VboData)
	{
		CEVAO vao = new CEVAO();
		IntBuffer buffer = CEBufferUtils.ArrayToBuffer(ibo);
		vao.IboData = buffer;
		vao.VboData = VboData;
		
		CEGLResourceManager.getInstence().putGLResrouce(vao);
		
		return vao;
	}
	
	@Override
	protected void onGLLoad() {
		ID = CEGL.GenVertexArrays();
		 
		CEGL.BindVertexArray(ID);
		
		StoreIndexBuffer(IboData);
		
		
		for(CEVboObject vbo : VboData)
		{
			if( !( vbo == null ||vbo.index < 0 || vbo.coordsize < 0 ||vbo.vertexs == null ) )
				StoreVertexBuffer(vbo.index, vbo.coordsize, vbo.vertexs);
		}
		
		CEGL.BindVertexArray(0);
	}

	private void StoreIndexBuffer(IntBuffer buffer)
	{
		int ibo = CEGL.GenBuffers();
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, ibo);
		CEGL.BufferData(CEGL.GL_ELEMENT_ARRAY_BUFFER, buffer, CEGL.GL_STATIC_DRAW);
		
	}
	
	private void StoreVertexBuffer(int index,int coods ,FloatBuffer Vertexs)
	{
		int vbo = CEGL.GenBuffers();
	
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vbo);
		CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, Vertexs, CEGL.GL_STATIC_DRAW);
		CEGL.VertexAttribPointer(index, coods, CEGL.GL_FLOAT, false, 0, 0);
		
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);

	}
	
	@Override
	protected void onGLDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isloaded() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public int getID()
	{
		return ID;
	}

	

	
}
