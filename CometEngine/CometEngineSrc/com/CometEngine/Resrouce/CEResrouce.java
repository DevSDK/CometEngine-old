package com.CometEngine.Resrouce;

import java.nio.ByteBuffer;

public class CEResrouce {

	private String FilePath = "" ;
	private boolean isLoaded = false;
	//Resource POLL »ý°¢Áß...
	private ByteBuffer Data = null;
	
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
	public CEResrouce(String filepath)
	{
		this.FilePath = filepath;
	}
	public void setData(ByteBuffer buffer)
	{
			Data = buffer;			
	}
	public ByteBuffer getData()
	{
		return Data;
	}
}
