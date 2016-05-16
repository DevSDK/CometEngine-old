package com.CometEngine.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public interface CEPlatformFileInterface {
	
	public String getCashPath();
	public String getIOFilePath();
	public InputStream  getResourceFile(String FileName);
}
