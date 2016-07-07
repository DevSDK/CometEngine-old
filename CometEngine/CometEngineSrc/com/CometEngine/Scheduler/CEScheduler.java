package com.CometEngine.Scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Timer;

public class CEScheduler {

	private final HashMap<CESchedule, Timer> SchedulerTabel = new HashMap<CESchedule, Timer>();

	private void RunSchedule(CESchedule schedule) {
		Timer timer = SchedulerTabel.get(schedule);
		switch (schedule.getType()) {
		case ONCE:
			timer.schedule(schedule.genTimerTask(), schedule.getDelay());
			break;
		case REPEAT:
			timer.schedule(schedule.genTimerTask(), schedule.getDelay(), schedule.getRepeat());
			break;
		default:
			break;
		}
	}

	public void Run(CESchedule schedule) {
		if (SchedulerTabel.containsKey(schedule) == true) {
			SchedulerTabel.get(schedule).cancel();
			SchedulerTabel.put(schedule, new Timer(true));
		} else {
			Timer timer = new Timer(true);
			SchedulerTabel.put(schedule, timer);
		}
		RunSchedule(schedule);
	}

	public void ExitScene() {
		for (CESchedule schedule : SchedulerTabel.keySet()) {
			if (schedule.isExited() == false) {
				SchedulerTabel.get(schedule).cancel();
				SchedulerTabel.get(schedule).purge();
				schedule.ChangeScheduleExitedStatus(true);
			}
		}
	}

	public void EnterScene() {
		for (CESchedule schedule : SchedulerTabel.keySet()) {
			if (schedule.isExited() == true) {
				if (schedule.isPaused() == false) {
					SchedulerTabel.put(schedule, new Timer(true));
					schedule.ChangeScheduleExitedStatus(false);
					RunSchedule(schedule);
				}
			}
		}
	}

	public void PauseAll() {
		for (CESchedule schedule : SchedulerTabel.keySet()) {
			Pause(schedule);
		}
	}

	public void Pause(CESchedule schedule) {
		if (schedule.isPaused() == false) {

			SchedulerTabel.get(schedule).purge();
			SchedulerTabel.get(schedule).cancel();

			schedule.Pause();
		}
	}

	public void Resume(CESchedule schedule) {
		if (schedule.isPaused() == true) {

			schedule.Resume();

			SchedulerTabel.put(schedule, new Timer(true));

			RunSchedule(schedule);
		}
	}

	public void ResumeAll() {
		for (CESchedule schedule : SchedulerTabel.keySet()) {
			Resume(schedule);
		}
	}
}
