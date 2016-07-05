package com.CometEngine.Renderer.VBO;

import java.nio.FloatBuffer;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResourceManager;

public class CEVertexBufferObject extends CEBufferObject {

	private FloatBuffer buffer = null;
	private int CoordSize = 0;

	private CEVertexBufferObject(FloatBuffer vertex) {
		buffer = vertex;
	}

	public static CEVertexBufferObject Create(FloatBuffer vertex) {

		CEVertexBufferObject bob = new CEVertexBufferObject(vertex);

		CEGLResourceManager.getInstence().putGLResrouce(bob);

		return bob;
	}

	public int getCoordSize() {
		return CoordSize;
	}

	@Override
	protected void onGLLoad() {
		ID = CEGL.GenBuffers();
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, ID);
		CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, buffer, CEGL.GL_STATIC_DRAW);
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);
	}

	@Override
	protected void onGLDelete() {

	}

	@Override
	public boolean isloaded() {
		return true;
	}

}
