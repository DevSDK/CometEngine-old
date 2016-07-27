package TESTSCENES;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.BoundBox.CEBoundBox;
import com.CometEngine.CELib.Button.CEButton;
import com.CometEngine.CELib.Button.CEButton.CEButtonCallBack;
import com.CometEngine.CELib.Button.CEImageButton;
import com.CometEngine.CELib.Button.CEPickableObject;
import com.CometEngine.CELib.Button.CETextButton;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scheduler.CESchedule;
import com.CometEngine.CELib.Scheduler.CEScheduler;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.CELib.model3d.CEModel;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Event.CEEventListenerTouch;
import com.CometEngine.Event.CEEventMouse;
import com.CometEngine.Event.CEEventTouch;
import com.CometEngine.Event.CEEventTouch.TouchData;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Font.CEFont;
import com.CometEngine.Resrouce.CEModelLoader;
import com.CometEngine.Resrouce.CEModelResource;

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
	CETextButton button2;

	public TESTSCENE2() {
		button = CEImageButton.Create("button.png", new CEButton.CEButtonCallBack() {
			@Override
			public void invoke(int status) {
				if (status == 0) {
					TestScene scene;
					CometEngine.getInstance().getSceneManager().PushScene(new TestScene());
				}
			}
		});
		button.getScale().x = 0.3f;
		button.getScale().y = 0.3f;

		button.Position().x = 500;
		button.Position().y = 500;
		this.add(button);

		button2 = CETextButton.Create(CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 1, true, "TEST"),
				new CEButtonCallBack() {

					@Override
					public void invoke(int status) {
						if (status == 0) {
							CEModelLoader.getInstance().LoadModel("ObjTest/origin/untitled.obj",
									new CEModelResource(""));
						}
					}
				});

		CEEventListenerTouch listener = new CEEventListenerTouch();
		listener.MULTITOUCH_CALLBACK = new CEEventListenerTouch.CEMultiTouchCallBack() {

			@Override
			public void invoke(ArrayList<TouchData> touchs) {
				System.out.println("Touch Size " + touchs.size());
			}
		};
		listener.SINGLETOUCH_CALLBACK = new CEEventListenerTouch.CESingleTouchCallBack() {
			@Override
			public void invoke(int status, float x, float y, CEEventTouch event) {
				System.err.println("Status " + status + " x " + x + " y " + y);
			}
		};
		CEEventDispatcher.getInstance().addEventListener(listener, this);

		button2.setColor(1, 1, 0);
		button2.setOpacity(0.5f);
		button2.Position().x = 500;
		button2.Position().y = 300;
		this.add(button2);

		CEEventListenerMouse mli = CEEventListenerMouse.Create(this);
		mli.MouseClickCallBack = new CEEventListenerMouse.CEMouseClick() {

			@Override
			public void invoke(int Button, int Status, double XPos, double YPos, CEEventMouse event) {

			}
		};
		CEEventDispatcher.getInstance().addEventListener(mli, this);
	}

}