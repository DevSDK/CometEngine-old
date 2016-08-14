package TESTSCENES;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scheduler.CESchedule;
import com.CometEngine.CELib.Sprite.CESprite2D;
import com.CometEngine.CELib.Text.CEBMPTextLabel;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Font.CEBMPFont;

public class TestGameScene extends CEScene {

	public TestGameScene() {

		mFpsCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 1, false, "FPS");
		mFpsCounter.getControlPoint().y = 0;
		mFpsCounter.getControlPoint().x = 0;
		mFpsCounter.getPosition().x = 0;
		mFpsCounter.getPosition().y = 0;
		this.add(mFpsCounter, 1000);

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

		TimerDisplay = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 1, false, "Time : ");
		TimerDisplay.getControlPoint().y = 1f;
		TimerDisplay.getControlPoint().x = 0;
		TimerDisplay.getPosition().y = 1080;
		this.add(TimerDisplay, 100);
		
		CESprite2D b1 = CESprite2D.Create("BackgroundImage/BG_DAY_1.png");
		CESprite2D b2 = CESprite2D.Create("BackgroundImage/BG_DAY_2.png");
		b1.getControlPoint().x = 0;
		b1.getControlPoint().y = 0;
		b2.getControlPoint().x = 0;
		b2.getControlPoint().y = 0;
		b2.getPosition().x = 1920;
		mBG_ImageQueue.add(b1);
		mBG_ImageQueue.add(b2);
		this.add(b1);
		this.add(b2);
		CESprite2D g1 = CESprite2D.Create("Images/Ground_Day_1.png");
		CESprite2D g2 = CESprite2D.Create("Images/Ground_Day_2.png");
		g1.getControlPoint().x = 0;
		g1.getControlPoint().y = 0;
		g2.getControlPoint().x = 0;
		g2.getControlPoint().y = 0;
		g2.getPosition().x = 1920;

		mGroundImageQueue.add(g1);
		mGroundImageQueue.add(g2);
		this.add(g1, 10);
		this.add(g2, 10);

		mJummper.getControlPoint().y = 0;
		mJummper.getControlPoint().x = 1f;
		mJummper.getPosition().y = 100;
		mJummper.getPosition().x = 450;

		this.add(mJummper);
		mJummper.getScale().x = 0.15f;
		mJummper.getScale().y = 0.15f;

	}

	private void initEvent() {
		CEEventListenerKeyboard KeyboardEvent = CEEventListenerKeyboard.Create();

		KeyboardEvent.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {

			public void KeyBoardEvent(CEEventKeyboard key) {
				if (key.isKeyPush(CEKeyBoard.CE_KEY_SPACE, CEKeyBoard.CE_KEY_STATUS_PRESS)) {
					Jump();
				}
			}
		};
		CEEventDispatcher.getInstance().addEventListener(KeyboardEvent, this);

	}

	private void Jump() {

		final CEScene scene = this;
		TotalJumpCounter++;
		if ((TotalJumpCounter <= 2)) {
			mYDirection = 4;

			mJummper.setAngle(5);

			scene.StartSchedule(JumpSc);

		}
	}

	private void Update() {
		if (mBG_ImageQueue.size() <= 0)
			return;
		for (int i = 0; i < mBG_ImageQueue.size(); i++) {
			CESprite2D image = mBG_ImageQueue.get(i);
			image.getPosition().x -= mBG_Speed;
			if (image.getPosition().x <= -1920) {
				image.getPosition().x = 1920;
			}
		}
		for (int i = 0; i < mGroundImageQueue.size(); i++) {
			CESprite2D image = mGroundImageQueue.get(i);
			image.getPosition().x -= mGroundSpeed;
			if (image.getPosition().x <= -1920) {
				image.getPosition().x = 1920;
			}
		}

		if (!(mJummper.getPosition().y <= 100 && mYDirection == GRAVITY)) {
			mJummper.getPosition().y += mYDirection;
		} else {
			TotalJumpCounter = 0;
		}
		// mJummper.setAngle(mJummper.getAngle() +0.02f);

		mFpsCounter.setString("FPS:" + CometEngine.getInstance().getRenderer().getFPS());
		updateOBS();

	}

	private boolean Collision(CESprite2D jumper, CESprite2D obs) {
		return obs.getBoundingBox().getAABB().isContainPoint(jumper.getPosition().x, jumper.getPosition().y);
	}

	private void updateOBS() {

		if (TIME <= genTimer) {
			OBSGenerator();
			genTimer = 0;
			TIME = getTimer();
		}
		for (int i = 0; i < OBSList.size(); i++) {
			OBSList.get(i).getPosition().x -= OBSSPEED;

			if (Collision(mJummper, OBSList.get(i))) {
				System.out.println("COLLISION!");
			}

			if (OBSList.get(i).getPosition().x <= -500) {
				this.remove(OBSList.get(i));
				OBSList.remove(OBSList.get(i));
			}
		}
		genTimer++;
	}

	private int getTimer() {

		random.setSeed(System.nanoTime());
		return 100 + random.nextInt(600);
	}

	private void OBSGenerator() {
		CESprite2D gen = CESprite2D.Create("Images/Obs.png");
		gen.getControlPoint().y = 0;
		gen.getScale().x = 0.5f;
		gen.getScale().y = 0.8f;
		gen.getPosition().x = 2000;
		this.add(gen);
		OBSList.add(gen);
	}

	final CESchedule JumpSc = CESchedule.CreateScheduleOnce(new CESchedule.CHEDULERFUNC() {
		public void invoke(float arg0) {

			mJummper.setAngle(0);

			mJummper.getControlPoint().y = 0f;
			mYDirection = GRAVITY;
		}
	}, 500);

	private Random random = new Random();

	private int OBSSPEED = 10;
	private int TotalJumpCounter = 0;
	private boolean doublejumpable = false;
	private int genTimer = 0;
	private int TIME = 100;

	private final ArrayList<CESprite2D> OBSList = new ArrayList<CESprite2D>();
	// config

	private CEBMPTextLabel TimerDisplay = null;
	private final float GRAVITY = -4.8f;
	private float mYDirection = GRAVITY;
	private CESprite2D mJummper = CESprite2D.Create("Images/Main.png");
	private CETextLabel mFpsCounter = null;
	private float mBG_Speed = 5;
	private float mGroundSpeed = 2;
	private CESchedule mBG_Updater = null;
	private final ArrayList<CESprite2D> mBG_ImageQueue = new ArrayList<CESprite2D>();
	private final ArrayList<CESprite2D> mGroundImageQueue = new ArrayList<CESprite2D>();
}
