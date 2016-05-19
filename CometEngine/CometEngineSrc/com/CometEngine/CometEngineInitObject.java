package com.CometEngine;

import com.CometEngine.FileUtil.Interface.CEAyncFileIOInterface;
import com.CometEngine.FileUtil.Interface.CEFilePathInterface;
import com.CometEngine.FileUtil.Interface.CESyncFileIOInterface;
import com.CometEngine.Renderer.CEGLInterface;

public class CometEngineInitObject {
	public CEGLInterface GL = null;
	public CEAyncFileIOInterface ASyncFileInterface = null;
	public CESyncFileIOInterface SyncFileInterface= null;
	public CEFilePathInterface fileInterface = null;
}
