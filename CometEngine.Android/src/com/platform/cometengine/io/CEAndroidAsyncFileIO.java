package com.platform.cometengine.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;

public class CEAndroidAsyncFileIO{

	public void cleanUP() {
		
	}

	public void read(String file, CEFileReadHandle handle) {
		
		
		//TODO: TESTER _ THIS IS SYNC METOHD MUST REPLACE THIS CODE
	
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
		handle.complite(buffer);
		buffer.flip();
	}
	

	public void write(String file, byte[] buf, CEFileWriteHandle handle) {
		
	}

}
