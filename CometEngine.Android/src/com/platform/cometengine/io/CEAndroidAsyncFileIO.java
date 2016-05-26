package com.platform.cometengine.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;
import com.CometEngine.FileUtil.Interface.CEAyncFileIOInterface;

public class CEAndroidAsyncFileIO extends CEAyncFileIOInterface {

	@Override
	public void cleanUP() {
		
	}

	@Override
	public void read(File file, CEFileReadHandle handle) {
		
		
		//TODO: TESTER _ THIS IS SYNC METOHD MUST REPLACE THIS CODE
	
		int size = (int) file.length();
		byte[] bytes = new byte[size];
	
		try {
		
			BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
		    buf.read(bytes, 0, bytes.length);
		    buf.close();
	
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		handle.complite(buffer);
	}
	

	@Override
	public void write(File file, byte[] buf, CEFileWriteHandle handle) {
		
	}

}
