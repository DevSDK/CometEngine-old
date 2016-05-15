package com.CometEngine.Tester;

import com.CometEngine.Renderer.Shader.ShaderProgram;


public class TesterShader extends ShaderProgram{

	
	private static final String VertexFileName   = "com/CometEngine/Tester/VertexShader.txt";

	private static final String FragmentFileName = "com/CometEngine/Tester/FragmentShader.txt";
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
