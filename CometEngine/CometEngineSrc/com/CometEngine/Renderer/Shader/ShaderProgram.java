package com.CometEngine.Renderer.Shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CERenderer.RENDERER_TYPE;

public abstract class ShaderProgram {
	private int programID;
	private int vertexShaderID;
	private int FragmentShaderID;
	
	
	public ShaderProgram(String VertexFileName ,String FragmentFileName)
	{
		
		
		vertexShaderID = loadeShasder(VertexFileName, CEGL.GL_VERTEX_SHADER);
		FragmentShaderID = loadeShasder(FragmentFileName, CEGL.GL_FRAGMENT_SHADER);
		programID = CEGL.CreateProgram();
		System.out.println("ProgramID " + programID );
		CEGL.AttachShader(programID, vertexShaderID);
		CEGL.AttachShader(programID, FragmentShaderID);
		bindAttributes();
		CEGL.LinkProgram(programID);
		CEGL.ValidateProgram(programID);
		LinkUnifroms();
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
	
		if(type == CEGL.GL_FRAGMENT_SHADER && CometEngine.getInstece().getRenderer().getType() == RENDERER_TYPE.CE_RENDERER_GLES)
		{
			source =  "precision mediump float;";
		} 	
			InputStream is =CEFileUtil.getInstence().getFileInstence().getResourceFile(FileName);
		  try {
		      BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		      String s;

		      while ((s = in.readLine()) != null) {
		    	  source += s;
		      }
		      in.close();
		       } catch (IOException e) {
		        System.err.println(e); 
		       
		    }
		  
	
		
		int shaderID = CEGL.CreateShader(type);
		
		
		CEGL.ShaderSource(shaderID, source);
		CEGL.CompileShader(shaderID);
		System.out.println(CEGL.GetShaderInfoLog(CEGL.GetShaderi(shaderID , CEGL.GL_COMPILE_STATUS ),500));
		
		if(CEGL.GetShaderi(shaderID , CEGL.GL_COMPILE_STATUS )==CEGL.GL_FALSE)
		{
			System.out.println(CEGL.GetShaderInfoLog(shaderID,500));
			
			System.out.println("Shader Compile Error");
			System.exit(-1);			
		}
		

		return shaderID;
		
		
	}
	
	
}
