package com.CometEngine.CELib.Scene;

import java.nio.FloatBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Node.CERenderableNode;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAOManager;
import com.CometEngine.Renderer.VBO.CEIndexBufferObject;
import com.CometEngine.Renderer.VBO.CEVertexBufferObject;
import com.CometEngine.Tester.TesterShader;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CETestSprite extends CERenderableNode{

	protected CETexture2D texture = null;
	protected float width = 0 ;
	protected float height = 0;
	protected CEVertexBufferObject vbo = null;
	protected CEIndexBufferObject ibo = null;
	protected TesterShader shader = null;
	
	// test
    
    int [] indexs = new int[]
    		{
    				0,1,2 , 2,1,3
    		};
	//test
	
	
	public CETestSprite(String filename) {
		 super(NODE_TYPE.CE_SPRITE);
		 texture  = CETexture2D.CreateTexture2D(filename);
		 
		 shader = new TesterShader();

		 ibo = CEIndexBufferObject.Create(CEBufferUtils.ArrayToBuffer(indexs));
	}

	
	@Override
	public CERenderCommand genRenderCommand() {
		CERenderCommandCustom command = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
			@Override
			public void invoke() {
				Drawing();
			}
		});
		return command;
	}

	boolean isInitVertex = false;
	private void initVertex()
	{

		CETexture2D tex = CETextureManager.getInstence().getTexture2D("1.png") ;
		int width = tex.getData().getWidth();
		int height = tex.getData().getHeight();
		
		
		float[] vertices = new float[]{
	    		-0.5f,  0.5f, 0,
	            -0.5f, -0.5f, 0,
	             0.5f,  0.5f, 0,
	             0.5f, -0.5f, 0
	    };	
		FloatBuffer buffer = CEBufferUtils.ArrayToBuffer(vertices);
		 vbo = CEVertexBufferObject.Create(buffer);
		 
	}
	
	@Override
	public void onDraw() {
		
		CEGL.Enable(CEGL.GL_TEXTURE_2D);
		
		CETexture2D tex = CETextureManager.getInstence().getTexture2D("1.png") ;
		
		if( tex == null)
		{
			return;
		}
	
		
		if(isInitVertex==false)
		{
			isInitVertex = true;
			initVertex();
		}
		System.out.println("RENDERING DRAW ELEMENT ID IS " + vbo.getID());
		
		if(vbo.getID() ==0 || ibo.getID() ==0 )
			return;
		
		
		shader.Start();
		CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, tex.getTextureID());
	
		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vbo.getID());
		CEGL.EnableVertexAttribArray(0);
		CEGL.VertexAttribPointer(0, 3, CEGL.GL_FLOAT, false, 0, 0);
	
		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, ibo.getID());
		
		CEGL.DrawElements(CEGL.GL_TRIANGLES, 12, CEGL.GL_UNSIGNED_INT, 0);
		
		CEGL.DisableVertexAttribArray(0);

		CEGL.BindBuffer(CEGL.GL_ELEMENT_ARRAY_BUFFER, 0);

		

		CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, 0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, 0);
		shader.Stop();
	}


	@Override
	public void onInit() {
		// TODO Auto-generated method stub
		
	}




}
