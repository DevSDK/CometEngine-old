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
	private CERenderCommandManager(){
			
	}
	

	public CERenderCommand genCommand(CERenderCommand.COMMENDTYPE CommendType){
		  CERenderCommand command =  CreateCommand(CommendType);
		 
		  return command;
	}
	
	private CERenderCommand CreateCommand(CERenderCommand.COMMENDTYPE type)
	{
		switch (type) {
		case CE_COMMEND_NULL:
			return new CERenderCommandNull();
		case CE_COMMEND_GL_CUSTIOM:
			return new CERenderCommandCustom();
		case CE_COMMEND_GL_MESH:
			return new CERenderCommandMesh();
		case CE_COMMEND_GL_GROUP:
			return new CERenderCommandGroup();
		default:
			return null;
		}
	}
	public static CERenderCommandManager getInstence()
	{
		if(m_s_Instence == null)
			return m_s_Instence = new CERenderCommandManager();
		else
			return m_s_Instence;	
	}
	
	public static void main(String [] arg)
	{
		for(int i = 0 ; i < 2000000; i++)
		{	
			CERenderCommand command = 	CERenderCommandManager.getInstence().genCommand(CERenderCommand.COMMENDTYPE.CE_COMMEND_GL_MESH);
			System.out.println(command.hashCode());
		}
	}
}
