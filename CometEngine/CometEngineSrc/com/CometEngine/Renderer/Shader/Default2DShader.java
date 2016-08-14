package com.CometEngine.Renderer.Shader;

import java.io.File;
import java.nio.FloatBuffer;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class Default2DShader extends ShaderProgram {

	private static final Default2DShader Instance = new Default2DShader();

	public static Default2DShader getInstance() {
		return Instance;
	}

	private Default2DShader() {
		super(VertexFileName, FragmentFileName);

	}

	private static final String VertexFileName = "Shaders/CometEngine/2D/Vertex.txt";

	private static final String FragmentFileName = "Shaders/CometEngine/2D/Fragment.txt";

	FloatBuffer buffer = CEBufferUtils.CreateFloatBuffer(16);
	int location = 0;
	int location2 = 0;
	int gColor = 0;
	int cameramatrix = 0;
	int Opacity = 0;

	@Override
	protected void LinkUnifroms() {
		location2 = super.getUniformLoction("ModelViewMatrix");
		location = super.getUniformLoction("glProjection");
		gColor = super.getUniformLoction("gColor");
		cameramatrix = super.getUniformLoction("CameraMatrix");
		Opacity = super.getUniformLoction("Opacity");
	}

	public void setOpacity(float opacity) {
		super.LoadFloat(Opacity, opacity);
	}

	public void setColor4f(FloatBuffer vector) {
		super.LoadVector4f(gColor, vector);
	}

	public void CameraMovementMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(cameramatrix, buffer);
	}

	public void setProjectionMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(location, buffer);
	}

	public void setModelViewMatrix(CEMatrix4f matrix) {
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(location2, buffer);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Pos");
		super.bindAttribute(1, "Tex");
	}

}
