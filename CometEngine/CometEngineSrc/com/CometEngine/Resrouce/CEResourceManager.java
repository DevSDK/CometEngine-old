package com.CometEngine.Resrouce;

import java.util.Hashtable;
import java.util.LinkedList;

public class CEResourceManager {
	private static CEResourceManager instence = null;
	private static Hashtable<String, CERawResrouce> resources = new Hashtable<String,  CERawResrouce>();

	private static final Object sync = new Object();
	public static CEResourceManager getInstence()
	{
			if(instence == null)
			{
				return instence = new CEResourceManager();
			}
			return instence;			
		
	}
	public void ShowLog()
	{
		System.out.println("Resource Manager : counter " + resources.size());
	}
	public <T extends CERawResrouce> T getResoruce(String filepath)
	{
		synchronized (sync) {
			return (T) resources.get(filepath);			
		}
	}
	
	
	
	public boolean isData(String filepath)
	{
		synchronized (sync) {
			return resources.containsKey(filepath);			
		}
	}
	public boolean isLoadedData(String filepath)
	{
		synchronized (sync) {
			boolean flag = false;
			
			if( isData(filepath))
			{
				flag = resources.get(filepath).isSetting();
			}
			
			return flag;			
		}
	}
	public int getSize()
	{
		synchronized (sync) {
			return resources.size();			
		}
	}
	public void putResoruceData(CERawResrouce resoruce)
	{
		synchronized (sync) {
		if(resources.containsKey(resoruce.getFilePath()))
		{
			//if(���� ����Ʈ�� ������ ����Ʈ���� �װ͸� ����� �Լ� ����
		}
		else
		{			
			resources.put(resoruce.getFilePath(), resoruce);
		}		
	}	
	}

}
