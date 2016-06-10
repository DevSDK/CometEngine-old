package com.CometEngine.Tester;

import java.io.File;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Util.Meth.jglm.Mat4;
import com.CometEngine.Util.Meth.jglm.Vec4;



public class Default2DShader extends ShaderProgram{

	
	private static final String VertexFileName   = "com/CometEngine/Tester/TestVertex.txt";

	private static final String FragmentFileName =  "com/CometEngine/Tester/TesterFragment.txt";
	public Default2DShader() {
		super(VertexFileName, FragmentFileName);
		
	}
	
	int location = 0;
	int location2 =0;
	int gColor = 0;
	@Override
	protected void LinkUnifroms() {
		location2 = super.getUniformLoction("ModelViewMatrix");
		location = super.getUniformLoction("glProjection");
		gColor = super.getUniformLoction("gColor");
	
	}

	public void setColor4f(Vec4 vector)
	{
		super.LoadVector4f(gColor, vector.getBuffer());
	}

	public void setProjectionMatrix(Mat4 matrix)
	{
		super.LoadeMatrix4f(location, matrix.getBuffer());
	}
	
	public void setModelViewMatrix(Mat4 matrix)
	{
		super.LoadeMatrix4f(location2, matrix.getBuffer());
	}
	
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Pos");
		super.bindAttribute(1, "Tex");
	}

}
