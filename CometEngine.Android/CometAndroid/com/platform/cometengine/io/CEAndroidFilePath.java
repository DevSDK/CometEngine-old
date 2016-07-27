
package com.platform.cometengine.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;

public class CEAndroidFilePath {
 private static Resources resource = null;
 private static Context context = null;
 public static void InitFileSysten(Resources _resource,Context _context )
 {
	 
	resource = _resource;
	context = _context;
 }
 
	public String getCashPath() {
		return context.getCacheDir().toString();
	}

	public String getIOFilePath() {
		return context.getFilesDir().toString();
	}

	public  static Context getContext(){
		return context;
	}

	
}