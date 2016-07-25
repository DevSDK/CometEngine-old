package com.CometEngine.Resrouce;

import java.util.ArrayList;
import java.util.HashMap;

import com.CometEngine.Model.obj.builder.Face;
import com.CometEngine.Renderer.CEGLResource;

public class CEModelResource extends CEResource {
	HashMap<String, ArrayList<Face>>  Model  = new HashMap<String, ArrayList<Face>>();

	public CEModelResource(String filepath) {
		super(filepath);
	}

}
