package com.platform.cometengine.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;


public class CEAndroidSyncFileIO{

	public void cleanUP() {
		// TODO Auto-generated method stub
		
	}

	public ByteBuffer read(File file) {
		
		
		
		int size = (int) file.length();
		byte[] bytes = new byte[size];
	
		try {
			FileInputStream stream = null;
			BufferedInputStream buf = new BufferedInputStream(stream = new FileInputStream(file));
		    buf.read(bytes, 0, bytes.length);
		    stream.close();
		    buf.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		return buffer;
	}

	public void write(File file, byte[] buf) {
		// TODO Auto-generated method stub
		
	}

}
