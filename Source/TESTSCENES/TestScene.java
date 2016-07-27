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
import com.CometEngine.CELib.Sprite.CESprited2D;
import com.CometEngine.CELib.Text.CETextLabel;
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
import com.CometEngine.Util.Meth.CEFloat2D;
import com.CometEngine.Util.Meth.CEPosition2D;

public class TestScene extends CEScene {
	CETextLabel label;
	CETextLabel label2;
	CEObject sprite;
	CESprited2D sprite2;
	CESprited2D sprite3;
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
		CEPosition2D pos = sprite.Position();

		sprite.setAngle(sprite.getAngle() + 0.1f);
		sprite.Position().x += 0.1f;
		sprite.Position().y += 0.1f;
		label.setString("Tick :  " + timer++);
		FPSCounter.setString("FPS : " + CometEngine.getInstance().getRenderer().getFPS());

		sprite2.setAngle(sprite2.getAngle() + 0.0f);

		if (sprite3.getAngle() >= 360) {
			sprite3.setAngle(0);
		}
		this.get3DCamera().getAngle().x += 0.0001f;
		this.get3DCamera().getAngle().y += 0.0001f;

		sprite3.setAngle(sprite3.getAngle() + 0.02f);

		sprite3.Position().x += 0.05f;
		sprite3.Position().y += 0.05f;
	}

	boolean paused = false;
	CESprited2D spritecg = CESprited2D.Create("Wood_Roughness.jpg");

	public TestScene() {
		this.add(spritecg);
		sprite = CESprited2D.Create("0.png");

		sprite2 = CESprited2D.Create("1.png");

		sprite3 = CESprited2D.Create("2.png");
		label = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.4f, false, "Timer");
		label2 = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.4f, true, "Comet Engine Tester", "DSM !",
				"CometEngineTester", "CometEngineTester", "CometEngineTester");

		CESkyBox skybox = CESkyBox.Create(1000, new String[] { "skyBox/top.png", "skyBox/bottom.png",
				"skyBox/front.png", "skyBox/back.png", "skyBox/right.png", "skyBox/left.png" });
		this.setSkyBox(skybox);

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

		label2.Position().y = 1000;
		label2.getControlPoint().x = 0;
		label2.getControlPoint().y = 1f;
		this.add(label2, 0);
		label.getControlPoint().x = 0;
		label.getControlPoint().y = 1;
		label.Position().x = 300;
		label.Position().y = 500;
		this.add(label, 4);
		label.setCamera(Cam);
		// this.add(sprite2, 2);
		this.add(sprite3);
		sprite3.setCamera(Cam);
		FPSCounter.getControlPoint().y = 0;
		FPSCounter.getControlPoint().x = 0;
		this.add(FPSCounter);
		sprite3.add(sprite2);

		sprite2.Position().x = 50;
		sprite2.setCamera(Cam);

	}

	CESchedule mainLoop;

}