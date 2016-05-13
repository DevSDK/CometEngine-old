package com.CometEngine.Tester;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import com.CometEngine.Buffer.Utils.CEBufferUtils;
import com.CometEngine.Renderer.CEGL;

public class Tester {
	float[] vertices = {
	        -0.5f, 0.5f, 0f,    // Left top         ID: 0
	        -0.5f, -0.5f, 0f,   // Left bottom      ID: 1
	        0.5f, -0.5f, 0f,    // Right bottom     ID: 2
	        0.5f, 0.5f, 0f  // Right left       ID: 3
	};
	byte[] indices = {
			0, 1, 2, 2, 3, 0
			};
	int vao ;
	TesterShader shader ;
	// Sending data to OpenGL requires the usage of (flipped) byte buffers
	public Tester()
	{
		shader = new TesterShader();
		/*
		ByteBuffer indicesBuffer = ByteBuffer.allocate( indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		
		IntBuffer buf = IntBuffer.allocate(4);
		
		
		CEGL.GenBuffers(buf);
		 vao = buf.get(); 
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, vao);
		CEGL.BufferData(CEGL.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, CEGL.GL_STATIC_DRAW);
		// Deselect (bind to 0) the VBO
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, 0);
		FloatBuffer verticesBuffer = FloatBuffer.allocate(vertices.length);
		verticesBuffer.put(vertices);
		verticesBuffer.flip();
		
		*/
		
	}
	public void draw()
	{
		
		/*
		//= Bind to the VAO that has all the information about the vertices
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vao);
		CEGL.EnableVertexAttribArray(0);
		 
		// Bind to the index VBO that has all the information about the order of the vertices
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, vao);
		 
		// Draw the vertices
		CEGL.glDrawElements(CEGL.GL_TRIANGLES, 6, CEGL.GL_UNSIGNED_BYTE, 0);
		 
		// Put everything back to default (deselect)
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, 0);
		CEGL.DisableVertexAttribArray(0);
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);
	*/
	}
}