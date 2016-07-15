package com.CometEngine.CELib.Object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Event.CEEventListener;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEFloat3D;
import com.CometEngine.Util.Meth.CEPosition2D;
import com.CometEngine.Util.Meth.CEScale3D;

public abstract class CEObject {
	protected final LinkedList<CEObject> ChildList = new LinkedList<CEObject>();
	protected boolean isChildUpdated = false;
	protected CEObject mParent = null;
	protected boolean isActive = true;
	protected CEObject Root = null;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean activestat) {
		isActive = activestat;
	}

	private int weight = 0;
	private static final Comparator<CEObject> CompareMethod = new Comparator<CEObject>() {
		@Override
		public int compare(CEObject arg0, CEObject arg1) {
			if (arg0.weight > arg1.weight)
				return 1;
			else if (arg0.weight == arg1.weight)
				return 0;
			else
				return -1;
		}
	};

	public abstract void CleanUp();

	public void add(CEObject node) {
		if (ChildList.contains(node) == false) {
			isChildUpdated = true;
			ChildList.add(node);
			Collections.sort(ChildList, CompareMethod);
			node.setParent(this);
			Root = getRoot();
		}
	}

	public LinkedList<CEObject> getChilds() {
		return ChildList;
	}

	public void add(CEObject node, int weight) {
		node.weight = weight;
		this.add(node);
	}

	public void remove(CEObject object) {
		remove(object, false);
	}

	public void remove(CEObject object, boolean isClean) {
		if (ChildList.contains(object)) {
			object.mParent = null;
			ChildList.remove(object);
			isChildUpdated = true;
		}
		if (isClean == true) {
			CleanUp();
		}
	}

	// Node

	protected final CEPosition2D mPosition = new CEPosition2D();;
	protected float angle = 0;
	protected final CEFloat3D scale = new CEFloat3D(1, 1, 1);
	protected final CEFloat3D control_point = new CEFloat3D(0.5f, 0.5f, 0.5f);

	public CEFloat3D getControlPoint() {
		return control_point;
	}

	public CEFloat3D getScale() {
		return scale;
	}

	public CEPosition2D Position() {
		return mPosition;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public CEObject getRoot() {
		CEObject obj = mParent;
		while (obj != null) {
			if (obj.mParent == null)
				break;
			obj = obj.mParent;
		}
		return obj;
	}

	public void setParent(CEObject p) {
		this.mParent = p;
	}

	public float getAngle() {
		return angle;
	}

	public void RemoveCallBack() {

	}

}
