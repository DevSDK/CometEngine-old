package com.CometEngine.DeskTop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;

public class CEDeskTopFileUtil extends CEFileUtil {

	private CEDeskTopAsyncFileIO AsyncIoInterface = null;
	private CEDeskTopSyncFileIO SyncIoInterface = null;
	private CEDeskTopPlatForm FileInterFace = null;

	@Override
	protected void onCreate() {
		AsyncIoInterface = new CEDeskTopAsyncFileIO();
		SyncIoInterface = new CEDeskTopSyncFileIO();
		FileInterFace = new CEDeskTopPlatForm();

	}

	public static ByteBuffer inputStreamToByteArray(InputStream is) throws IOException {

		byte[] resBytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		byte[] buffer = new byte[2048];
		int read = -1;
		while ((read = is.read(buffer)) != -1) {
			bos.write(buffer, 0, read);
		}

		resBytes = bos.toByteArray();
		bos.close();

		ByteBuffer buf = ByteBuffer.wrap(resBytes);

		return buf;
	}

	public String getFullResourcePath(String path) {
		return "/Resource/" + path;
	}

	public ByteBuffer ReadResurceDirectoryToSync(String path) {

		return SyncIoInterface.read(path);

	}

	public void ReadResoruceToAsync(String path, CEFileReadHandle handle) {
		AsyncIoInterface.read(path, handle);
	}
}
