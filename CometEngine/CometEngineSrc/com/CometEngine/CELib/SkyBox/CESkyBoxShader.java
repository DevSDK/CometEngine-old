package com.CometEngine.CELib.SkyBox;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CESkyBoxShader extends ShaderProgram {

	private static final String VertexFileName = "com/CometEngine/SkyBox/Vertex.txt";
	private static final String FragmentFileName = "com/CometEngine/SkyBox/Fragment.txt";

	public CESkyBoxShader() {
		super(VertexFileName, FragmentFileName);
	}

	int projectionMatrix = 0;
	int rotation = 0;

	@Override
	protected void LinkUnifroms() {
		projectionMatrix = super.getUniformLoction("Projection");
		rotation = super.getUniformLoction("rotation");
	}

	FloatBuffer buffer = CEBufferUtils.CreateFloatBuffer(16);

	public void setRotations(float x, float y, float z) {
		super.LoadVector3f(rotation, x, y, z);
	}

	public void setProjectionMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(projectionMatrix, buffer);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Vertex");
	}

}
