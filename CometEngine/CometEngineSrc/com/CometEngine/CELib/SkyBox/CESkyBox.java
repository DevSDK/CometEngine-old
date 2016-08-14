package com.CometEngine.CELib.SkyBox;

import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera3D;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEMatrixStack;
import com.CometEngine.Renderer.Texture.CETextureCubeMap;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Util.Meth.CEAngle;
import com.CometEngine.Util.Meth.CEMatrix4f;
public class CESkyBox {

	private CESkyBoxShader shader = new CESkyBoxShader();
	private CECamera3D currentCamera = CESceneManager.getInstance().NowRender3DCamera;
	private int SKYBOXSIZE = 100;
	private CETextureCubeMap TextureMap;
	private CEVAO VAO;
	private float[] VERTICES;
	private CEMatrix4f RotationMatrix = new CEMatrix4f();
	CEMatrix4f MatrixForRotating = new CEMatrix4f();

	private static final int[] indexs = { 0, 1, 2, 2, 3, 0,
			// top
			1, 5, 6, 6, 2, 1,
			// back
			7, 6, 5, 5, 4, 7,
			// bottom
			4, 0, 3, 3, 7, 4,
			// left
			4, 5, 1, 1, 0, 4,
			// right
			3, 2, 6, 6, 7, 3 };

	public static CESkyBox Create(int SkyBoxSize, String[] cube) {
		CESkyBox skybox = CESkyBox.Create(SkyBoxSize, cube[0], cube[1], cube[2], cube[3], cube[4], cube[5]);
		return skybox;
	}

	public static CESkyBox Create(int SkyBoxSize, String top, String bottom, String front, String back, String right,
			String left) {
		CESkyBox skybox = new CESkyBox(SkyBoxSize, top, bottom, front, back, right, left);
		return skybox;
	}


	private void setVertexs() {
		this.VERTICES = new float[] {

				-SKYBOXSIZE, -SKYBOXSIZE, SKYBOXSIZE, SKYBOXSIZE, -SKYBOXSIZE, SKYBOXSIZE, SKYBOXSIZE, SKYBOXSIZE,
				SKYBOXSIZE, -SKYBOXSIZE, SKYBOXSIZE, SKYBOXSIZE,

				-SKYBOXSIZE, -SKYBOXSIZE, -SKYBOXSIZE, SKYBOXSIZE, -SKYBOXSIZE, -SKYBOXSIZE, SKYBOXSIZE, SKYBOXSIZE,
				-SKYBOXSIZE, -SKYBOXSIZE, SKYBOXSIZE, -SKYBOXSIZE

		};
	} 

	private CESkyBox(int SkyBoxSize, String top, String bottom, String front, String back, String right, String left) {
		TextureMap = CETextureCubeMap.Create(top, bottom, front, back, right, left);
		this.SKYBOXSIZE = SkyBoxSize;
		setVertexs();
		CEVboObject[] initarray = new CEVboObject[] { new CEVboObject(0, 3, this.VERTICES) };
		VAO = CEVAO.Create(null, indexs, initarray);
	}

	public void Render() {
		if (TextureMap.isloaded() == false) {
			return;
		}

		if (VAO.isGLLoaded() == false)
			return;

		CEGL.ActiveTexture(CEGL.GL_TEXTURE0);
		CEGL.BindTexture(CEGL.GL_TEXTURE_CUBE_MAP, TextureMap.getTextureID());
		shader.Start();
		shader.setProjectionMatrix(currentCamera.getPorjection());
		CEAngle angle = currentCamera.getAngle();
		shader.setRotations((float)Math.toRadians(angle.x), (float)Math.toRadians(angle.y), (float)Math.toRadians(angle.z));

		CEGL.BindVertexArray(VAO.getID());

		CEGL.EnableVertexAttribArray(0);
		CEGL.DrawElements(CEGL.GL_TRIANGLES, indexs.length, CEGL.GL_UNSIGNED_INT, 0);
		CEGL.DisableVertexAttribArray(0);
		CEGL.BindVertexArray(0);

		shader.Stop();
		CEGL.BindTexture(CEGL.GL_TEXTURE_CUBE_MAP, 0);
	}
}
