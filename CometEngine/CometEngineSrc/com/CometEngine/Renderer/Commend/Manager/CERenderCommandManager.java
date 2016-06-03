package com.CometEngine.Renderer.Commend.Manager;

import java.util.Hashtable;
import java.util.LinkedList;

import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCommandGroup;
import com.CometEngine.Renderer.Commend.CERenderCommandMesh;
import com.CometEngine.Renderer.Commend.CERenderCommandNull;



public class CERenderCommandManager {
	private static final CERenderCommandManager m_s_Instence =  new CERenderCommandManager();
	private static LinkedList<CERenderCommand> CommandQue = new LinkedList<CERenderCommand>();
	private Object Sync = new Object();
	
 	
	public void AddCommand(CERenderCommand command)
	{
		synchronized (Sync) {
			CommandQue.add(command);
	}}
	public  synchronized  void AddCommands(LinkedList<CERenderCommand> commands)
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
			return m_s_Instence;	
	}
}
