package com.CometEngine.CELib.Scene;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Node.CE2DNode;
import com.CometEngine.CELib.Node.CERenderableNode;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAOLoader;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Renderer.VBO.CEIndexBufferObject;
import com.CometEngine.Renderer.VBO.CEVertexBufferObject;
import com.CometEngine.Tester.TesterShader;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CETestSprite extends CE2DNode{

	protected CETexture2D texture = null;
	protected float width = 0 ;
	protected float height = 0;
	protected CEVertexBufferObject vbo = null;
	protected CEIndexBufferObject ibo = null;
	protected CEVAO vao = null;
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

		int width = texture.getData().getWidth();
		int height = texture.getData().getHeight();

		
		float[] vertices = new float[]{
	    		0, height, 0,
	            0, 0, 0,
	            width,  height, 0,
	            width, 0, 0
	    };	
		float[] normals = new float[]
				{
						0,0,0,
						0,0,0,
						0,0,0,
						0,0,0
				};
		float [] texs = new float[]
				{
						1,0,
						1,1,
						0,0,
						0,1
				};
		CEVboObject []obj = new CEVboObject[]{ new CEVAO.CEVboObject(0, 3, vertices),new CEVAO.CEVboObject(1, 3, normals), new CEVAO.CEVboObject(2, 2, texs)};

		
		vao = CEVAO.Create(indexs, obj);		 
		

			}
	
	@Override
	public void onDraw() {
		
		CEGL.Enable(CEGL.GL_TEXTURE_2D);
		
		if(  texture.isloaded() == false )
		{
			return;
		}
	
		
		if(isInitVertex==false)
		{
			initVertex();
			isInitVertex = true;
		}
			
		if(vao.getID() <= 0)
			return;
		
		
		
		shader.Start();
		
		shader.setCameraMatrix(CESceneManager.getInstence().nowRenderCamera.getPorjection());
		
		CEGL.BindVertexArray(vao.getID());
		CEGL.EnableVertexAttribArray(0);
		CEGL.EnableVertexAttribArray(1);
		CEGL.EnableVertexAttribArray(2);
		
		CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, texture.getTextureID());
		CEGL.DrawElements(CEGL.GL_TRIANGLES, indexs.length, CEGL.GL_UNSIGNED_INT, 0);
		
		CEGL.DisableVertexAttribArray(2);
		CEGL.DisableVertexAttribArray(1);
		CEGL.DisableVertexAttribArray(0);
		
		CEGL.BindVertexArray(0);
		
		shader.Stop();
	
	}


	@Override
	public void onInit() {
		
	}




}
