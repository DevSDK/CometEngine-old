package com.CometEngine.CELib.Text;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import com.CometEngine.CELib.Node.CEColor4f;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Font.CEFont;
import com.CometEngine.Font.BMPFont.character;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Texture.Textures.CETexture;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Tester.Default2DShader;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;
import com.CometEngine.Util.Meth.CESize;

public class CEBMPTextLabel extends CETextLabel{

		private CETexture2D texture  = null;
		private FontShader2D shader = null;
		
	
		
		private CEVAO vao = null;
		private CEColor4f color = new CEColor4f(1, 1, 1, 1);
		private FloatBuffer colorbuffer = CEBufferUtils.CreateFloatBuffer(4);
		private CEMatrix4f lineTranslateMatrix = new CEMatrix4f();
		private CEMatrix4f charTrlateMatrix = new CEMatrix4f();
		private CEMatrix4f loadYoffset = new CEMatrix4f();

		protected CEBMPTextLabel(String strings, float scale, boolean centered, CEFont font) {
		super(strings, scale, centered, font);
		texture = ( (CEBMPFont)font).getTexture();
		
		CEVAO.CEVboObject []InitVao = new CEVAO.CEVboObject[] {new CEVAO.CEVboObject(0, 2, 
				new float[] {0, 512,
							0 ,0,
							712, 512,
							712, 0}) , 
				new CEVAO.CEVboObject(1, 2, new float[] {0,0 , 0 , 1 , 1, 0 , 1 ,1})}; 
		
		vao = CEVAO.Create(new int []{ 0 , 1, 2  , 2, 1, 3 }, InitVao ,CEGL.GL_DYNAMIC_DRAW);
		shader = new FontShader2D();
		}
		private void PrepareLineRendering()
		{
			charTrlateMatrix.resetIDENTITY();
			shader.setCharMatrix(charTrlateMatrix);
		}
		private void PrepareTextRendering()
		{
			lineTranslateMatrix.resetIDENTITY();
		}
	
	private int drawtLine(char [] line)
	{		
			if(TextCharData == null)
				return 0;
			if(Font.isLoaded() == false)
				return 0;
			if(vao.isGLLoaded() == false)
				return 0;
			
			int width = 0;
			
			CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
			CEGL.BindTexture(CEGL.GL_TEXTURE_2D, texture.getTextureID());
			
			CEGL.BindVertexArray(vao.getID());
			
			for(int i = 0; i< line.length; i++)
			{
				char c = line[i];
				character c_info = Font.getChar(c);
				if(c_info == null)
					continue;
				if( c_info.getId() == 32 )
				{
					charTrlateMatrix.translate(c_info.getxAdvance() * scale.x ,0, 0);
					width += c_info.getxAdvance()* scale.x;
					shader.setCharMatrix(charTrlateMatrix);
				
					continue;
				}
				
				
				loadYoffset.setMatrix(charTrlateMatrix);
				charTrlateMatrix.translate((c_info.getxOffset() )*scale.x ,( - c_info.getyOffset())*scale.y , 0);
				
				shader.setCharMatrix(charTrlateMatrix);
				CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vao.getVBOID(0));
				
				CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, Font.getVertex(c), CEGL.GL_DYNAMIC_DRAW);
		
				CEGL.BindBuffer(CEGL.GL_ARRAY_BUFFER, vao.getVBOID(1));
				CEGL.BufferData(CEGL.GL_ARRAY_BUFFER, Font.getTexCoord(c), CEGL.GL_DYNAMIC_DRAW);
			
				CEGL.EnableVertexAttribArray(0);
				CEGL.EnableVertexAttribArray(1);
				
				CEGL.DrawElements(CEGL.GL_TRIANGLES, 6, CEGL.GL_UNSIGNED_INT, 0);
				
				charTrlateMatrix.setMatrix(loadYoffset);
				charTrlateMatrix.translate( ( c_info.getxAdvance()) , 0, 0);
				CEGL.DisableVertexAttribArray(0);
				CEGL.DisableVertexAttribArray(1);
				
				width += c_info.getxAdvance() * scale.x;
			}
			CEGL.BindVertexArray(0);
			
			return width;
			
	}
	
	private CERenderCommand command = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
		
		@Override
		public void invoke() {
			Drawing();
		}
	});
	@Override
	public CERenderCommand genRenderCommand() {
		return command;
	}
	
			
	@Override
	public void onInit() {
		
	}
	

	private CEMatrix4f translatematrix = new CEMatrix4f();
	
	private void ResetBox()
	{
		LabelHeight = 0;
		LabelWidth = 0;
	}
	
	@Override
	public void onDraw() {
	
		shader.Start();
		shader.setProjectionMatrix(CESceneManager.getInstence().nowRender2DCamera.getPorjection());
		
		color.getBuffer(colorbuffer);
		shader.setColor4f(colorbuffer);
		translatematrix.resetIDENTITY();

		translatematrix.translate(-(LabelWidth  )* control_point.x, (LabelHeight ) * control_point.y , 0);
		translatematrix.translate(mPosition.x, mPosition.y, 0);
		translatematrix.scale(scale.x,  scale.x, 0);
		shader.setModelViewMatrix(translatematrix);
		
		PrepareTextRendering();
		
		ResetBox();
		for(int i = 0 ; i < TextCharData.size(); i ++ )
		{
			char [] line = TextCharData.get(i);

			PrepareLineRendering();
			lineTranslateMatrix.translate(0,  -Font.getLineHeight(), 0);
			shader.setLineMatrix(lineTranslateMatrix);
			
			LabelHeight += (i + 1 ) * Font.getLineHeight();
			int linewidth = drawtLine(line);
			 if(LabelWidth < linewidth)
			 {
				 LabelWidth = linewidth;
			 }
			 
			
		}
		
		
		
		shader.Stop();
	}
}
