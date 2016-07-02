package com.CometEngine.CELib.Text;

import java.nio.FloatBuffer;

import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;
public class FontShader2D extends ShaderProgram{


	private static final String VertexFileName   = "com/CometEngine/Font/Vertex.txt";

	private static final String FragmentFileName =  "com/CometEngine/Font/Fragment.txt";
	public FontShader2D() {
		super(VertexFileName, FragmentFileName);
		
	}
	FloatBuffer buffer = CEBufferUtils.CreateFloatBuffer(16);
	int location = 0;
	int location2 =0;
	int gColor = 0;
	int charmatrix = 0;
	int linematrix = 0;
	@Override
	protected void LinkUnifroms() {
		location2 = super.getUniformLoction("ModelViewMatrix");
		location = super.getUniformLoction("glProjection");
		gColor = super.getUniformLoction("gColor");;
		 charmatrix = super.getUniformLoction("charMatrix");
		 linematrix = super.getUniformLoction("lineMatrix");
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
	public void setLineMatrix(CEMatrix4f matrix)
	{
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(linematrix, buffer);
	}
	
	public void setCharMatrix(CEMatrix4f matrix)
	{
		matrix.getBuffer(buffer);
		super.LoadeMatrix4f(charmatrix, buffer);
	}
	
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "Pos");
		super.bindAttribute(1, "Tex");
	}

}
