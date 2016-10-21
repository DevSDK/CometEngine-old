package com.CometEngine.Resrouce;

import java.util.ArrayList;
import java.util.HashMap;

import com.CometEngine.Model.obj.builder.Face;
import com.CometEngine.Model.obj.builder.ObjecBuilder;
import com.CometEngine.Renderer.CEGLResource;
import com.CometEngine.Renderer.Mesh.CEMaterial;
import com.CometEngine.Renderer.Texture.CETexture2D;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.jumi.scene.JUMIScene;
import com.jumi.scene.objects.JUMIMaterial;
import com.jumi.scene.objects.JUMIMesh;

public class CEModelResource extends CEResource {

	public JUMIScene getModelSpace() {
		return ModelSpace;
	}

	public CEModelResource(String filepath) {
		super(filepath);
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	protected void setModelSpace(JUMIScene space) {
		ModelSpace = space;

		for (int i = 0; i < ModelSpace.getAllMeshes().length; i++) {
			JUMIMesh[] Meshs = ModelSpace.getAllMeshes();
			for (int ti = 0; ti < Meshs[i].materials.length; ti++) {
				JUMIMaterial mat = Meshs[i].materials[i];
				if (mat.diffuseTexture != null) {
					CETexture2D.CreateTexture2D(mat.diffuseTexture.fullFilePath);
				}
				if (mat.normalTexture != null) {

					CETexture2D.CreateTexture2D(mat.normalTexture.fullFilePath);
				}
				if (mat.specularTexture != null) {

					CETexture2D.CreateTexture2D(mat.normalTexture.fullFilePath);
				}
			}

		}

	}

	private JUMIScene ModelSpace = null;

}
