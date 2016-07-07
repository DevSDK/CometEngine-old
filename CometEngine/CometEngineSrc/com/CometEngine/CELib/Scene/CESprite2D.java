package com.CometEngine.CELib.Scene;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.Default2DShader;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Renderer.VBO.CEIndexBufferObject;
import com.CometEngine.Renderer.VBO.CEVertexBufferObject;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CESprite2D extends CERenderableObject {

	protected CETexture2D texture = null;
	protected Default2DShader shader = null;
	protected CEQuad quad = null;
	protected CEColor4f color = new CEColor4f(1, 1, 1, 1);

	FloatBuffer colorbuffer = CEBufferUtils.CreateFloatBuffer(4);
	FloatBuffer ModelViewMatrixBuffer = CEBufferUtils.CreateFloatBuffer(16);

	public CESprite2D(String filename) {
		texture = CETexture2D.CreateTexture2D(filename);
		shader = new Default2DShader();

	}

	CERenderCommandCustom command = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
		@Override
		public void invoke() {
			Drawing();
		}
	});;

	@Override
	public CERenderCommand genRenderCommand() {
		return command;
	}

	boolean isInitVertex = false;

	private void initQuad() {
		int width = texture.getData().getWidth();
		int height = texture.getData().getHeight();
		quad = CEQuad.Create(texture, width, height);
		quad.setColor(color);
	}

	private CEMatrix4f modelviewmatrix = new CEMatrix4f();

	@Override
	public void onDraw() {
		
		if (texture.isloaded() == false) {
			return;
		}

		if (isInitVertex == false) {
			initQuad();
			isInitVertex = true;
		}

		shader.Start();

		shader.setProjectionMatrix(CometEngine.getInstance().getSceneManager().nowRender2DCamera.getPorjection());

		// Identity * translate * rotate * scale
		///
		modelviewmatrix.resetIDENTITY();
		modelviewmatrix.translate(mPosition.x, mPosition.y, 0).rotate(angle, 0, 0, 1).scale(scale.x, scale.y, scale.z);

		shader.setModelViewMatrix(modelviewmatrix);

		color.getBuffer(colorbuffer);
		shader.setColor4f(colorbuffer);

		CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, texture.getTextureID());

		quad.DrawShape();

		CEGL.BindTexture(CEGL.GL_TEXTURE_2D, 0);
		CEGL.BindVertexArray(0);

		shader.Stop();

	}

	@Override
	public void onInit() {

	}

	@Override
	public void CleanUp() {
		
	}

}
