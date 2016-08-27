package com.CometEngine.DeskTop;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;

public class CEAsyncLoader extends Thread {

	private String currentLoadFile = "";

	private CEFileReadHandle handle;

	public void setHandle(CEFileReadHandle handle) {
		this.handle = handle;
	}

	public void setFILE(String Filename) {
		currentLoadFile = Filename;
	}

	public void run() {
		ByteBuffer buf = null;
		IOException se = null;
		try {
			buf = CEDeskTopFileUtil
					.inputStreamToByteArray(this.getClass().getClassLoader().getResourceAsStream(currentLoadFile));

		} catch (IOException e) {
			se = e;
			e.printStackTrace();
			buf = null;
		}
		if (buf != null) {
			handle.complite(buf);
		} else {
			handle.failure(se);
		}
	}

}
