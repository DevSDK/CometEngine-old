import java.util.Timer;
import java.util.TimerTask;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBound2D;
import com.CometEngine.CELib.BoundBox.CEBoundBox;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESprited2D;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Event.CEEventMouse;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Scheduler.CESchedule;
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

	@Override
	protected void onEnter() {
		super.onEnter();
	}

	@Override
	protected void onExit() {
		super.onExit();
	}

	public void tick() {
		timer++;
		CEPosition2D pos = sprite.Position();

		sprite.setAngle(sprite.getAngle() + 0.1f);
		sprite.Position().x += 0.1f;
		sprite.Position().y += 0.1f;

		label.setString("Tick :  " + timer);
		FPSCounter.setString("FPS : " + CometEngine.getInstance().getRenderer().getFPS());
		CEPosition2D pos2 = sprite2.Position();
		sprite2.setAngle(sprite2.getAngle() + 0.0f);

		pos2.x += 0.02f;
		pos2.y += 0.02f;

		CEPosition2D pos3 = sprite3.Position();
		sprite3.setAngle(sprite3.getAngle() - 0.02f);
		sprite3.Position().x += 0.5f;
		sprite3.Position().y += 0.5f;
	}

	public TestScene() {

		sprite = new CESprited2D("0.png");

		sprite2 = new CESprited2D("1.png");
		sprite3 = new CESprited2D("2.png");
		label = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, false, "Timer");
		label2 = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, true, "Comet Engine Tester",
				"DSM !", "CometEngineTester", "CometEngineTester", "CometEngineTester");

		CEEventListenerKeyboard listener = CEEventListenerKeyboard.Create(this);

		listener.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {

			@Override
			public void KeyBoardEvent(CEEventKeyboard event) {
				if (event.isKeyPush(CEKeyBoard.CE_KEY_UP, CEKeyBoard.CE_KEY_STATUS_REPEAT)) {
					label.Position().y += 3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_DOWN, CEKeyBoard.CE_KEY_STATUS_REPEAT)) {
					label.Position().y += -3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_RIGHT, CEKeyBoard.CE_KEY_STATUS_REPEAT)) {
					label.Position().x += 3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_LEFT, CEKeyBoard.CE_KEY_STATUS_REPEAT)) {
					label.Position().x += -3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_1, CEKeyBoard.CE_KEY_STATUS_PRESS)) {

					System.out.println("This event Test 2");
					CometEngine.getInstance().getSceneManager().setScene(BeforeScene);
				}

			}
		};

		CEEventDispatcher.getInstance().addEventListener(listener, this);

		CEEventListenerMouse mouse = CEEventListenerMouse.Create(this);
		mouse.MouseClickCallBack = new CEEventListenerMouse.CEMouseClick() {
			@Override
			public void invoke(int Button, int Status, double XPos, double YPos, CEEventMouse event) {
				System.out.println("Button : " + Button + " Status : " + Status + " XPos: " + XPos + " YPos: " + YPos);
				if (Status == CEMouse.MOUSE_DOWN)
					if (CEBoundBox.getBoundingBoxAABB(sprite2).isContainPoint((float) XPos, (float) YPos)) {
						System.out.println("YEHAA");

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

		FPSCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, false, "FPS : ");
		CESchedule mainLoop = CESchedule.CreateScheduleRepeat(new CESchedule.CHEDULERFUNC() {

			@Override
			public void invoke(float delta) {
				tick();
			}
		}, 0, 1);
		thisscene.StartSchedule(mainLoop);

		CEEventDispatcher.getInstance().addEventListener(mouse, this);
		label2.Position().y = 1000;
		label2.getControlPoint().x = 0;
		label2.getControlPoint().y = 1f;
		this.add(label2, 0);
		label.getControlPoint().x = 0;
		label.getControlPoint().y = 1;
		label.Position().x = 300;
		label.Position().y = 500;
		this.add(label, 4);

	//	this.add(sprite2, 2);
		this.add(sprite3);
		FPSCounter.getControlPoint().y = 0;
		FPSCounter.getControlPoint().x = 0;
		this.add(FPSCounter);
		sprite.add(sprite2);
		sprite2.Position().x = 50;
		
	}

}