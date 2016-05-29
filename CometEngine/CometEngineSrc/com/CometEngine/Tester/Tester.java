package com.CometEngine.Tester;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class Tester {
    float[] vertices = new float[]{
            -0.5f, -0.5f, 0,
            -0.5f,  0.5f, 0,
             0.5f,  0.5f, 0,
            -0.5f,  0.5f, 0
    };	
    float[] normals = new float[]{
    		0, 0,
            0, 1, 
            1, 0,
            1, 1
    };	
    int [] indexs = new int[]
    		{
    				0,1,2,1,3,4
    		};
    int buf;
	int ibuf = 0;
    int normalID = 0;
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
		
		//TODO: Tester
		CEGL.Enable(CEGL.GL_TEXTURE_2D);
		CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
		
		CETexture2D tex = CETextureManager.getInstence().getTexture2D("1.png") ;
		//System.out.println("is not Blcoking !! ! ! ! ! ! ! ! ! ! ! ");
		if( tex ==null)
		{
			return;
		}
		

		
		shader.Start();
		CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, tex.getTextureID());
		
		System.out.println("Now RENDERING TEXTURE IS : "+ tex.getTextureID());
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, buf);
		CEGL.EnableVertexAttribArray(0);
		
		CEGL.VertexAttribPointer(0, 3, CEGL.GL_FLOAT, false, 0, 0);
		CEGL.DrawArrays(CEGL.GL_TRIANGLES, 0, 6);

		CEGL.DisableVertexAttribArray(0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, 0);
		shader.Stop();
        
	}
	
}