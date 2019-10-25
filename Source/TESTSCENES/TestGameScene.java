
package TESTSCENES;

import com.CometEngine.CELib.BoundBox.CEAABB2D;
import com.CometEngine.CELib.BoundBox.CEBoundBox2D;
import com.CometEngine.CELib.Button.CEButton;
import com.CometEngine.CELib.Button.CEImageButton;
import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.CELib.Scheduler.CESchedule;
import com.CometEngine.CELib.Sprite.CESprite2D;
import com.CometEngine.CELib.Text.CEBMPTextLabel;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Device.CETouchPad;
import com.CometEngine.CometEngine;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListener;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Event.CEEventListenerMouse;
import com.CometEngine.Event.CEEventListenerTouch;
import com.CometEngine.Event.CEEventMouse;
import com.CometEngine.Event.CEEventTouch;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Renderer.CERenderer;
import com.CometEngine.Util.Meth.CEFloat3D;
import java.util.ArrayList;
import java.util.Random;

public class TestGameScene extends CEScene {
	final CESchedule JumpSc = CESchedule.CreateScheduleOnce(new CESchedule.CHEDULERFUNC() {

		public void invoke(float arg0) {
			mJummper.setAngle(0.0f);
			mYDirection = GRAVITY;
		}
	}, 420);;

	public TestGameScene() {

		mFpsCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 1.0f, false, "FPS");
		mFpsCounter.setColor(CEColor4f.RED);
		mFpsCounter.getControlPoint().y = 0.0f;
		mFpsCounter.getControlPoint().x = 0.0f;
		
		mFpsCounter.getPosition().x = 0.0f;
		mFpsCounter.getPosition().y = 0.0f;

		add(mFpsCounter, 1000);
		initSprite();
		initEvent();

