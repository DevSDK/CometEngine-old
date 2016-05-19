package com.CometEngine.FileUtil.Interface;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;

public abstract class CEAyncFileIOInterface implements CEFileIOInterface {
	private static CEAyncFileIOInterface instence = null;

	public static boolean initFileLoader(CEAyncFileIOInterface _instence)
	{
		if(instence == null)
		{
			instence = _instence;
			return true;
		}
		return false;
	}
	public static CEAyncFileIOInterface getInstence()
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
	public abstract void read(File file, CEFileReadHandle handle);
	public abstract void write(File file, byte [] buf , CEFileWriteHandle handle);
	
	
}
