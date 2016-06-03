package com.CometEngine.CELib.Node;
import java.util.LinkedList;


public abstract class CENode {
	protected LinkedList<CENode> Childes = new LinkedList<CENode>();
	public enum NODE_TYPE {CE_NODE,CE_SOUND ,CE_SCENE, CE_SPRITE  };
	protected NODE_TYPE mNodeType = NODE_TYPE.CE_NODE;
	public CENode (NODE_TYPE type)
	{
		this.mNodeType = type;
	}
	
}
