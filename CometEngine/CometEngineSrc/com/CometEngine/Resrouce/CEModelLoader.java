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
				if (FilePath.endsWith("obj")) {
					ObjecBuilder bulder = new ObjecBuilder(FilePath);
					try {
						Parse parser = new Parse(bulder, data, FilePath);
						Model.SetBuilder(bulder);
						Model.setIsLoaded(true);
					} catch (IOException e) {

						e.printStackTrace();
					}

					return;
				}

			}
		});
	}
}
