package com.CometEngine.FileUtil.Interface;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;

public abstract class CESyncFileIOInterface implements CEFileIOInterface {
	private static CESyncFileIOInterface instence = null;

	public static boolean initFileLoader(CESyncFileIOInterface _instence)
	{
		if(instence == null)
		{
			instence = _instence;
			return true;
		}
		return false;
	}
	public static CESyncFileIOInterface getInstence()
	{
		try{
		if(instence!=null)
			return instence;
		else
		{
			throw new Exception("File AyncFileIOInstence has not init");
			
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public abstract void cleanUP();
	public abstract ByteBuffer read(String file);
	public abstract void write(File file, byte [] buf );
	
}
