package com.CometEngine.Renderer.Commend;

public abstract class CERenderCommand {
	public enum COMMENDTYPE
	{
		CE_COMMEND_NULL,
		CE_COMMEND_GL_MESH,
		CE_COMMEND_GL_CUSTIOM,
		CE_COMMEND_GL_GROUP
	}

	private COMMENDTYPE CommendType = COMMENDTYPE.CE_COMMEND_NULL;

	private int Weight = 0;
	
	public int getWeigt()
	{
		return Weight;
	}
	public void setWeigt(int weight)
	{
		Weight = weight;
	}

	public abstract void execute();
	
	public COMMENDTYPE getType()
	{
		return CommendType;
	}
	public CERenderCommand(COMMENDTYPE commendType)
	{
		
	}
	
	
	
	
	
}
