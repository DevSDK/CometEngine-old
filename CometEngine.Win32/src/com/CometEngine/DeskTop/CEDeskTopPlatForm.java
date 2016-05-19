package com.CometEngine.DeskTop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.CometEngine.FileUtil.Interface.CEFilePathInterface;


public class CEDeskTopPlatForm implements CEFilePathInterface{
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
	public String getResourcePath() {
		// TODO Auto-generated method stub
		return COMET_ROOT + "Resource/";
	}
}

	

