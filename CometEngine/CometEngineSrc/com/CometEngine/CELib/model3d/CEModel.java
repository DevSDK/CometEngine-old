package com.CometEngine.CELib.model3d;

import java.util.ArrayList;
import java.util.HashMap;

import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Model.obj.builder.Face;
import com.CometEngine.Model.obj.builder.FaceVertex;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEMatrixStack;
import com.CometEngine.Renderer.Texture.CETexture2D;
import com.CometEngine.Renderer.Texture.CETextureManager;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Resrouce.CEModelLoader;
import com.CometEngine.Resrouce.CEModelResource;
import com.jumi.scene.objects.JUMIMesh;

public class CEModel extends CEObject3D {

	private CEModelResource Model;
	private final HashMap<CEVAO, JUMIMesh> MashTable = new HashMap<CEVAO,JUMIMesh>();
	private CEVAO[] VaoArray;
	private int[] IndexSizes;
	
	
	private int indeces = 0;
	private boolean isLoadedResorce = false;

	private CE3DShader shader = CE3DShader.getInstance();

	public static CEModel CreateWithObjFile(String FIleName) {

		CEModel obj = new CEModel(FIleName);

		return obj;
	}

	private void setTexture(JUMIMesh mesh) {
		
	}

	private void RenderInit() {
		if (isLoadedResorce == false) {
			JUMIMesh[] meshs = Model.getModelSpace().getAllMeshes();

			VaoArray = new CEVAO[meshs.length];
			IndexSizes = new int[meshs.length];

			for (int i = 0; i < Model.getModelSpace().getAllMeshes().length; i++) {

				VaoArray[i] = CEVAO.CreateWithIndics(null, meshs[i].indices,
						new CEVboObject[] { new CEVboObject(0, 3, meshs[i].vertices)
								,new CEVboObject(2, 2, meshs[i].uvs), new CEVboObject(1, 3, meshs[i].normals) });
				IndexSizes[i] = meshs[i].indices.length;
			}

			isLoadedResorce = true;
		}

	}
 
	private boolean Loaded() {
		for (int i = 0; i < VaoArray.length; i++) {
			if (VaoArray[i].isGLLoaded() == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onInit() {

	}

	@Override
	public void onDraw() {

		if (Model.isLoaded() == false) {
			return;
		}

		RenderInit();

		if (Loaded() == false)
			return;
		
		ModelViewMatrix.resetIDENTITY();
		ModelViewMatrix.scale(0.1f, 0.1f, 0.1f);
		ModelViewMatrix.translate(0, 0, (float) -TESTER);
		ModelViewMatrix.rotate((float) TESTER, 0, 1, 0);
		shader.Start();
		shader.setProjectionMatrix(this.mCamera.getPorjection());
		shader.setCamreaMatrix(this.mCamera.getMovementMatrix());
		shader.setModelViewMatrix(this.ModelViewMatrix);
		for (int i = 0; i < VaoArray.length; i++) {
			RenderVAO(VaoArray[i], IndexSizes[i]);
		}
		shader.Stop();

		TESTER += 0.01;

	}

	private void RenderVAO(CEVAO vao, int indexSize) {

		CEGL.BindVertexArray(vao.getID());
		CEGL.EnableVertexAttribArray(0);
		CEGL.EnableVertexAttribArray(1);
		CEGL.EnableVertexAttribArray(2);
		CEGL.DrawElements(CEGL.GL_TRIANGLES, indexSize, CEGL.GL_UNSIGNED_INT, 0);
		CEGL.DisableVertexAttribArray(0);
		CEGL.DisableVertexAttribArray(1);
		CEGL.DisableVertexAttribArray(2);

		CEGL.BindVertexArray(0);

	}

	double TESTER = 0;

	@Override
	public void CleanUp() {

	}

	private CEModel(String file) {

		Model = new CEModelResource(file);
		this.mCamera = CESceneManager.getInstance().NowRender3DCamera;

		CEModelLoader.getInstance().LoadModel(file, Model);

	}

}
