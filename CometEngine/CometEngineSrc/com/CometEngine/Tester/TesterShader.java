package com.CometEngine.Tester;

import com.CometEngine.Renderer.Shader.Shader;

public class TesterShader extends Shader{

	static String vertex = 
"attribute  vec3 pos;"+
"attribute  vec3 Normal;"+
"attribute  vec2 Tex;"+

"varying vec2 cood;"+
"varying vec3 normalse;"+
"uniform mat4 CameraMatrix;"+
"uniform mat4 ModelViewMatrix;"+

"void main(void)"+
"{"+
 "  normalse = Normal;"+
 "  cood = Tex;"+
 "   gl_Position =   gl_ProjectionMatrix * CameraMatrix * ModelViewMatrix * gl_Vertex;"+
"}";
	
	
	static String frag = 
			"uniform sampler2D textureSampler;"+
			"varying vec2 cood;"+
			"void main(void)"+
			"{"    +
			   " gl_FragColor = texture2D(textureSampler, cood);"+
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
