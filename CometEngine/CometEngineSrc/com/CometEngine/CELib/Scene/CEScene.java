package com.CometEngine.CELib.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Button.CEPickableObject;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Camera.CECamera3D;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Object.CERenderableObject;
import com.CometEngine.CELib.Scheduler.CESchedule;
import com.CometEngine.CELib.Scheduler.CEScheduler;
import com.CometEngine.CELib.SkyBox.CESkyBox;
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
import com.CometEngine.Renderer.CEMatrixStack;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Commend.Manager.CERenderCommandManager;
import com.CometEngine.Util.Meth.CEPosition2D;

public class CEScene extends CERenderableObject {
	private final CEScheduler SCHEDULER = CEScheduler.Create();
	private CECamera2D camera2D = CECamera2D.Create();
	private CECamera3D camera3D = CECamera3D.Create();
	private CESkyBox CurrentSkyBox = null;
	int count = 0;
	int objectcounter = 0;
	boolean isExited = true;
	final CEScene scene = this;

	public void SetCamera(CECamera camera) {
		if (camera instanceof CECamera2D) {
			this.camera2D = (CECamera2D) camera;
		} else if (camera instanceof CECamera3D) {
			this.camera3D = (CECamera3D) camera;
		}
	}

	public void setSkyBox(CESkyBox skybox) {
		CurrentSkyBox = skybox;
	}

	public void set2DCamera(CECamera2D camera) {
		this.camera2D = camera;
	}

	public void set3DCamera(CECamera3D camera) {
		camera3D = camera;
	}

	public CECamera2D get2DCamera() {
		return camera2D;
	}

	public CECamera3D get3DCamera() {
		return camera3D;
	}

	public CEScene() {
		CESceneManager.getInstance().NowRender2DCamera = camera2D;
		CometEngine.getInstance().getSceneManager().NowRender3DCamera = camera3D;
	}

	public boolean isExit() {
		return isExited;
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

	private void VisitAndGenChildCommands() {
		for (int i = 0 ; i < ChildList.size(); i++) {
			if (ChildList.get(i) instanceof CERenderableObject) {
				RenderingList.add((CERenderableObject) ChildList.get(i));
			}
		}
	}

	private void UpdateRenderList() {
		CometEngine.getInstance().getSceneManager().NowRender2DCamera = camera2D;
		CometEngine.getInstance().getSceneManager().NowRender3DCamera = camera3D;
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

			if (CurrentSkyBox != null) {
				CurrentSkyBox.Render();
			}
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
		isExited = false;

		onEnter();
		SCHEDULER.EnterScene();
	}
 
	protected void MANAGER_CALL_EXIT() {
		isExited = true;
		LinkedList<CEObject> childs = getChilds();
		if(childs==null)
			return;
		for (CEObject obj : childs) {
			if(obj == null)
				continue;
			if (obj instanceof CEPickableObject) {
				((CEPickableObject) obj).UnPick();
			}
		}
		onExit();
		SCHEDULER.ExitScene();
	}

	protected void onExit() {

	}

	protected void onEnter() {

	}
}
