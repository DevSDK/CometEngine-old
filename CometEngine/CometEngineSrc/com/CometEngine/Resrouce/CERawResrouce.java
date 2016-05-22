package com.CometEngine.Resrouce;

import java.nio.ByteBuffer;

public class CERawResrouce {

	private String FilePath = "" ;
	private boolean isLoaded = false;
	//Resource POLL »ý°¢Áß...
	private ByteBuffer Data = null;
	private static Object sync = new Object();
	
	public String getFilePath()
	{
		return FilePath;
	}
	public boolean isSetting()
	{
		return isLoaded;
	}
	public void setIsLoaded(boolean flag)
	{
		isLoaded = flag;
	}
	public CERawResrouce(String filepath)
	{
		this.FilePath = filepath;
	}
	public void setData(ByteBuffer buffer)
	{
		synchronized(sync){
			Data = buffer;			
		}
	}
	public ByteBuffer getData()
	{
		return Data;
	}
}
