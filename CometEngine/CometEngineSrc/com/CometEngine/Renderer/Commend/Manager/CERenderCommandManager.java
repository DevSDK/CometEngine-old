package com.CometEngine.Renderer.Commend.Manager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCommandGroup;
import com.CometEngine.Renderer.Commend.CERenderCommandMesh;
import com.CometEngine.Renderer.Commend.CERenderCommandNull;



public class CERenderCommandManager {
	private static final CERenderCommandManager m_s_Instence =  new CERenderCommandManager();
	private static final List<CERenderCommand> CommandQue = new ArrayList<CERenderCommand>();
	private Object Sync = new Object();
	
 	
	public void AddCommand(CERenderCommand command)
	{
		synchronized (Sync) {
			CommandQue.add(command);
	}}
	public synchronized  void AddCommands(ArrayList<CERenderCommand> commands)
	{	
		CommandQue.addAll(commands);
	}

	public void _debug_LoggingInformation()
	{
		System.out.println(" Rendering QUE Lenght : " + CommandQue.size()  );
		for(CERenderCommand command : CommandQue )
		{
			System.out.println(command.getType());
		}
	}
	

	public void InvokeAllCommands()
	{
		synchronized(Sync){
			for(int i = 0 ;  i < CommandQue.size(); i++)
			{
				CommandQue.get(i).execute();
			}
			CommandQue.clear();			
		}		
	}
	
	public static CERenderCommandManager getInstence()
	{
			return m_s_Instence;	
	}
}
