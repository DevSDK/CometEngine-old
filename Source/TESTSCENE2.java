
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBoundBox;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESprite2D;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Scheduler.CESchedule;
import com.CometEngine.Scheduler.CEScheduler;

public class TESTSCENE2 extends CEScene {

	CESprite2D sprite;

	public void tick() {

	}

	CESchedule scheduless;

	@Override
	protected void onEnter() {
		System.out.println("onINIT SCENE");

	}

	@Override
	protected void onExit() {

		System.out.println("onFINALIZE SCENE");
	}

	boolean flag = false;
	boolean flag2 = false;

	public TESTSCENE2() {
		sprite = new CESprite2D("2.png");
		sprite.Position().x = 500;
		sprite.Position().y = 500;
		this.add(sprite);

		CEEventListenerKeyboard keyboard = CEEventListenerKeyboard.Create();
		CEScene scene = this;
		keyboard.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {
			@Override
			public void KeyBoardEvent(CEEventKeyboard event) {

				if (event.isKeyPush(CEKeyBoard.CE_KEY_UP, CEKeyBoard.CE_KEY_STATUS_REPEAT)) {
					sprite.Position().y += 10;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_ENTER, CEKeyBoard.CE_KEY_STATUS_PRESS)) {
					System.out.println("ENTER");
					flag = !flag;
					if (flag) {
						scene.PuaseAllSchedule();
					} else {
						scene.ResumeAllSchedule();
					}
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_A, CEKeyBoard.CE_KEY_STATUS_PRESS)) {
					flag2 = !flag2;
					if (flag2) {
						scene.PauseSchedule(scheduless);
					} else {
						scene.ResumeSchedule(scheduless);
					}
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_1, CEKeyBoard.CE_KEY_STATUS_PRESS)) {
					TestScene sce;
					System.out.println("This event Test 1");
					CometEngine.getInstance().getSceneManager().setScene(sce = new TestScene());
					sce.BeforeScene = scene;
				}
			}
		};
		CEEventDispatcher.getInstance().addEventListener(keyboard, this);
		CEEventListenerMouse mouse = CEEventListenerMouse.Create(new CEEventListenerMouse.CEMouseMove() {

			@Override
			public void invoke(double XPos, double YPos) {
			
			}
		}, new CEEventListenerMouse.CEMouseClick() {

			@Override
			public void invoke(int Button, int Status, double XPos, double YPos) {
				if (Status == CEMouse.MOUSE_DOWN) {
					if (CEBoundBox.getBoundingBoxAABB(sprite).isContainPoint((float) XPos, (float) YPos)) {
						TestScene sce;
						CometEngine.getInstance().getSceneManager().setScene(sce = new TestScene());
						sce.BeforeScene = scene;
					}
				}
			}
		}, new CEEventListenerMouse.CEMouseScroll() {

			@Override
			public void invoke(double delta) {
				// TODO Auto-generated method stub

			}
		}, sprite);

		CEEventDispatcher.getInstance().addEventListener(mouse, this);

		this.StartSchedule(scheduless = CESchedule.CreateScheduleRepeat(new CESchedule.CHEDULERFUNC() {
			@Override
			public void invoke(float delta) {
				System.out.println("TEST!");
			}
		}, 0, 1000));
		this.StartSchedule(CESchedule.CreateScheduleRepeat(new CESchedule.CHEDULERFUNC() {
			@Override
			public void invoke(float delta) {
				System.out.println("TEST!2 " + delta);
			}
		}, 0, 2000));
	}

}
