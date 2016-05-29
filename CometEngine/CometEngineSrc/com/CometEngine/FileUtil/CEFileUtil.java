package com.CometEngine.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;

public abstract class CEFileUtil {
	private static CEFileUtil instence = null; 
	private static CometEngine.PLATFORM platform = PLATFORM.CE_NULL;
	
	public static boolean FileSystemInit(PLATFORM Platform, CEFileUtil platformUtilSystem)
	{
		if(instence != null)
			return false;
		instence = platformUtilSystem;
		platform = Platform;
		instence.onCreate();
		return true;
	}

	public static CEFileUtil getInstence()
	{
		if(instence == null)
		{
			System.err.println("NULL FILE SYSTEM" );
			return null;
		}
		return instence;
	}
	protected abstract void onCreate();
	
	public abstract ByteBuffer ReadResurceDirectoryToSync(String path);
	public abstract void ReadResoruceToAsync(String path, CEFileReadHandle handle);


}
