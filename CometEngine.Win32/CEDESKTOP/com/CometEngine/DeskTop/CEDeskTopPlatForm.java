package com.CometEngine.DeskTop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class CEDeskTopPlatForm {
	private static final String COMET_ROOT = "..//";

	public String getCashPath() {
		return  COMET_ROOT + "IOFiles/Cache";
	}

	public String getIOFilePath() {
		return COMET_ROOT  + "IOFiles"; 
	}



	public String getResourcePath() {
		// TODO Auto-generated method stub
		return COMET_ROOT + "Resource/";
	}
}

	

