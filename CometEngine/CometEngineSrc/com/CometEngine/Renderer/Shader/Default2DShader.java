package com.CometEngine.Renderer.Shader;

import java.io.File;
import java.nio.FloatBuffer;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;



public class Default2DShader extends ShaderProgram{

	
	private static final String VertexFileName   = "com/CometEngine/Tester/TestVertex.txt";

	private static final String FragmentFileName =  "com/CometEngine/Tester/TesterFragment.txt";
	public Default2DShader() {
		super(VertexFileName, FragmentFileName);
		
	}
	FloatBuffer buffer = CEBufferUtils.CreateFloatBuffer(16);
	int location = 0;
	int location2 =0;
	int gColor = 0;
	@Override
	protected void LinkUnifroms() {
		location2 = super.getUniformLoction("ModelViewMatrix");
		location = super.getUniformLoction("glProjection");
		gColor = super.getUniformLoction("gColor");
	
	}

	public void setColor4f(FloatBuffer vector)
	{
		super.LoadVector4f(gColor, vector);
	}

	public void setProjectionMatrix(CEMatrix4f matrix)
	{
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(location, buffer);
	}
	
	public void setModelViewMatrix(CEMatrix4f matrix)
	{
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(location2, buffer);
	}
	
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Pos");
		super.bindAttribute(1, "Tex");
	}

}
