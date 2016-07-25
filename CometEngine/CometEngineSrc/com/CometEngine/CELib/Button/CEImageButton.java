package com.CometEngine.CELib.Button;

import java.nio.FloatBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Object.CEQuad;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.Default2DShader;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEFloat2D;
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
		shader = Default2DShader.getInstance();
		this.CallBack = ceButtonCallBack;

	}

	@Override
	public void setParent(CEObject p) {
		super.setParent(p);
	}

	protected CEPickHandler handler = null;

	boolean isInitVertex = false;

	private void initQuad() {
		int width = texture.getData().getWidth();
		int height = texture.getData().getHeight();
		boundbox = new CEBoundBox2D(width, height,
				(CECamera2D) CometEngine.getInstance().getSceneManager().getCurrentScene().get2DCamera());
		quad = CEQuad.Create(texture, width, height);
		quad.setColor(color);

		ContentSize.x = width;
		ContentSize.y = height;

		CEObject scene = getRoot();
		if (scene instanceof CEScene) {
			if (handler == null)
				handler = new CEPickHandler((CEScene) scene, this);
		}

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

		shader.setProjectionMatrix(mCamera.getPorjection());
		shader.CameraMovementMatrix(mCamera.getMovementMatrix());
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

		boundbox.updateBoundingBoxTranslate(modelviewmatrix);
		return boundbox;
	}

	@Override
	public void UnPick() {

		handler.unPick();
	}

	private final CEFloat2D ContentSizeProtocal = new CEFloat2D();

	@Override
	public CEFloat2D getSize() {
		ContentSizeProtocal.x = ContentSizeProtocal.x;
		ContentSizeProtocal.y = ContentSizeProtocal.y;
		return ContentSizeProtocal;
	}

}
