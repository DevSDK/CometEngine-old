import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESprite2D;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Util.Meth.CEPosition2D;

public class TestScene extends CEScene{
	CETextLabel label;
	CETextLabel label2;
	CEObject sprite;
	CESprite2D sprite2;
	CESprite2D sprite3;
	CETextLabel FPSCounter;

	int timer = 0;

	@Override
	public void tick() {
		timer++;
		CEPosition2D pos = sprite.getPosition();

		sprite.setAngle(sprite.getAngle() + 0.1f);
		sprite.getPosition().x += 0.5f;
		sprite.getPosition().y += 0.5f;

		label.setString("Tick :  " + timer);
		FPSCounter.setString("FPS : " + CometEngine.getInstance().getRenderer().getFPS());
		CEPosition2D pos2 = sprite2.getPosition();
		sprite2.setAngle(sprite2.getAngle() + 0.0f);

		pos2.x += 0.02f;
		pos2.y += 0.02f;

		CEPosition2D pos3 = sprite3.getPosition();
		sprite3.setAngle(sprite3.getAngle() - 0.02f);
		sprite3.getPosition().x += 0.5f;
		sprite3.getPosition().y += 0.5f;
	}
	public TestScene() {
		sprite = new CESprite2D("0.png");

		sprite2 = new CESprite2D("1.png");
		sprite3 = new CESprite2D("2.png");
		label = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, false, "Timer");
		label2 = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 0.8f, true, "Comet Engine Tester",
				"DSM !", "CometEngineTester", "CometEngineTester", "CometEngineTester");

		CEEventListenerKeyboard listener = CEEventListenerKeyboard.Create(this);

		listener.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {

			@Override
			public void KeyBoardEvent(CEEventKeyboard event) {

				if (event.isKeyPush(CEKeyBoard.CE_KEY_UP, CEKeyBoard.CE_KEY_REPEAT)) {
					label.getPosition().y += 3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_DOWN, CEKeyBoard.CE_KEY_REPEAT)) {
					label.getPosition().y += -3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_RIGHT, CEKeyBoard.CE_KEY_REPEAT)) {
					label.getPosition().x += 3;
				}
				if (event.isKeyPush(CEKeyBoard.CE_KEY_LEFT, CEKeyBoard.CE_KEY_REPEAT)) {
					label.getPosition().x += -3;
				}

			}
		};

		CEEventDispatcher.getInstance().addEventListener(listener, this);

		CEEventListenerMouse mouse = CEEventListenerMouse.Create(this);
		mouse.MouseClickCallBack = new CEEventListenerMouse.CEMouseClick() {
			@Override
			public void invoke(int Button, int Status, double XPos, double YPos) {
				System.out.println("Button : " + Button + " Status : " + Status + " XPos: " + XPos + " YPos: " + YPos);
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
				System.out.println("DELTA : " + delta);
			}
		};

		CEEventDispatcher.getInstance().addEventListener(mouse, this);
		label2.getPosition().y = 1000;
		label2.getControlPoint().x = 0;
		label2.getControlPoint().y = 1f;
		this.add(label2, 0);
		label.getControlPoint().x = 0;
		label.getControlPoint().y = 1;
		label.getPosition().x = 300;
		label.getPosition().y = 500;
		this.add(label, 4);

		this.add(sprite2, 2);
		this.add(sprite3);
		FPSCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, false, "FPS : ");
		FPSCounter.getControlPoint().y = 0;
		FPSCounter.getControlPoint().x = 0;
		this.add(FPSCounter);
	}
	
}