package com.platform.cometengine.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;

import android.content.Context;

public class CEAndroidFileUtil extends CEFileUtil {
	
	CEAndroidAsyncFileIO AsyncIoInterface = null;
	CEAndroidSyncFileIO SyncIoInterface = null;
	Context context = null;

	@Override
	protected void onCreate() {
		AsyncIoInterface = new CEAndroidAsyncFileIO();
		SyncIoInterface = new CEAndroidSyncFileIO();
		context = CEAndroidFilePath.getContext();
	}

	@Override
	public ByteBuffer ReadResurceDirectoryToSync(String path) {
		InputStream stream = null;
		try {
			stream = context.getAssets().open(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] array = null;
		try {
			array = new byte[stream.available()];
			stream.read(array);

			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (array == null)
			return null;
		ByteBuffer buffer = ByteBuffer.wrap(array);
		return buffer;

	}

	@Override
	public void ReadResoruceToAsync(String path, CEFileReadHandle handle) {
		InputStream stream = null;
		try {
			stream = context.getAssets().open(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] array = null;
		try {
			array = new byte[stream.available()];
			stream.read(array);
			stream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ByteBuffer buffer = ByteBuffer.wrap(array);
		handle.complite(buffer);
		buffer.flip();

	}
}
