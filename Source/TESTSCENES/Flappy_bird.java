
package TESTSCENES;

import com.CometEngine.CELib.Button.CEButton;
import com.CometEngine.CELib.Button.CEImageButton;
import com.CometEngine.CELib.Object.CEColor4f;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scheduler.CESchedule;
import com.CometEngine.CELib.Sprite.CESprite2D;
import com.CometEngine.CELib.Text.CEBMPTextLabel;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.CometEngine;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Device.CETouchPad;
import com.CometEngine.Event.*;
import com.CometEngine.Font.CEBMPFont;

import java.util.ArrayList;
import java.util.Random;

public class Flappy_bird extends CEScene {
    final CESchedule JumpSc = CESchedule.CreateScheduleOnce(new CESchedule.CHEDULERFUNC() {

        public void invoke(float arg0) {
            mBird.setAngle(0.0f);
            mYDirection = GRAVITY;
        }
    }, 250);;

    public Flappy_bird() {

        mFpsCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 1.0f, false, "FPS");
        mFpsCounter.setColor(CEColor4f.RED);
        mFpsCounter.getControlPoint().y = 0.0f;
        mFpsCounter.getControlPoint().x = 0.0f;
        mFpsCounter.getPosition().x = 0.0f;
        mFpsCounter.getPosition().y = 0.0f;
        add(mFpsCounter, 1000);
        initSprites();
        initEvent();

