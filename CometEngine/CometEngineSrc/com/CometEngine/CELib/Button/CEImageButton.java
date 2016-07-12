package com.CometEngine.CELib.Button;

import java.nio.FloatBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEQuad;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.Default2DShader;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CEImageButton extends CEButton {

	protected CETexture2D texture = null;
	protected Default2DShader shader = null;
	protected CEQuad quad = null;
	protected CEColor4f color = new CEColor4f(1, 1, 1, 1);
	protected CEEventDispatcher eventdispatcher;
	FloatBuffer colorbuffer = CEBufferUtils.CreateFloatBuffer(4);
	FloatBuffer ModelViewMatrixBuffer = CEBufferUtils.CreateFloatBuffer(16);

	public CEImageButton(String filename, CEButton.CEButtonCallBack ceButtonCallBack) {
		texture = CETexture2D.CreateTexture2D(filename);
		shader = new Default2DShader();
		this.CallBack = ceButtonCallBack;

	}


	@Override
	public void setParent(CEObject p) {
		super.setParent(p);
		CEObject scene = getRoot();
		if (scene instanceof CEScene)
			handler = new CEPickHandler((CEScene) scene, this);
	}

	CEPickHandler handler;
	boolean isInitVertex = false;

	private void initQuad() {
		int width = texture.getData().getWidth();
		int height = texture.getData().getHeight();
		boundbox = new CEBoundBox2D(width, height,
				(CECamera2D) CometEngine.getInstance().getSceneManager().nowRender2DCamera);
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
		if (handler.isPicked()) {
			modelviewmatrix.resetIDENTITY();
			modelviewmatrix.translate(mPosition.x, mPosition.y, 0).rotate(angle, 0, 0, 1).scale(scale.x * 1.2f,
					scale.y * 1.2f, scale.z);
		} else {
			modelviewmatrix.resetIDENTITY();
			modelviewmatrix.translate(mPosition.x, mPosition.y, 0).rotate(angle, 0, 0, 1).scale(scale.x, scale.y,
					scale.z);
		}
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

	private CEBoundBox2D boundbox = new CEBoundBox2D(0, 0, null);

	private CEMatrix4f PxT = new CEMatrix4f();

	@Override
	public CEBoundBox2D getBoundingBox() {

		boundbox.updateBoundingBox(modelviewmatrix);
		return boundbox;
	}

	@Override
	public void UnPick() {

		handler.unPick();
	}

}
