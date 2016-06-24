package com.CometEngine.Renderer.Shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

import com.CometEngine.CometEngine;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResource;
import com.CometEngine.Renderer.CEGLResourceManager;
import com.CometEngine.Renderer.CERenderer.RENDERER_TYPE;

public abstract class ShaderProgram extends CEGLResource {
	private int programID;
	private int vertexShaderID;
	private int FragmentShaderID;
	
	private String VertexFileName;
	private String FragmentFileName;
	
	public ShaderProgram(String VertexFileName ,String FragmentFileName)
	{
		this.VertexFileName = VertexFileName;
		this.FragmentFileName = FragmentFileName;
		CEGLResourceManager.getInstence().putGLResrouce(this);
	}
	
	
	protected  abstract void LinkUnifroms();
	
	protected void LoadInteger(int location,int value)
	{
		
		CEGL.Uniform1i(location, value);
	}
	
	protected void LoadeMatrix4f(int locantion,FloatBuffer mat)
	{
		CEGL.UniformMatrix4fv(locantion, false, mat);
	}
	public int getUniformLoction(String path)
	{
		return CEGL.GetUniformLocation(programID, path);
	}
	public void LoadVector4f(int location, FloatBuffer vec)
	{
		CEGL.Uniform4fv(location, vec);
	}
	
	
	public void Start()
	{
		CEGL.UseProgram(programID);
	}
	
	public void Stop()
	{
			CEGL.UseProgram(0);	
	}

	public void cleanUP()
	{
		Stop();
		CEGL.DetachShader(programID, vertexShaderID);
		CEGL.DetachShader(programID, FragmentShaderID);
		CEGL.DeleteShader(vertexShaderID);
		CEGL.DeleteShader(FragmentShaderID);
		CEGL.DeleteProgram(programID);
	}
	
	protected void bindAttribute(int attribute , String code)
	{
		CEGL.BindAttribLocation(programID, attribute, code);	
	}
	
	protected abstract void bindAttributes();
	
	
	private static int loadeShasder(String FileName,int type)
	{

		String source = "";
	
		if( CometEngine.getInstance().getRenderer().getType() == RENDERER_TYPE.CE_RENDERER_GLES)
		{
			source = "#version 300 es\n";
			
			if(type == CEGL.GL_FRAGMENT_SHADER )
				source +=  "precision mediump float;";
		} 	
			ByteBuffer buffer =CEFileUtil.getInstence().ReadResurceDirectoryToSync(FileName);
			String fof = new String(buffer.array(), Charset.forName("UTF-8"));
			source += fof;
			
			int shaderID = CEGL.CreateShader(type);
		
		
		CEGL.ShaderSource(shaderID, source);
		CEGL.CompileShader(shaderID);
		if(	CEGL.GetShaderi(shaderID , CEGL.GL_COMPILE_STATUS) ==CEGL.GL_FALSE)
		{
			System.out.println(CEGL.GetShaderInfoLog(shaderID,500));
			
			System.out.println("Shader Compile Error");
			System.exit(-1);			
		}
		

		return shaderID;
		
		
	}

	@Override
	protected void onGLLoad()
	{

		vertexShaderID = loadeShasder(VertexFileName, CEGL.GL_VERTEX_SHADER);
		FragmentShaderID = loadeShasder(FragmentFileName, CEGL.GL_FRAGMENT_SHADER);
		programID = CEGL.CreateProgram();
		
		//TODO: Remove The Debug
		CEGL.AttachShader(programID, vertexShaderID);
		CEGL.AttachShader(programID, FragmentShaderID);
		bindAttributes();
		CEGL.LinkProgram(programID);
		CEGL.ValidateProgram(programID);
		LinkUnifroms();

	}
	@Override
	protected void onGLDelete()
	{
		
	}
	@Override
	public boolean isloaded()
	{
		return true;
	}
	
	
}
