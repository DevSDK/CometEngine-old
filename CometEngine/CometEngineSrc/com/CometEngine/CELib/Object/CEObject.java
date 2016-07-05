package com.CometEngine.CELib.Object;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.CometEngine.Event.CEEventListener;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEFloat3D;
import com.CometEngine.Util.Meth.CEPosition2D;
import com.CometEngine.Util.Meth.CEScale3D;


public abstract class CEObject {
	protected final LinkedList<CEObject> ChildList = new LinkedList<CEObject>();
	protected boolean isChildUpdated = false;
	protected CEObject mParent = null;
	
	
	protected final ArrayList<CEEventListener> EventListenerList = new ArrayList<CEEventListener>();
	
	private int weight = 0;
	private static final Comparator<CEObject> CompareMethod = new Comparator<CEObject>() {
		@Override
		public int compare(CEObject arg0, CEObject arg1) {
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
	public void add(CEObject node)
	{
		isChildUpdated = true;
		node.mParent = this;
		ChildList.add(node);
		Collections.sort(ChildList,CompareMethod);
		
	}
	public void add(CEObject node, int weight){
		node.weight = weight;
		this.add(node);
	}
	public void removeChild(CEObject node)
	{
		if(ChildList.contains(node))
		{
			node.mParent = null;
			ChildList.remove(node);
			
			isChildUpdated = true;
		}
	
	}
	
	
	
	
	// Node
	protected final CEPosition2D mPosition = new CEPosition2D();;
	protected float angle = 0;
	protected final CEFloat3D scale = new CEFloat3D(1, 1, 1);
	protected final CEFloat3D control_point  = new CEFloat3D(0.5f, 0.5f, 0.5f); 
	
	
	public CEFloat3D getControlPoint()
	{
		return control_point;
	}
	
	public CEFloat3D getScale()
	{
		return scale;
	}

	public CEPosition2D getPosition()
	{
		return mPosition;
	}
	public void setAngle(float angle)
	{
		this.angle = angle;
	}
	public float getAngle()
	{
		return angle;
	}

	
}
