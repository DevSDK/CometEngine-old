package com.CometEngine.Tester;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class Tester {
    float[] vertices = new float[]{
            -0.5f, -0.5f,0,
            -0.5f,  0.5f,0,
             0.5f, -0.5f,0,
             0.5f,  0.5f,0,
            -0.5f,  0.5f,0,
             0.5f, -0.5f,0
    };

	int buf;
	int ibuf;
	TesterShader shader ;
	// Sending data to OpenGL requires the usage of (flipped) byte buffers
	public Tester()
	{
		
		
	      FloatBuffer DataBuffer= CEBufferUtils.ArrayToBuffer(vertices);//position at 0.
          DataBuffer.put(vertices);//put all the data in the buffer, position at the end of the data
          DataBuffer.flip();//set the limit at the position=end of the data(ie no effect right now),and sets the position at 0 again 
          
          buf = CEGL.GenBuffers( );
          CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, buf);
          CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, DataBuffer, CEGL.GL_STATIC_DRAW);
          CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);
		
		shader = new TesterShader();
		
		
		
	}
	public void draw()
	{
		shader.Start();
	
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, buf);
		CEGL.EnableVertexAttribArray(0);
		CEGL.VertexAttribPointer(0, 3, CEGL.GL_FLOAT, false, 0, 0);
		CEGL.DrawArrays(CEGL.GL_TRIANGLES, 0, 6);

		CEGL.DisableVertexAttribArray(0);
		shader.Stop();
        
	}
}