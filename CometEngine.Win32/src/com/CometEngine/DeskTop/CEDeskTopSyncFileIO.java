package com.CometEngine.DeskTop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.file.Files;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;
import com.CometEngine.FileUtil.Interface.CESyncFileIOInterface;


public class CEDeskTopSyncFileIO extends CESyncFileIOInterface
{

	@Override
	public void cleanUP() {
		
	}



	

	@Override
	public void write(File file, byte[] buf) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public ByteBuffer read(String file) {
	
		
			try {
				byte [] buf= Files.readAllBytes((new File(file)).toPath());
				
				ByteBuffer buffer = ByteBuffer.wrap(buf);
				return buffer;
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		return null;

		
		}
	}
