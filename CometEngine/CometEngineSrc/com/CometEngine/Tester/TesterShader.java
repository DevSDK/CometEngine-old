package com.CometEngine.Tester;

import java.io.File;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.Shader.ShaderProgram;


public class TesterShader extends ShaderProgram{

	
	private static final String VertexFileName   = "com/CometEngine/Tester/TestVertex.txt";

	private static final String FragmentFileName =  "com/CometEngine/Tester/TesterFragment.txt";
	public TesterShader() {
		super(VertexFileName, FragmentFileName);
		
	}
	
			
	@Override
	protected void LinkUnifroms() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Pos");
		super.bindAttribute(1, "Normal");
		super.bindAttribute(2, "Tex");
	}

}
