package com.CometEngine.Launch;



import com.CometEngine.*;
import com.CometEngine.DeskTop.CEDeskTop;

import cometengine.config.CELaunchConfig;

public class Run {

	public static void main(String[] argc) {

		CEDeskTop.INIT();
		CometEngine.getInstance().getSceneManager().setScene(CELaunchConfig.CurrentScene);
		CEDeskTop.RUN();
	}

}
