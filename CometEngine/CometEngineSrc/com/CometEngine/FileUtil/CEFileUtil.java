package com.CometEngine.FileUtil;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;

public class CEFileUtil {
	private static CEFileUtil instence = null; 
	private static CometEngine.PLATFORM platform = PLATFORM.CE_NULL;
	private static CEPlatformFileInterface FileInterFace = null;
	public static boolean FileSystemInit(PLATFORM Platform, CEPlatformFileInterface fileinterface)
	{
		if(instence != null)
			return false;
		instence = new CEFileUtil();
		platform = Platform;
		FileInterFace = fileinterface;
		return true;
	}
	public static CEFileUtil getInstence()
	{
		if(FileInterFace == null || platform == PLATFORM.CE_NULL || FileInterFace == null)
		{
			System.err.println("NULL FILE SYSTEM" );
			return null;
		}
		return instence;
	}
	public CEPlatformFileInterface getFileInstence()
	{
		return FileInterFace;
	}
	public CometEngine.PLATFORM getPlatForm()
	{
		return platform;
	}

}
