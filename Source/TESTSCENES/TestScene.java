package TESTSCENES;

import java.util.Timer;
import java.util.TimerTask;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBound2D;
import com.CometEngine.CELib.BoundBox.CEBoundBox;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Camera.CECamera2D;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scheduler.CESchedule;
import com.CometEngine.CELib.SkyBox.CESkyBox;
import com.CometEngine.CELib.Sprite.CESprite2D;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.CELib.model3d.CEModel;
import com.CometEngine.CELib.model3d.CEObject3D;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Event.CEEventMouse;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Renderer.Texture.CETexture2D;
import com.CometEngine.Renderer.Texture.CETextureCubeMap;
import com.CometEngine.Resrouce.CEModelLoader;
import com.CometEngine.Resrouce.CEModelResource;
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEFloat3D;
import com.CometEngine.Util.Meth.CEPosition2D;

public class TestScene extends CEScene {
	CETextLabel label;
	CETextLabel label2;
	CEObject sprite;
	CESprite2D sprite2;
	CESprite2D sprite3;
	CETextLabel FPSCounter;
	int timer = 0;
	int timercounter = 0;
	CEScene thisscene = this;
	CEScene BeforeScene;
	CECamera2D Cam = CECamera2D.Create();

	@Override
	protected void onEnter() {
		super.onEnter();
	}

	@Override
	protected void onExit() {
		super.onExit();
	}

	public void tick() {
		CEFloat3D pos = sprite.getPosition();

		sprite.setAngle(sprite.getAngle() + 0.1f);
		sprite.getPosition().x += 0.1f;
		sprite.getPosition().y += 0.1f;
		label.setString("Tick :  " + timer++);
		FPSCounter.setString("FPS : " + CometEngine.getInstance().getRenderer().getFPS());

		sprite2.setAngle(sprite2.getAngle() + 0.0f);

		if (sprite3.getAngle() >= 360) {
			sprite3.setAngle(0);
		}
		this.get3DCamera().getAngle().x += 0.01f;
		this.get3DCamera().getAngle().y += 0.01f;

		sprite3.setAngle(sprite3.getAngle() + 1f);

		sprite3.getPosition().x += 0.05f;
		sprite3.getPosition().y += 0.05f;
	}

	boolean paused = false;
	CESprite2D spritecg = CESprite2D.Create("Wood_Roughness.jpg");

	public TestScene() {
		this.add(spritecg);
		sprite = CESprite2D.Create("0.png");

		sprite2 = CESprite2D.Create("1.png");

		sprite3 = CESprite2D.Create("2.png");
		label = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.4f, false, "Timer");
		label2 = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.4f, true, "Comet Engine Tester", "DSM !",
				"CometEngineTester", "CometEngineTester", "CometEngineTester");

		CEEventListenerKeyboard listener = CEEventListenerKeyboard.Create(this);
		listener.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {
			@Override
			public void KeyBoardEvent(CEEventKeyboard event) {
				if (event.isKeyPush(CEKeyBoard.CE_KEY_P, CEKeyBoard.CE_KEY_STATUS_PRESS)) {
					paused = !paused;
					if (paused) {
						PauseSchedule(mainLoop);
					} else {
						ResumeAllSchedule();
					}
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_SPACE, CEKeyBoard.CE_KEY_STATUS_PRESS)) {
					if (CEBoundBox.getBoundingBoxAABB(spritecg)
							.isContainBoundingBox(CEBoundBox.getBoundingBoxAABB(sprite3))) {
						thisscene.remove(sprite3);
					}
				}
			}
		};
		CEEventDispatcher.getInstance().addEventListener(listener, this);

		CEEventListenerMouse mouse = CEEventListenerMouse.Create(this);
		mouse.MouseClickCallBack = new CEEventListenerMouse.CEMouseClick() {
			@Override
			public void invoke(int Button, int Status, double XPos, double YPos, CEEventMouse event) {
				if (Status == CEMouse.MOUSE_DOWN) {

				}
			}
		};
		mouse.MouseMoveCallBack = new CEEventListenerMouse.CEMouseMove() {
			@Override
			public void invoke(double XPos, double YPos) {

			}
		};
		mouse.MouseScrollCallBack = new CEEventListenerMouse.CEMouseScroll() {

			@Override
			public void invoke(double delta) {

			}
		};
		CEEventDispatcher.getInstance().addEventListener(mouse, this);

		FPSCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.4f, false, "FPS : ");

		mainLoop = CESchedule.CreateScheduleRepeat(new CESchedule.CHEDULERFUNC() {
			@Override
			public void invoke(float delta) {
				tick();
			}
		}, 0, 1);

		this.StartSchedule(mainLoop);

		label2.getPosition().y = 1000;
		label2.getControlPoint().x = 0;
		label2.getControlPoint().y = 1f;
		this.add(label2, 0);
		label.getControlPoint().x = 0;
		label.getControlPoint().y = 1;
		label.getPosition().x = 300;
		label.getPosition().y = 500;
		this.add(label, 4);
		label.setCamera(Cam);
		// this.add(sprite2, 2);
		this.add(sprite3);
		sprite3.setCamera(Cam);
		FPSCounter.getControlPoint().y = 0;
		FPSCounter.getControlPoint().x = 0;
		this.add(FPSCounter);
		sprite3.add(sprite2);

		sprite2.getPosition().x = 50;
		sprite2.setCamera(Cam);

		CESkyBox skybox = CESkyBox.Create(1000, new String[] { "skybox/top.png", "skybox/bottom.png",
				"skybox/front.png", "skybox/back.png", "skybox/right.png", "skybox/left.png" });
		this.setSkyBox(skybox);

		CEModel model = CEModel.CreateWithObjFile("3dTest/Handgun_Obj/Handgun_obj.obj");
		this.add(model);
	}

	CESchedule mainLoop;

}