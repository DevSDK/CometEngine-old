package com.CometEngine.Commend.Manager;

import java.util.Hashtable;
import java.util.LinkedList;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCommandGroup;
import com.CometEngine.Renderer.Commend.CERenderCommandMesh;
import com.CometEngine.Renderer.Commend.CERenderCommandNull;



import com.CometEngine.Commend.*;

public class CERenderCommandManager {
	private static CERenderCommandManager m_s_Instence =  null;
	private static LinkedList<CERenderCommand> CommandQue = new LinkedList<CERenderCommand>();
	private Object Sync = new Object();
	
 	private CERenderCommandManager(){
			
	}
	
	public void AddCommand(CERenderCommand command)
	{
		synchronized (Sync) {
			CommandQue.add(command);
	}
	}
	public void _debug_LoggingInformation()
	{
		System.out.println(" Rendering QUE Lenght : " + CommandQue.size()  );
		for(CERenderCommand command : CommandQue )
		{
			System.out.println(command.getType());
		}
	}
	
	public void InvokeOneCommands()
	{
		synchronized (Sync) {
			if(CommandQue.isEmpty())
				return;
			CommandQue.getFirst().execute();
			CommandQue.removeFirst();			
		}
	}
	public void InvokeAllCommands()
	{
		synchronized(Sync){
			for(CERenderCommand command : CommandQue)
			{
				command.execute();
			}
			CommandQue.clear();			
		}		
	}
	
	public static CERenderCommandManager getInstence()
	{
		if(m_s_Instence == null)
			return m_s_Instence = new CERenderCommandManager();
		else
			return m_s_Instence;	
	}
}
