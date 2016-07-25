package com.CometEngine.Renderer.Mesh;

import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.Model.obj.builder.Material;
import com.CometEngine.Renderer.CEGLResource;
import com.CometEngine.Renderer.VAO.CEVAO;

public class CEMesh extends CEGLResource {
	
	CEVAO vao;
	Material material;
	
	@Override
	protected void onGLLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onGLDelete() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isloaded() {
		// TODO Auto-generated method stub
		return false;
	}

}
