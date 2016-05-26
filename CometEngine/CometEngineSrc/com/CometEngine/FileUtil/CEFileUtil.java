package com.CometEngine.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Interface.CEAyncFileIOInterface;
import com.CometEngine.FileUtil.Interface.CEFilePathInterface;
import com.CometEngine.FileUtil.Interface.CESyncFileIOInterface;

public class CEFileUtil {
	private static CEFileUtil instence = null; 
	private static CometEngine.PLATFORM platform = PLATFORM.CE_NULL;
	private static CEFilePathInterface FileInterFace = null;
	private static CESyncFileIOInterface SyncIoInterface = null;
	private static CEAyncFileIOInterface AsyncIoInterface= null;
	
	public static boolean FileSystemInit(PLATFORM Platform, CEFilePathInterface fileinterface, CESyncFileIOInterface syncfileinterface, CEAyncFileIOInterface asyncfileinterface)
	{
		if(instence != null)
			return false;
		instence = new CEFileUtil();
		platform = Platform;
		FileInterFace = fileinterface;
		SyncIoInterface = syncfileinterface;
		AsyncIoInterface = asyncfileinterface;
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
	public String getFullResourcePath(String path)
	{
		return FileInterFace.getResourcePath() + path;
	}
	public ByteBuffer ReadResurceDirectoryToSync(String path)
	{
		
			 return SyncIoInterface.read(new File(FileInterFace.getResourcePath()+ path));
			
	
	}
	public void ReadResoruceToAsync(String path, CEFileReadHandle handle)
	{
		AsyncIoInterface.read(new File(FileInterFace.getResourcePath() + path), handle);
	}

}
