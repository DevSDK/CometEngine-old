package com.CometEngine.Renderer.VAO;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResource;
import com.CometEngine.Renderer.CEGLResourceManager;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CEVAO extends CEGLResource {

	private final ArrayList<Integer> VBOS = new ArrayList<Integer>();

	public static class CEVboObject {
		public CEVboObject(int index, int coordsize, float[] vertexs) {
			FloatBuffer buffer = CEBufferUtils.ArrayToBuffer(vertexs);
			this.vertexs = buffer;
			this.index = index;
			this.coordsize = coordsize;

		}

		private float[] FloatTofloatArray(ArrayList<Float> array) {
			float[] arr = new float[array.size()];
			for (int i = 0; i < array.size(); i++) {
				arr[i] = array.get(i);
			}
			return arr;
		}

		public CEVboObject(int index, int coordsize, ArrayList<Float> VertexList) {

			FloatBuffer buffer = CEBufferUtils.ArrayToBuffer(FloatTofloatArray(VertexList));

			this.vertexs = buffer;
			this.index = index;
			this.coordsize = coordsize;

		}

		public CEVboObject(int index, int coordsize, int buffersize) {
			this.isBufferObject = true;
			this.index = index;
			this.coordsize = coordsize;
			this.buffersize = buffersize;
		}

		protected boolean isBufferObject = false;
		protected int buffersize = 0;
		protected int offsetSize = 0;

		protected FloatBuffer vertexs = null;
		protected int index = -1;
		protected int coordsize = -1;

	}

	private int ID = 0;
	private int glStatment = CEGL.GL_STATIC_DRAW;
	private IntBuffer IboData = null;
	private CEVboObject[] VboData = null;

	public static CEVAO Create(Object ManagementKey, int[] ibo, CEVboObject[] VboData, int glStatment) {
		if (ManagementKey == null) {
			CEVAO vao = new CEVAO();
			IntBuffer buffer = CEBufferUtils.ArrayToBuffer(ibo);
			vao.IboData = buffer;
			vao.VboData = VboData;
			vao.glStatment = glStatment;
			CEGLResourceManager.getInstence().putGLResrouce(vao);
			return vao;
		} else if (VAOTABLE.containsKey(ManagementKey)) {
			return VAOTABLE.get(ManagementKey);
		}
		CEVAO vao = new CEVAO();

		IntBuffer buffer = CEBufferUtils.ArrayToBuffer(ibo);
		vao.IboData = buffer;
		vao.VboData = VboData;
		vao.glStatment = glStatment;
		CEGLResourceManager.getInstence().putGLResrouce(vao);
		VAOTABLE.put(ManagementKey, vao);
		return vao;
	}

	private CEVAO() {
	};

	private static final HashMap<Object, CEVAO> VAOTABLE = new HashMap<Object, CEVAO>();

	public static CEVAO Create(Object ManagementKey, int[] ibo, CEVboObject[] VboData) {
		if (ManagementKey == null) {
			CEVAO vao = new CEVAO();
			IntBuffer buffer = CEBufferUtils.ArrayToBuffer(ibo);
			vao.IboData = buffer;
			vao.VboData = VboData;
			CEGLResourceManager.getInstence().putGLResrouce(vao);
			return vao;
		} else if (VAOTABLE.containsKey(ManagementKey)) {
			return VAOTABLE.get(ManagementKey);
		}
		CEVAO vao = new CEVAO();
		IntBuffer buffer = CEBufferUtils.ArrayToBuffer(ibo);
		vao.IboData = buffer;
		vao.VboData = VboData;
		CEGLResourceManager.getInstence().putGLResrouce(vao);
		VAOTABLE.put(ManagementKey, vao);
		return vao;
	}

	@Override
	protected void onGLLoad() {
		ID = CEGL.GenVertexArrays();

		CEGL.BindVertexArray(ID);

		StoreIndexBuffer(IboData);

		for (CEVboObject vbo : VboData) {
			if (vbo.isBufferObject == true) {
				CreateBuffer(vbo.index, vbo.coordsize, vbo.buffersize);
			} else {
				if (!(vbo == null || vbo.index < 0 || vbo.coordsize < 0 || vbo.vertexs == null))
					StoreVertexBuffer(vbo.index, vbo.coordsize, vbo.vertexs);
			}
		}

		CEGL.BindVertexArray(0);
	}

	private void CreateBuffer(int index, int coord, int size) {
		int vbo = CEGL.GenBuffers();
		VBOS.add(vbo);

		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vbo);
		CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, size, glStatment);
		CEGL.VertexAttribPointer(index, coord, CEGL.GL_FLOAT, false, 0, 0);

		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);
	}

	public int getVBOID(int idx) {
		if (VBOS.isEmpty() == false)
			return VBOS.get(idx);
		return 0;
	}

	private void StoreIndexBuffer(IntBuffer buffer) {
		int ibo = CEGL.GenBuffers();
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, ibo);
		CEGL.BufferData(CEGL.GL_ELEMENT_ARRAY_BUFFER, buffer, glStatment);

	}

	private void StoreVertexBuffer(int index, int coods, FloatBuffer Vertexs) {
		int vbo = CEGL.GenBuffers();
		VBOS.add(vbo);

		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vbo);
		CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, Vertexs, glStatment);
		CEGL.VertexAttribPointer(index, coods, CEGL.GL_FLOAT, false, 0, 0);

		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);
	}

	@Override
	protected void onGLDelete() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isloaded() {
		return true;
	}

	public int getID() {
		return ID;
	}

}
