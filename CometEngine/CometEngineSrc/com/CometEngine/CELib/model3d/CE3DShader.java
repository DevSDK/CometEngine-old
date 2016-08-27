package com.CometEngine.CELib.model3d;

import java.nio.FloatBuffer;

import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CE3DShader extends ShaderProgram {

	private static final CE3DShader Instance = new CE3DShader();

	public static CE3DShader getInstance() {
		return Instance;
	}

	private static final String VertexShader_FileName = "Shaders/CometEngine/3D/Vertex.txt";
	private static final String FragmenetShder_FileName = "Shaders/CometEngine/3D/Fragment.txt";

	private int ModelViewMatrix = 0;
	private int CameraMatrix = 0;
	private int ProjectionMatrix = 0;

	private final FloatBuffer buffer = FloatBuffer.allocate(16);

	private CE3DShader() {
		super(VertexShader_FileName, FragmenetShder_FileName);
	}

	public void setModelViewMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
	
		super.LoadeMatrix4f(ModelViewMatrix,buffer );
	}

	public void setCamreaMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(CameraMatrix, buffer);
	}

	public void setProjectionMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(ProjectionMatrix, buffer);
	}

	@Override
	protected void LinkUnifroms() {
		ModelViewMatrix = super.getUniformLoction("ModelViewMatrix");
		CameraMatrix = super.getUniformLoction("CmaeraMatrix");
		ProjectionMatrix = super.getUniformLoction("glProjection");
	}

	@Override
	protected void bindAttributes() {

		super.bindAttribute(0, "Pos");

		super.bindAttribute(0, "Normal");
		super.bindAttribute(2, "Tex");
	}

}
