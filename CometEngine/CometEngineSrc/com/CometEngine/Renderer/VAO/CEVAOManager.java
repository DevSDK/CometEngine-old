package com.CometEngine.Renderer.VAO;

import java.util.Hashtable;
import java.util.LinkedList;

public class CEVAOManager {
	private static long mIdPointer =  0;
	private static final Hashtable<Long, CEVAO> IDTable = new Hashtable<Long, CEVAO>();
	private static final CEVAOManager instence = new CEVAOManager();
	public static long genID(CEVAO vao)
	{
    	long id = ++mIdPointer;	;
    	IDTable.put(id, vao);    	
    	return id;	
	}
	public CEVAOManager getInstence()
	{
		return instence;
	}

    public static CEVAO getVAO(long id)
    {
    	if(IDTable.containsKey(id))
    	{
    		return IDTable.get(id);
      	}
    	return null;
    	
    }
}
