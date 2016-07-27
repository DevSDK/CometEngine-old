package com.platform.cometengine.io;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;

import android.os.AsyncTask;

public class CEAndroidEvnetTask extends AsyncTask<Void, Void, Void> {

	boolean flag = true;
	String[] paths = { "1" };

	public void run() {

		init();
		loop();
	}

	public void init() {
		CESceneManager.getInstance().setScene(new CEScene());

	}

	int n = 0;

	public void loop() {
		while (CometEngine.getInstance().isRun() && flag) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}

		}

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}

	@Override
	protected void onPostExecute(Void r) {
		super.onPostExecute(null);

	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		flag = false;
	}

	@Override
	protected Void doInBackground(Void... params) {

		run();
		return null;
	}

}
