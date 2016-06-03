package com.CometEngine.Resrouce;

import java.util.Hashtable;
import java.util.LinkedList;

public class CEResourceManager {
	private static final CEResourceManager instence = new CEResourceManager();
	private static Hashtable<String, CEResrouce> resources = new Hashtable<String,  CEResrouce>();

	private static final Object sync = new Object();
	public static CEResourceManager getInstence()
	{
			return instence;			
		
	}
	public void ShowLog()
	{
		System.out.println("Resource Manager : counter " + resources.size());
	}
	public  <T extends CEResrouce> T getResoruce(String filepath)
	{
			return (T) resources.get(filepath);			
		
	}
	
	
	
	public boolean isData(String filepath)
	{
			return resources.containsKey(filepath);			
	}
	public boolean isLoadedData(String filepath)
	{
			boolean flag = false;
			
			if( isData(filepath))
			{
				flag = resources.get(filepath).isSetting();
			}
			
			return flag;			
	}
	
	public int getSize()
	{
			return resources.size();			
		
	}
	public void putResoruceData(CEResrouce resoruce)
	{
		if(resources.containsKey(resoruce.getFilePath()))
		{
			//if(제거 리스트에 있으면 리스트에서 그것만 지우고 함수 종료
		}
		else
		{			
			resources.put(resoruce.getFilePath(), resoruce);
		}		
	
	}

}
