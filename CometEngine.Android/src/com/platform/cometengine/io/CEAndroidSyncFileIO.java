package com.platform.cometengine.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.Interface.CESyncFileIOInterface;

public class CEAndroidSyncFileIO extends CESyncFileIOInterface{

	@Override
	public void cleanUP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ByteBuffer read(File file) {
		
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
		
		return buffer;
	}

	@Override
	public void write(File file, byte[] buf) {
		// TODO Auto-generated method stub
		
	}

}
