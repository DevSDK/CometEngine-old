package com.CometEngine.Resrouce;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.CELib.model3d.CEModel;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.Model.obj.builder.ObjecBuilder;
import com.CometEngine.Model.obj.parser.Parse;
import com.jumi.JUMILoader;
import com.jumi.scene.JUMIScene;

public class CEModelLoader {
	private static CEModelLoader Instance = new CEModelLoader();

	public static CEModelLoader getInstance() {
		return Instance;
	}
	
	public void LoadModel(final String FilePath, final CEModelResource Model) {
		
		
		CEFileUtil.getInstence().ReadResoruceToAsync(FilePath, new CEFileReadHandle() {
		
			
			@Override
			public void failure(Throwable e) {
				System.err.println("FALLURE FILE LOAD");
				e.printStackTrace();

			}

			@Override
			public void complite(ByteBuffer data) {
				if (FilePath.endsWith("obj") || FilePath.endsWith("fbx")) {
						JUMIScene  modelSpace = JUMILoader.loadModel(data, FilePath);
						
						Model.setModelSpace(modelSpace);
						Model.setIsLoaded(true);

					return;
				}

			}
		});
	}
}
//TODO: �ϳ��� ���� �������̽� ���� But MetaFile �ȿ� Matrerial Data�� ������ ������� �߰����� �۾��� �����ϵ��� ����.