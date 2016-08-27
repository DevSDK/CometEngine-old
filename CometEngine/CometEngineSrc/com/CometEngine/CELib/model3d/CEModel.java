package com.CometEngine.CELib.model3d;

import java.util.ArrayList;

import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.Model.obj.builder.Face;
import com.CometEngine.Model.obj.builder.FaceVertex;
import com.CometEngine.Renderer.VAO.CEVAO;
import com.CometEngine.Renderer.VAO.CEVAO.CEVboObject;
import com.CometEngine.Resrouce.CEModelLoader;
import com.CometEngine.Resrouce.CEModelResource;

public class CEModel extends CEObject3D {

	CEModelResource Model;

	CEVAO RenderVAO;

	private ArrayList<String> GroupList = null;
	private ArrayList<String> ObjectList = null;

	private boolean isLoadedResorce = false;

	private CE3DShader shader = CE3DShader.getInstance();

	public static CEModel CreateWithObjFile(String FIleName) {

		CEModel obj = new CEModel(FIleName);

		return obj;
	}

	private void initVertex() {

		GroupList = Model.getGroups();
		ObjectList = Model.getObjects();

		// 사각형을 삼각형으로 바꿧을때의 VertexCounter 도 고려하도록 설계하던, ArrayList를 이용해 최종본을 뽑든
		// 해야지.

		/////// FOR TESTING//////
		int indeces = 0;
		ArrayList<Integer> Indeces = new ArrayList<Integer>();
		ArrayList<Float> Vertexs = new ArrayList<Float>();
		ArrayList<Float> Normals = new ArrayList<Float>();
		ArrayList<Float> Texcoods = new ArrayList<Float>();
		//// TEST!!!////

		int itor_for_array = 0;

		for (int i = 0; i < ObjectList.size(); i++) {

			for (ArrayList<Face> f : Model.getFaceTree().get(ObjectList.get(i)).values()) {

				for (int j = 0; j < f.size(); j++) {

					ArrayList<FaceVertex> VertexList = f.get(j).vertices;

					for (int vertex_iter = 0; vertex_iter < f.get(j).vertices.size(); vertex_iter++) {

						/* FOR TESTING */
						if (VertexList.size() == 3) {

							Vertexs.add(VertexList.get(vertex_iter).v.x);
							Vertexs.add(VertexList.get(vertex_iter).v.y);
							Vertexs.add(VertexList.get(vertex_iter).v.z);

							Normals.add(VertexList.get(vertex_iter).n.x);
							Normals.add(VertexList.get(vertex_iter).n.y);
							Normals.add(VertexList.get(vertex_iter).n.z);

							Texcoods.add(VertexList.get(vertex_iter).t.u);
							Texcoods.add(VertexList.get(vertex_iter).t.v);

							indeces++;

							Indeces.add(indeces);

						}

						/* TESTING END */

					}

				}
			}

		}
		float[] ARRAY_Vertex = new float[Vertexs.size()];
		int[] indeces_array = new int[Indeces.size()];

		for (int i = 0; i < ARRAY_Vertex.length; i++) {
			ARRAY_Vertex[i] = Vertexs.get(i);
		}
		for (int i = 0; i < Indeces.size(); i++) {
			indeces_array[i] = Indeces.get(i);
		}
		for (int i = 0; i < Texcoods.size(); i++) {

		}
		CEVboObject[] objs = new CEVAO.CEVboObject[] { new CEVAO.CEVboObject(0, 3, Vertexs),
				new CEVAO.CEVboObject(1, 3, Normals) };

		RenderVAO = CEVAO.CreateWithIndics(null, indeces_array, objs);
		//
	}

	private void RenderInit() {
		if (isLoadedResorce == false) {
			initVertex();
			isLoadedResorce = true;
		}

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

		int vaoID = RenderVAO.getID();
		shader.Start();
		
		shader.Stop();

	}

	@Override
	public void CleanUp() {

	}

	private CEModel(String file) {

		Model = new CEModelResource(file);

		CEModelLoader.getInstance().LoadModel(file, Model);

	}

}
