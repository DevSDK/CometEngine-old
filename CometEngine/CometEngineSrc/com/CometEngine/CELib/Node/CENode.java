package com.CometEngine.CELib.Node;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.CometEngine.Util.Meth.CEScale3D;


public abstract class CENode {
	protected LinkedList<CENode> ChildList = new LinkedList<CENode>();
	protected boolean isChildUpdated = false;
	protected CENode mParent = null;
	
	
	private int weight = 0;
	private static final Comparator<CENode> CompareMethod = new Comparator<CENode>() {
		@Override
		public int compare(CENode arg0, CENode arg1) {
			 if(arg0.weight > arg1.weight)
				 return 1;
			 else if(arg0.weight == arg1.weight)
				 return 0;
			 else
				 return -1;
		}
	};
	
	public void add(CENode node)
	{
		isChildUpdated = true;
		ChildList.add(node);
		Collections.sort(ChildList,CompareMethod);
		
	}
	public void add(CENode node, int weight){
		node.weight = weight;
		node.mParent = this;
		this.add(node);
	}
	public void removeChild(CENode node)
	{
		if(ChildList.contains(node))
		{
			node.mParent = null;
			ChildList.remove(node);
			
			isChildUpdated = true;
		}
	
	}
	
	
}
