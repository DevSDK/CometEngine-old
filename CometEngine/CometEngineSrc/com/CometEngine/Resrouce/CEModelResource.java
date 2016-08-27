package com.CometEngine.Resrouce;

import java.util.ArrayList;
import java.util.HashMap;

import com.CometEngine.Model.obj.builder.Face;
import com.CometEngine.Model.obj.builder.ObjecBuilder;
import com.CometEngine.Renderer.CEGLResource;

public class CEModelResource extends CEResource {

	private ObjecBuilder build;

	public boolean isLoaded() {
		return isLoaded;
	}

	public void SetBuilder(ObjecBuilder builder) {
		this.build = builder;
	}

	public HashMap<String, HashMap<String, ArrayList<Face>>> getFaceTree() {
		return build.groups;
	}

	public ArrayList<String> getObjects() {
		return build.ObjectList;
	}

	public ArrayList<String> getGroups() {
		
		return build.GroupList;
	}

	public CEModelResource(String filepath) {
		super(filepath);
	}

	public int VertexCount() {
		return build.faceVerticeList.size();
	}

}
