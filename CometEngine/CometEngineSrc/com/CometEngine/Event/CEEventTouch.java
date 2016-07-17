package com.CometEngine.Event;

import java.util.ArrayList;

import com.CometEngine.Device.CETouchPad;

public class CEEventTouch extends CEEvent {

	protected int TouchPosSize = 0;
	private boolean isTouched = false;

	protected final TouchData[] Datas = new TouchData[30];
	protected final ArrayList<TouchData> dataList = new ArrayList<TouchData>();

	public void setData(int ID, float x, float y, int status) {
		if (Datas[ID] == null) {
			Datas[ID] = new TouchData();
		}
		Datas[ID].x = x;
		Datas[ID].y = y;
		Datas[ID].status = status;
		Datas[ID].ID = ID;
		if (status == CETouchPad.CE_TOUCH_DOWN && !dataList.contains(Datas[ID])) {
			dataList.add(Datas[ID]);
			System.out.println("Im Add that Index: " + ID + " Status : " + status);
		}
	}

	public static class TouchData {
		int status;
		int ID;
		float x;
		float y;

	}

}
