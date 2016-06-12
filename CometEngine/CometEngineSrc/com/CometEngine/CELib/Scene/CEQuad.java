package com.CometEngine.CELib.Scene;

import com.CometEngine.CELib.Node.CEColor4f;
import com.CometEngine.CELib.Node.CENode2D;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Renderer.VBO.CEIndexBufferObject;
import com.CometEngine.Renderer.VBO.CEVertexBufferObject;
import com.CometEngine.Tester.Default2DShader;

public class CEQuad {

	protected CEVertexBufferObject vbo = null;
	protected CEIndexBufferObject ibo = null;
	protected CEVAO vao = null;
	protected Default2DShader shader = null;
	protected int Width = 0;
	protected int Hight = 0;
	protected CEColor4f  color = new CEColor4f(1, 1, 1, 1);
	
	
	// test
    
    int [] indexs = new int[]
    		{
    				0,1,2 , 2,1,3
    		};
	//test
	
	public CEColor4f getColor()
	{
		return color.Clone();
	}
	public void setColor(CEColor4f color)
	{
		this.color.Red = color.Red;
		this.color.Green= color.Green;
		this.color.Blue = color.Blue;
		this.color.Alpha = color.Alpha;
	}
	
	public void setColor(float Red, float Green, float Blue, float Alpha)
	{
		this.color.Red = Red;
		this.color.Green = Green;
		this.color.Blue = Blue;
		this.color.Alpha = Alpha;
	}
	
	public CEQuad(int width, int hight) {
		
		this.Width = width;
		this.Hight = hight;
		float dw = Width / 2;
		float dh = Hight / 2;
		float[] vertices = new float[]{
	    		-dw, dh, 
	            -dw, -dh,
	            dw,  dh,
	            dw, -dh,
	    };	
	
		float [] texs = new float[]
				{
						1,0,
						1,1,
						0,0,
						0,1
				};
		CEVboObject []obj = new CEVboObject[]{ new CEVAO.CEVboObject(0, 2, vertices), new CEVAO.CEVboObject(1, 2, texs)};

		
		vao = CEVAO.Create(indexs, obj);		 
	}

	public void DrawShape()
	{
		if(vao.isGLLoaded() == false)
			return;
		
		CEGL.BindVertexArray(vao.getID());
		CEGL.EnableVertexAttribArray(0);
		CEGL.EnableVertexAttribArray(1);
		CEGL.DrawElements(CEGL.GL_TRIANGLES, indexs.length, CEGL.GL_UNSIGNED_INT, 0);
		CEGL.DisableVertexAttribArray(0);
		CEGL.DisableVertexAttribArray(1);
		
		CEGL.BindVertexArray(0);
	}
	



}
