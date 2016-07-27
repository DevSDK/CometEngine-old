package com.CometEngine.CELib.Sprite;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBound2D;
import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Object.CEQuad;
import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEMatrixStack;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.Default2DShader;
import com.CometEngine.Renderer.Texture.CETexture2D;
import com.CometEngine.Renderer.Texture.CETextureManager;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Renderer.VBO.CEIndexBufferObject;
import com.CometEngine.Renderer.VBO.CEVertexBufferObject;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEMatrix4f;

public class CESprited2D extends CERenderableObject implements CEBound2D {

	protected CETexture2D texture = null;
	protected Default2DShader shader = null;
	protected boolean isChangetexture = false;
	protected CEQuad quad = null;

	FloatBuffer colorbuffer = CEBufferUtils.CreateFloatBuffer(4);
	FloatBuffer ModelViewMatrixBuffer = CEBufferUtils.CreateFloatBuffer(16);

	public static CESprited2D Create(String filename) {
		CESprited2D sprite = new CESprited2D(filename);
		return sprite;
	}

	private CESprited2D(String filename) {
		texture = CETexture2D.CreateTexture2D(filename);
		shader = Default2DShader.getInstance();
	}

	boolean isInitVertex = false;

	public void setTexture(CETexture2D texture) {
		this.texture = texture;
		isInitVertex = false;
	}

	public void ChangeImage(String filepath) {
		setTexture(CETexture2D.CreateTexture2D(filepath));
	}

	private void initQuad() {
		int width = texture.getData().getWidth();
		int height = texture.getData().getHeight();
		ContentSize.x = width;
		ContentSize.y = height;
		boundbox = new CEBoundBox2D(width, height,
				(CECamera2D) CometEngine.getInstance().getSceneManager().NowRender2DCamera);

		quad = CEQuad.Create(texture, width, height);
		quad.setColor(Color);
	}

	CEMatrix4f parent = new CEMatrix4f();
	CEMatrix4f test = new CEMatrix4f();

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
		shader.setOpacity(Opacity);
		// Identity * translate * rotate * scale
		parent.resetIDENTITY();
		ModelViewMatrix.resetIDENTITY();
		CEMatrixStack.getInstanceFor2D().GetTopOfStackMatrix(parent);
		ModelViewMatrix.setMatrix(parent);
		ModelViewMatrix.translate(mPosition.x, mPosition.y, 0).rotate(angle, 0, 0, 1).scale(scale.x, scale.y, scale.z);
		shader.setModelViewMatrix(ModelViewMatrix);

		Color.getBuffer(colorbuffer);
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
		PxT.resetIDENTITY();
		PxT.setMatrix((ModelViewMatrix));
		boundbox.updateBoundingBoxTranslate(PxT);
		return boundbox;
	}

	private final CEFloat2D Protocal = new CEFloat2D();

	@Override
	public CEFloat2D getSize() {
		Protocal.x = ContentSize.x;
		Protocal.y = ContentSize.y;
		return Protocal;
	}

}
