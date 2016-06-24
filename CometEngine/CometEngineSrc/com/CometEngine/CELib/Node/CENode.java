package com.CometEngine.CELib.Node;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.CometEngine.Event.Listener.CEEventListener;
import com.CometEngine.Util.Meth.CEScale3D;


public abstract class CENode {
	protected final LinkedList<CENode> ChildList = new LinkedList<CENode>();
	protected boolean isChildUpdated = false;
	protected CENode mParent = null;
	
	
	protected final ArrayList<CEEventListener> EventListenerList = new ArrayList<CEEventListener>();
	
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
	public void addEventListener()
	{
		
	}
	public void add(CENode node)
	{
		isChildUpdated = true;
		node.mParent = this;
		ChildList.add(node);
		Collections.sort(ChildList,CompareMethod);
		
	}
	public void add(CENode node, int weight){
		node.weight = weight;
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
