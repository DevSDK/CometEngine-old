package com.CometEngine.Tester;

import java.io.File;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Util.Meth.jglm.Mat4;



public class TesterShader extends ShaderProgram{

	
	private static final String VertexFileName   = "com/CometEngine/Tester/TestVertex.txt";

	private static final String FragmentFileName =  "com/CometEngine/Tester/TesterFragment.txt";
	public TesterShader() {
		super(VertexFileName, FragmentFileName);
		
	}
	
	int location = 0;
	int location2 =0;
	@Override
	protected void LinkUnifroms() {
		location2 = super.getUniformLoction("ModelViewMatrix");
		location = super.getUniformLoction("glProjection");
		
		System.out.println("LOCATION "+location);
		System.out.println("LOCATION2 "+location2);
	
	}
	

	public void setCameraMatrix(Mat4 matrix)
	{
		super.LoadeMatrix4f(location, matrix.getBuffer());
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Pos");
		super.bindAttribute(1, "Normal");
		super.bindAttribute(2, "Tex");
	}

}
