package com.CometEngine.Tester;

import com.CometEngine.Renderer.Shader.ShaderProgram;

public class TesterShader extends ShaderProgram{

	static String vertex = 
"attribute  vec3 Pos;"+
"attribute  vec3 Normal;"+
"attribute  vec2 Tex;"+

"varying vec2 cood;"+
"varying vec3 normalse;"+
"uniform mat4 CameraMatrix;"+
"uniform mat4 ModelViewMatrix;"+

"void main(void)"+
"{"+
 "  normalse = Normal;"+
 "  cood = Tex;"
 + "vec4 Vertex = vec4(Pos.x,Pos.y,Pos.z,1);"+
 "   gl_Position = Vertex  ;"+
"}";
	
	
	static String frag = 
			"precision mediump float;"+
			"uniform sampler2D textureSampler;"+
			"varying vec2 cood;"+
			"void main(void)"+
			"{"    +
			   " gl_FragColor = vec4(1,0,0,1);"+
			"}";
			
	private static final String VertexFileName  =  "A";
	private static final String FragmentFileName = "B";
	public TesterShader() {
		super(vertex, frag);
		
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