		mBG_Updater = CESchedule.CreateScheduleRepeat(new CESchedule.CHEDULERFUNC() {

			public void invoke(float arg0) {
				Update();
			}
		}, 0, 10);
		StartSchedule(mBG_Updater);
	}

	private void initSprite() {
		TimerDisplay = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.4f, false, "SCORE(TIME) : 0");
		TimerDisplay.setColor(CEColor4f.BLACK);
		TimerDisplay.getControlPoint().y = 1.0f;
		TimerDisplay.getControlPoint().x = 0.0f;
		TimerDisplay.getPosition().y = 1080.0f;
		this.add(TimerDisplay, 100);

		CESprite2D b1 = CESprite2D.Create("BackgroundImage/BG_DAY_1.png");
		CESprite2D b2 = CESprite2D.Create("BackgroundImage/BG_DAY_2.png");
		b1.getControlPoint().x = 0.0f;
		b1.getControlPoint().y = 0.0f;
		b2.getControlPoint().x = 0.0f;
		b2.getControlPoint().y = 0.0f;
		b2.getPosition().x = 1920.0f;

		mBG_ImageQueue.add(b1);
		mBG_ImageQueue.add(b2);

		this.add(b1);
		this.add(b2);

		CESprite2D g1 = CESprite2D.Create("Images/Ground_Day_1.png");
		CESprite2D g2 = CESprite2D.Create("Images/Ground_Day_2.png");
		g1.getControlPoint().x = 0.0f;
		g1.getControlPoint().y = 0.0f;
		g2.getControlPoint().x = 0.0f;
		g2.getControlPoint().y = 0.0f;
		g2.getPosition().x = 1920.0f;

		mGroundImageQueue.add(g1);
		mGroundImageQueue.add(g2);
		this.add(g1, 20);
		this.add(g2, 20);

		mJummper.getControlPoint().y = 0.0f;
		mJummper.getControlPoint().x = 1.0f;
		mJummper.getPosition().y = 100.0f;
		mJummper.getPosition().x = 450.0f;
		this.add(mJummper, 20);
		mJummper.getScale().x = 0.15f;
		mJummper.getScale().y = 0.15f;
	}

	private void initEvent() {
		CEEventListenerKeyboard KeyboardEvent = CEEventListenerKeyboard.Create();
		KeyboardEvent.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {

			public void KeyBoardEvent(CEEventKeyboard key) {
				if (key.isKeyPush(32, 1)) {
					Jump();
				}
			}
		};
		CEEventDispatcher.getInstance().addEventListener(KeyboardEvent, this);

		CEEventListenerMouse mouesListener = CEEventListenerMouse.Create(this);
		mouesListener.MouseClickCallBack = new CEEventListenerMouse.CEMouseClick() {

			@Override
			public void invoke(int arg0, int arg1, double arg2, double arg3, CEEventMouse arg4) {
				if (arg1 == CEMouse.MOUSE_DOWN) {
					Jump();
				}
			}
		};

		CEEventDispatcher.getInstance().addEventListener(mouesListener, this);

		CEEventListenerTouch toucheventlsitener = CEEventListenerTouch.Create(this);

		toucheventlsitener.SINGLETOUCH_CALLBACK = new CEEventListenerTouch.CESingleTouchCallBack() {

			@Override
			public void invoke(int arg0, float arg1, float arg2, CEEventTouch arg3) {
				if (arg0 == CETouchPad.CE_TOUCH_DOWN) {
					Jump();
				}
			}
		};

		CEEventDispatcher.getInstance().addEventListener(toucheventlsitener, this);
	}

	private void Jump() {
		TotalJumpCounter++;
		if (TotalJumpCounter <= 2) {
			mYDirection = JUMP;
			mJummper.setAngle(5.0f);
			this.StartSchedule(JumpSc);
		}
	}

	private void Update() {
		if (mBG_ImageQueue.size() <= 0) {
			return;
		}
		UpdateGrounds();
		ProcessesGamePlayer();
		mFpsCounter.setString("FPS:" + CometEngine.getInstance().getRenderer().getFPS());
		updateOBS();
		UpdateTimer();
	}

	private void ProcessesGamePlayer() {
		if (mJummper.getPosition().y > 100.0f || mYDirection != GRAVITY) {
			mJummper.getPosition().y += mYDirection;
		} else {
			TotalJumpCounter = 0;
		}
	}

	private void UpdateGrounds() {
		CESprite2D image;
		int i = 0;
		while (i < mBG_ImageQueue.size()) {
			image = mBG_ImageQueue.get(i);
			image.getPosition().x -= mBG_Speed;
			if (image.getPosition().x <= -1920.0f) {
				image.getPosition().x = 1920.0f;
			}
			i++;
		}
		i = 0;
		while (i < mGroundImageQueue.size()) {
			image = mGroundImageQueue.get(i);
			image.getPosition().x -= mGroundSpeed;
			if (image.getPosition().x <= -1920.0f) {
				image.getPosition().x = 1920.0f;
			}
			i++;
		}
	}

	private void UpdateTimer() {
		if (TimerForSec >= 100) // Do not Edit
		{
			TIMER++;
			TimerDisplay.setString("SCORE(TIME) : " + TIMER);
			TimerForSec = 0;

			if (MAXINUM_SPEED > OBSSPEED) {

				OBSSPEED += (1.0f / (float) MAXIMUMTIME) * (MAXINUM_SPEED - STARTSPEED);

			}

		}
		TimerForSec++;

	}

	private boolean Collision(CESprite2D jumper, CESprite2D obs) {
		return obs.getBoundingBox().getAABB().isContainPoint(jumper.getPosition().x, jumper.getPosition().y);
	}

	private void updateOBS() {
		if (GenTIME <= genTimer) {
			OBSGenerator();
			genTimer = 0;
			GenTIME = getTimer();
		}

		int i = 0;

		while (i < OBSList.size()) {
			OBSList.get(i).getPosition().x -= OBSSPEED;
			if (Collision(mJummper, OBSList.get(i))) {
				GameOver();
			}
			if (OBSList.get(i).getPosition().x <= -500.0f) {
				remove(OBSList.get(i));
				OBSList.remove(OBSList.get(i));
			}
			i++;
		}
		genTimer++;
	}

	private void GameOver() {

		this.PuaseAllSchedule();
		CEBMPTextLabel gameover = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 1.0f, true, "GAME OVER!!");

		gameover.setColor(CEColor4f.BLACK);
		gameover.getPosition().x = 960.0f;
		gameover.getPosition().y = 600.0f;
		this.add(gameover);

		CEImageButton button = CEImageButton.Create("Images/Restart.png", new CEButton.CEButtonCallBack() {

			public void invoke(int arg0) {
				if (arg0 == CEButton.END) {
					Restart();
				}
			}
		});
		button.getScale().x = 0.5f;
		button.getScale().y = 0.5f;
		button.getPosition().x = 960.0f;
		button.getPosition().y = 460.0f;
		this.add(button);
	}

	private void Restart() {
		CometEngine.getInstance().getSceneManager().setScene(new TestGameScene());
	}

	private float getTimer() {
		if (SpawnSpeedPer >= 0.5f) {
			SpawnSpeedPer -= 1.0f / (float) MAXIMUMTIME;
		}
		random.setSeed(System.nanoTime());
		return (float) (300 + random.nextInt(600)) * SpawnSpeedPer;
	}

	private CESprite2D GenSprite() {
		random.setSeed(System.nanoTime());
		int r = random.nextInt(3);
		CESprite2D gen = null;

		switch (r) {
		case 0: {
			gen = CESprite2D.Create("Images/Obs.png");
			gen.getScale().x = 0.5f;
			gen.getScale().y = 0.8f;
			break;
		}
		case 1: {
			gen = CESprite2D.Create("Images/Obs2.png");
			gen.getScale().x = 0.5f;
			gen.getScale().y = 0.8f;
			break;
		}
		case 2: {
			gen = CESprite2D.Create("Images/Obs3.png");
			gen.getScale().x = 0.5f;
			gen.getScale().y = 0.6f;
			break;
		}
		}
		return gen;
	}

	private void OBSGenerator() {
		CESprite2D gen = GenSprite();
		gen.getControlPoint().y = 0.0f;
		gen.getPosition().x = 2000.0f;
		this.add(gen, 0);
		OBSList.add(gen);
	}

	private int TimerForSec = 0;
	private Random random = new Random();
	private int TIMER = 0;
	private int TotalJumpCounter = 0;
	private float SpawnSpeedPer = 1.0f;
	private int genTimer = 0;
	private float GenTIME = 100.0f;
	private final ArrayList<CESprite2D> OBSList = new ArrayList<CESprite2D>();
	private CEBMPTextLabel TimerDisplay = null;

	private final float GRAVITY = -6.0f;
	private final float JUMP = 6.0f;

	private final float MAXINUM_SPEED = 15.0f;
	private final float STARTSPEED = 5.0f;
	private int MAXIMUMTIME = 200; // sec

	private float OBSSPEED = STARTSPEED;
	private float mYDirection = GRAVITY;

	private CESprite2D mJummper = CESprite2D.Create("Images/Main.png");
	private CETextLabel mFpsCounter = null;
	private float mBG_Speed = 1.0f;
	private float mGroundSpeed = 4.0f;
	private CESchedule mBG_Updater;
	private final ArrayList<CESprite2D> mBG_ImageQueue = new ArrayList<CESprite2D>();
	private final ArrayList<CESprite2D> mGroundImageQueue = new ArrayList<CESprite2D>();

}
