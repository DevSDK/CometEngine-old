package com.CometEngine.CELib.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Camera.CE2DDefaultCamera;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEvent;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Commend.Manager.CERenderCommandManager;
import com.CometEngine.Scheduler.CESchedule;
import com.CometEngine.Scheduler.CEScheduler;
import com.CometEngine.Util.Meth.CEPosition2D;

public class CEScene
		extends CERenderableObject /* extends root class ex) Node */ {
	private final CEScheduler SCHEDULER = new CEScheduler();
	private CECamera DefaultCamera = new CE2DDefaultCamera();

	// Maybe move to Node
	int count = 0;
	int objectcounter = 0;
	boolean isExited = true;
	final CEScene scene = this;

	public CEScene() {

	}

	

	public void PuaseAllSchedule() {
		SCHEDULER.PauseAll();
	}

	public void ResumeAllSchedule() {
		SCHEDULER.ResumeAll();
	}

	public void PauseSchedule(CESchedule schedule) {
		SCHEDULER.Pause(schedule);
	}

	public void ResumeSchedule(CESchedule schedule) {
		SCHEDULER.Resume(schedule);
	}

	public void StartSchedule(CESchedule schedule) {
		SCHEDULER.Run(schedule);
	}

	public boolean isFinalize() {
		return isExited;
	}

	private void VisitAndGenChildCommands() {
		for (CEObject child : ChildList) {
			if (child instanceof CERenderableObject) {
				RenderingList.add((CERenderableObject) child);
			}
		}
	}

	private final List<CERenderableObject> RenderingList = new ArrayList<CERenderableObject>();

	private void UpdateRenderList() {
		RenderingList.clear();
		VisitAndGenChildCommands();
	}

	private CERenderCommandCustom SpriteRenderCommand = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
		@Override
		public void invoke() {

			if (isChildUpdated == true) {
				UpdateRenderList();
				isChildUpdated = false;
			}

			CometEngine.getInstance().getSceneManager().nowRender2DCamera = DefaultCamera;

			Drawing();

			for (int i = 0; i < RenderingList.size(); i++) {
				CERenderCommand command = RenderingList.get(i).genRenderCommand();
				if (command != null) {

					command.execute();

				}
			}
		}
	});

	@Override
	public CERenderCommandCustom genRenderCommand() {
		return SpriteRenderCommand;
	}

	@Override
	public void onDraw() {

	}

	@Override
	public void onInit() {

	}

	@Override
	public void CleanUp() {

	}

	protected void MANAGER_CALL_ENTER() {
		SCHEDULER.EnterScene();
		isExited = false;
		onEnter();
	}

	protected void MANAGER_CALL_EXIT() {
		onExit();
		isExited = true;
		SCHEDULER.ExitScene();
	}

	protected void onExit() {

	}

	protected void onEnter() {

	}
}
