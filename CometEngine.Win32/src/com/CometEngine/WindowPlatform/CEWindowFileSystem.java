package com.CometEngine.WindowPlatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.CometEngine.FileUtil.CEPlatformFileInterface;


public class CEWindowFileSystem implements CEPlatformFileInterface{
	private static final String COMET_ROOT = "..//";
	@Override
	public String getCashPath() {
		return  COMET_ROOT + "IOFiles/Cache";
	}

	@Override
	public String getIOFilePath() {
		return COMET_ROOT  + "IOFiles"; 
	}

	@Override
	public InputStream getResourceFile(String FileName) {
			
		try {
			return new FileInputStream(new File(COMET_ROOT  + "Resource" +"/"+ FileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
