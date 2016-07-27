package com.CometEngine.CELib.Scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class CESchedule {

	private boolean isPaused = false;
	private boolean isExit = false;
	private boolean isRemovedSchedule = false;

	private long LastTime = 0;

	private CHEDULERFUNC func = null;
	private SCHEDULETYPE type = SCHEDULETYPE.NULL;
	private int Delay = 0;
	private int Repeat = 0;

	private CESchedule() {

	}

	public TimerTask genTimerTask() {
		return new TimerTask() {

			@Override
			public void run() {

				long startTime = System.currentTimeMillis();
				func.invoke(LastTime / 1000);
				LastTime = System.currentTimeMillis() - startTime;

			}
		};
	}

	public static CESchedule CreateScheduleOnce(CHEDULERFUNC func, int Delay) {
		CESchedule sch = new CESchedule();
		sch.func = func;
		sch.Delay = Delay;
		sch.type = SCHEDULETYPE.ONCE;
		return sch;
	}

	public static CESchedule CreateScheduleRepeat(CHEDULERFUNC func, int Delay, int Repeat) {
		CESchedule sch = new CESchedule();
		sch.func = func;
		sch.Delay = Delay;
		sch.Repeat = Repeat;
		sch.type = SCHEDULETYPE.REPEAT;
		return sch;
	}

	public void ChangeScheduleExitedStatus(boolean status) {
		isExit = status;
	}

	public boolean isExited() {
		return isExit;
	}

	public interface CHEDULERFUNC {
		public void invoke(float delta);
	}

	public int getDelay() {
		return Delay;
	}

	public int getRepeat() {
		return Repeat;
	}

	public SCHEDULETYPE getType() {
		return type;
	}

	protected void Pause() {
		if (!isPaused) {
			isPaused = true;
			
		}

	}

	protected void Resume() {
		if (isPaused) {
			isPaused = false;

		}
	}

	public boolean isPaused() {
		return isPaused;
	}

	protected enum SCHEDULETYPE {
		NULL, ONCE, REPEAT
	}

}