        mBG_Updater = CESchedule.CreateScheduleRepeat(new CESchedule.CHEDULERFUNC() {
            public void invoke(float arg0) {
                Update();
            }
        }, 0, 10);
        StartSchedule(mBG_Updater);
    }

    private void initSprites() {
        TimerDisplay = CETextLabel.CreateBMPText(CEBMPFont.create("font.fnt"), 0.3f, false, "SCORE(TIME) : 0");
        TimerDisplay.setColor(CEColor4f.BLACK);
        TimerDisplay.getControlPoint().y = 1.0f;
        TimerDisplay.getControlPoint().x = 0.0f;
        TimerDisplay.getPosition().y = 1080.0f;
        this.add(TimerDisplay, 100);

        //Background Ansync movement
        CESprite2D b1 = CESprite2D.Create("fbres/background-day.png");
        CESprite2D b2 = CESprite2D.Create("fbres/background-day.png");
        b1.getControlPoint().x = 0.0f;
        b1.getControlPoint().y = 0.0f;
        b2.getControlPoint().x = 0.0f;
        b2.getControlPoint().y = 0.0f;
        b2.getPosition().x = BASE_WIDTH;
        mBG_ImageQueue.add(b1);
        mBG_ImageQueue.add(b2);
        this.add(b1);
        this.add(b2);

        //Ground
        CESprite2D ground1 = CESprite2D.Create("fbres/base.png");
        CESprite2D ground2 = CESprite2D.Create("fbres/base.png");
        ground1.getControlPoint().x = 0.0f;
        ground1.getControlPoint().y = 0.0f;
        ground2.getControlPoint().x = 0.0f;
        ground2.getControlPoint().y = 0.0f;
        ground2.getPosition().x = BASE_WIDTH;

        mGroundImageQueue.add(ground1);
        mGroundImageQueue.add(ground2);
        this.add(ground1, 20);
        this.add(ground2, 20);


        for(int i =0;i<20;i++)
        {
            CESprite2D  pip = CESprite2D.Create("fbres/pipe.png");
            pip.getPosition().x = -1000;
            pip.setActive(false);
            mPipeCollection.add(pip);
            add(pip);
            pip = CESprite2D.Create("fbres/pipe.png");
            pip.getPosition().x = -1000;
            pip.setActive(false);
            mPipeCollection.add(pip);
            add(pip);

        }

        mBird.getControlPoint().y = 0.5f;
        mBird.getControlPoint().x = 1.0f;
        mBird.getPosition().y = 700.0f;
        mBird.getPosition().x = 140.0f;
        CESprite2D black = CESprite2D.Create("fbres/black.png");
        black.getControlPoint().x = 0;
        black.getControlPoint().y = 0.5f;
        black.getPosition().x = 607;
        black.getScale().x = 20;
        black.getScale().y = 20;
        this.add(black, 2000);
        this.add(mBird, 20);

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
                if (arg0 == CETouchPad.CE_TOUCH_DOWN)
                    Jump();
            }
        };

        CEEventDispatcher.getInstance().addEventListener(toucheventlsitener, this);
    }

    private void Jump() {

        mYDirection = JUMP;
        mBird.setAngle(6.0f);
        this.StartSchedule(JumpSc);
    }

    private void Update() {
        if (mBG_ImageQueue.size() <= 0)
            return;
        UpdateGrounds();
        ProcessesGamePlayer();
        mFpsCounter.setString("FPS:" + CometEngine.getInstance().getRenderer().getFPS());
        updateOBS();
        UpdateTimer();
    }
    private void ProcessesGamePlayer() {
        if (mBird.getPosition().y > 100.0f || mYDirection != GRAVITY) {
            mBird.getPosition().y += mYDirection;
        }

        if(mBird.getPosition().y < 200 || mBird.getPosition().y > 1080)
            GameOver();;


    }
    private void UpdateGrounds() {
        CESprite2D image;
        int i = 0;
        while (i < mBG_ImageQueue.size()) {
            image = mBG_ImageQueue.get(i);
            image.getPosition().x -= mBG_Speed;
            if (image.getPosition().x <= -BASE_WIDTH) {
                image.getPosition().x = BASE_WIDTH;
            }
            i++;
        }
        i = 0;
        while (i < mGroundImageQueue.size()) {
            image = mGroundImageQueue.get(i);
            image.getPosition().x -= mGroundSpeed;
            if (image.getPosition().x <= -BASE_WIDTH) {
                image.getPosition().x = BASE_WIDTH;
            }
            i++;
        }
    }

    private void UpdateTimer() {
        if (TimerForSec >= 100)
        {
            TIMER++;
            TimerDisplay.setString("SCORE(TIME) : " + TIMER);
            TimerForSec = 0;
            if (MAXINUM_SPEED > OBSSPEED)
                OBSSPEED += (1.0f / (float) MAXIMUMTIME) * (MAXINUM_SPEED - STARTSPEED);

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
       //     GenTIME = getTimer();
        }
        for (int i = 0; i < mPipesActived.size(); i+=2) {
            mPipesActived.get(i).getPosition().x -= OBSSPEED;
            mPipesActived.get(i+1).getPosition().x -= OBSSPEED;
            if (Collision(mBird, mPipesActived.get(i)) || Collision(mBird,mPipesActived.get(i+1))) {
                GameOver();
            }
            if (mPipesActived.get(i).getPosition().x <= -200.0f) {
                CESprite2D sp = mPipesActived.get(i);
                sp.setActive(false);
                mPipeCollection.add(sp);
                sp = mPipesActived.get(i+1);
                sp.setActive(false);
                mPipesActived.remove(mPipesActived.get(i));
                mPipesActived.remove(sp);


            }
        }
        genTimer++;
    }

    private void GameOver() {
        this.PuaseAllSchedule();
        CESprite2D gameover = CESprite2D.Create("fbres/gameover.png");
        gameover.getScale().x = 1.5f;
        gameover.getScale().y = 1.5f;
        gameover.getPosition().x = 300.0f;
        gameover.getPosition().y = 600.0f;
        this.add(gameover);

        CEImageButton button = CEImageButton.Create("Images/Restart.png", new CEButton.CEButtonCallBack() {
            public void invoke(int arg0) {
                if (arg0 == CEButton.END) {
                    Restart();
                }
            }
        });

        CEEventListenerKeyboard KeyboardEvent = CEEventListenerKeyboard.Create();

        KeyboardEvent.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {
            public void KeyBoardEvent(CEEventKeyboard key) {
                if (key.isKeyPush(32, 1)) {
                    Restart();
                }
            }
        };
        CEEventDispatcher.getInstance().addEventListener(KeyboardEvent, this);


        button.getScale().x = 0.4f;
        button.getScale().y = 0.4f;
        button.getPosition().x = 300.0f;
        button.getPosition().y = 460.0f;
        this.add(button);
    }

    private void Restart() {
        CometEngine.getInstance().getSceneManager().setScene(new Flappy_bird());
    }

    private float getTimer() {
        if (SpawnSpeedPer >= 0.5f) {
            SpawnSpeedPer -= 1.0f / (float) MAXIMUMTIME;
        }
        random.setSeed(System.nanoTime());
        return (float) (300 + random.nextInt(600)) * SpawnSpeedPer;
    }



    private void OBSGenerator() {
        if(mPipeCollection.size()<=1)
        {
            System.out.println("[CE]{FB} Not enough pipes");
            return;
        }
        float height = (float) (Math.random()*500f) + 300;
        CESprite2D gen = mPipeCollection.get(0);
        mPipeCollection.remove(0);
        CESprite2D gen2 = mPipeCollection.get(0);
        mPipeCollection.remove(0);

        gen.setActive(true);
        gen2.setActive(true);

        gen.getControlPoint().y = 1f;
        gen.getPosition().x = 1400.0f;
        gen.getPosition().y = height;


        gen2.getControlPoint().y = 1;
        gen2.getPosition().x=1400.0f;
        gen2.getPosition().y=height+ HOLE_HEIGHT;
        gen2.setAngle(180.0f);

        mPipesActived.add(gen);
        mPipesActived.add(gen2);
    }

    private int TimerForSec = 0;
    private Random random = new Random();
    private int TIMER = 0;
    private float SpawnSpeedPer = 1.0f;
    private int genTimer = 0;
    private float GenTIME = 100.0f;

    private CEBMPTextLabel TimerDisplay = null;

    private final float GRAVITY = -8f;
    private final float JUMP = 8f;

    private final float MAXINUM_SPEED = 15.0f;
    private final float STARTSPEED = 5.0f;
    private int MAXIMUMTIME = 200; // sec

    private float OBSSPEED = STARTSPEED;
    private float mYDirection = GRAVITY;
    private float HOLE_HEIGHT = 200;

    private boolean toggle_debug = false;

    private CESprite2D mBird = CESprite2D.Create("fbres/bird.png");
    private CETextLabel mFpsCounter = null;
    private float mBG_Speed = 1.0f;
    private float mGroundSpeed = 4.0f;
    private CESchedule mBG_Updater;
    private final ArrayList<CESprite2D> mBG_ImageQueue = new ArrayList<CESprite2D>();
    private final ArrayList<CESprite2D> mPipeCollection = new ArrayList<CESprite2D>();
    private final ArrayList<CESprite2D> mPipesActived = new ArrayList<CESprite2D>();
    private final ArrayList<CESprite2D> mGroundImageQueue = new ArrayList<CESprite2D>();
    private final float BASE_WIDTH = 605f;
}
