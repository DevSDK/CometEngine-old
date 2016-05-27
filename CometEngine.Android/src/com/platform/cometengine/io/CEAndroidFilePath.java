
package com.platform.cometengine.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.CometEngine.FileUtil.Interface.CEFilePathInterface;
import com.platform.cometengine.android.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;

public class CEAndroidFilePath implements CEFilePathInterface{
 private static Resources resource = null;
 private static Context context = null;
 public static void InitFileSysten(Resources _resource,Context _context )
 {
	 
	resource = _resource;
	context = _context;
 }
 
 	@Override
	public String getCashPath() {
		return context.getCacheDir().toString();
	}

	@Override
	public String getIOFilePath() {
		return context.getFilesDir().toString();
	}

	@Override
	public String getResourcePath() {
		return "assets://";
	}

	
}