package com.CometEngine.CELib.Text;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import com.CometEngine.CometEngine;
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

		protected CEBMPTextLabel(CEFont font, float scale, boolean centered, String []strings) {
		super(strings, scale, centered, font);
		texture = ( (CEBMPFont)font).getTexture();
		
		CEVAO.CEVboObject []InitVao = new CEVAO.CEVboObject[] {new CEVAO.CEVboObject(0, 2, Font.getVertexList()) , 
				new CEVAO.CEVboObject(1, 2, Font.getTextureCoordList())}; 
			int [] array = genIndeces(Font.getCharacterSize());
			
		vao = CEVAO.Create(array, InitVao ,CEGL.GL_STATIC_DRAW);
		
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
	

		private int[] genIndeces(int size)
		{
			int [] array= new int[size*6];
			for(int i = 0  ; i  < size ; i++)
			{
			
				array[i * 6] =  i * 4; 
				array[i * 6 + 1] =  i * 4 + 1;
				array[i * 6 + 2] =  i * 4 + 2;
				
				array[i * 6 + 3] =  i * 4 + 2;
				array[i * 6 + 4] =  i * 4 + 1;
				array[i * 6 + 5] =  i * 4 + 3;
				
			}
		
			return array;
		}
		
	private void drawtLine(char [] line)
	{		
		if(line == null)
			return ;
			
			if(Font.isLoaded() == false)
				return ;
			if(vao.isGLLoaded() == false)
				return ;
			
			
			CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
			CEGL.BindTexture(CEGL.GL_TEXTURE_2D, texture.getTextureID());
			
			CEGL.BindVertexArray(vao.getID());
			
			for(int i = 0; i < line.length; i++)
			{
				char c = line[i];
				character c_info = Font.getChar(c);
				if(c_info == null)
					continue;
				if( c_info.getId() == 32 )
				{
					charTrlateMatrix.translate(c_info.getxAdvance(),0, 0);
					shader.setCharMatrix(charTrlateMatrix);
					
					continue;
				}
				
				
				loadYoffset.setMatrix(charTrlateMatrix);
				charTrlateMatrix.translate((c_info.getxOffset() ) * scale.x ,( - c_info.getyOffset()), 0);
				
				shader.setCharMatrix(charTrlateMatrix);
	
				CEGL.EnableVertexAttribArray(0);
				CEGL.EnableVertexAttribArray(1);	
				
				CEGL.DrawElements(CEGL.GL_TRIANGLES, 6, CEGL.GL_UNSIGNED_INT, c_info.getDrawOffset()* 6 * 4);
				
				charTrlateMatrix.setMatrix(loadYoffset);
				charTrlateMatrix.translate( ( c_info.getxAdvance()) , 0, 0);
				CEGL.DisableVertexAttribArray(0);
				CEGL.DisableVertexAttribArray(1);
				
			}
			CEGL.BindVertexArray(0);
	
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
	
	private int getLineWidth(char [] line)
	{

		int width = 0;
		if(line == null)
			return 0 ;
		
		for(int i = 0; i< line.length; i++)
		{
			char c = line[i];
			character c_info = Font.getChar(c);
			if(c_info == null)
				continue;
			if( c_info.getId() == 32 )
			{
				width += c_info.getxAdvance();
				
				continue;
			}
			
			width += c_info.getxAdvance() + c_info.getxOffset();
		}
		
		return width;
		
	}
	private CEMatrix4f translatematrix = new CEMatrix4f();
	
	private void ResetBox()
	{
		LabelHeight = 0;
	}
	
	@Override
	public void onDraw() {
	
		
		shader.Start();
		shader.setProjectionMatrix(CometEngine.getInstance().getSceneManager().nowRender2DCamera.getPorjection());
		
		color.getBuffer(colorbuffer);
		shader.setColor4f(colorbuffer);
		translatematrix.resetIDENTITY();

		translatematrix.translate(-(LabelWidth * scale.x)* control_point.x, (LabelHeight * scale.y)  * (1- control_point.y ) , 0);
		
		translatematrix.translate(mPosition.x, mPosition.y, 0).rotate(angle, 0, 0, 1);
		translatematrix.scale(scale.x,  scale.x, 0);
		shader.setModelViewMatrix(translatematrix);
		
		PrepareTextRendering();
		
		ResetBox();
		for(int i = 0 ; i < TextCharData.size(); i ++ )
		{
			char [] line  = null;
				try{line = TextCharData.get(i);}catch(IndexOutOfBoundsException e)
				{
					return;
				}
			
			PrepareLineRendering();
			

			int linewidth = getLineWidth(line);
		
			if(isCentered)
				charTrlateMatrix.translate((LabelWidth/2 - linewidth/2 ),0, 0);
			
			LabelHeight +=  Font.getLineHeight() * scale.y;
			
			shader.setLineMatrix(lineTranslateMatrix);
			drawtLine(line);
			lineTranslateMatrix.translate(  0 ,  -Font.getLineHeight(), 0);

			if(LabelWidth < linewidth)
			 {
				 LabelWidth = linewidth;
			 }
		}
		
		
		shader.Stop();
	}
}
