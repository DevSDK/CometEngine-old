
package com.platform.cometengine.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;

import android.os.AsyncTask;

public class CEAndroidAsyncFileIO {
	private static CEAndroidAsyncFileIO Instance;

	public CEAndroidAsyncFileIO() {
		Instance = this;
	}

	public static CEAndroidAsyncFileIO getInstance() {
		return Instance; 
	}

	class FileLoader extends AsyncTask<Object, Void, Void> {

		@Override
		protected Void doInBackground(Object... params) {
			String file = (String) params[0];
			CEFileReadHandle handler = (CEFileReadHandle) params[1];

			int size = (int) file.length();
			byte[] bytes = new byte[size];

			try {
				FileInputStream filestream = null;
				BufferedInputStream buf = new BufferedInputStream(filestream = new FileInputStream(file));
				buf.read(bytes, 0, bytes.length);

				filestream.close();
				buf.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			handler.complite(buffer);
			buffer.flip();
			return null;
		}

	}

	public void cleanUP() {

	}

	public void read(String file, CEFileReadHandle handle) {

		FileLoader loader = new FileLoader();
		loader.execute(file, handle);
	}

	public void write(String file, byte[] buf, CEFileWriteHandle handle) {

	}

}
