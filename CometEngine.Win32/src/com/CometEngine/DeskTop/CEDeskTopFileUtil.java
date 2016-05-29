package com.CometEngine.DeskTop;

import java.io.File;
import java.nio.ByteBuffer;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngine.PLATFORM;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;


public class CEDeskTopFileUtil extends CEFileUtil {
	

	private CEDeskTopAsyncFileIO AsyncIoInterface = null;
	private CEDeskTopSyncFileIO SyncIoInterface = null;
	private CEDeskTopPlatForm FileInterFace = null;
	
	@Override
	protected void onCreate()
	{
		AsyncIoInterface = new CEDeskTopAsyncFileIO();
		SyncIoInterface = new CEDeskTopSyncFileIO();
		FileInterFace = new CEDeskTopPlatForm();
	}
	
	public String getFullResourcePath(String path)
	{
		return "../Resource/" + path;
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
