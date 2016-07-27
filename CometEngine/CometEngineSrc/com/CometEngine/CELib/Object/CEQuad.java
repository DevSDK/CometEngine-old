package com.CometEngine.CELib.Object;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.Default2DShader;
import com.CometEngine.Renderer.Texture.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Renderer.VBO.CEIndexBufferObject;
import com.CometEngine.Renderer.VBO.CEVertexBufferObject;

public class CEQuad {

	protected CEVAO vao = null;
	protected int Width = 0;
	protected int Hight = 0;
	protected CEColor4f color = new CEColor4f(1, 1, 1, 1);

	int[] indexs = new int[] { 0, 1, 2, 2, 1, 3 };

	public CEColor4f getColor() {
		return color.Clone();
	}

	public void setColor(CEColor4f color) {
		this.color.Red = color.Red;
		this.color.Green = color.Green;
		this.color.Blue = color.Blue;
		this.color.Alpha = color.Alpha;
	}

	public void setColor(float Red, float Green, float Blue, float Alpha) {
		this.color.Red = Red;
		this.color.Green = Green;
		this.color.Blue = Blue;
		this.color.Alpha = Alpha;
	}

	private CEQuad() {
	}

	public static CEQuad Create(int width, int height) {
		return Create(null, width, height);
	}

	public static CEQuad Create(Object VAOKey, int width, int height) {
		CEQuad quad = new CEQuad();
		quad.Width = width;
		quad.Hight = height;
		float dw = quad.Width / 2;
		float dh = quad.Hight / 2;
		float[] vertices = new float[] { -dw, dh, -dw, -dh, dw, dh, dw, -dh, };

		float[] texs = new float[] { 0, 0, 0, 1, 1, 0, 1, 1 };
		CEVboObject[] obj = new CEVboObject[] { new CEVAO.CEVboObject(0, 2, vertices),
				new CEVAO.CEVboObject(1, 2, texs) };

		quad.vao = CEVAO.Create(VAOKey, quad.indexs, obj);
		return quad;
	}

	public void DrawShape() {
		if (vao.isGLLoaded() == false)
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
