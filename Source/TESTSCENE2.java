
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBoundBox;
import com.CometEngine.CELib.Button.CEButton;
import com.CometEngine.CELib.Button.CEImageButton;
import com.CometEngine.CELib.Button.CEPickableObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Scheduler.CESchedule;
import com.CometEngine.Scheduler.CEScheduler;

public class TESTSCENE2 extends CEScene {
	CEScene SCENE = this;

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
	CEImageButton button;

	public TESTSCENE2() {
		button = new CEImageButton("button.png", new CEButton.CEButtonCallBack() {
			@Override
			public void invoke(int status) {
				System.out.println("STATUS = " + status);
				if (status == 0) {
					TestScene scene;
					CometEngine.getInstance().getSceneManager().setScene(scene = new TestScene());
					scene.BeforeScene = SCENE;
				}
			}
		});
		button.getScale().x = 0.3f;
		button.getScale().y = 0.3f;

		button.Position().x = 500;
		button.Position().y = 500;
		this.add(button);
	}
}