package com.CometEngine.Launch;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import com.CometEngine.*;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.DeskTop.CEDeskTop;
import com.CometEngine.DeskTop.CEDeskTopAsyncFileIO;
import com.CometEngine.DeskTop.CEDeskTopFileUtil;
import com.CometEngine.DeskTop.CEDeskTopKeyboard;
import com.CometEngine.DeskTop.CEDeskTopMouseClick;
import com.CometEngine.DeskTop.CEDeskTopMouseMove;
import com.CometEngine.DeskTop.CEDeskTopPlatForm;
import com.CometEngine.DeskTop.CEDeskTopSyncFileIO;
import com.CometEngine.DeskTop.CEDesktopEventThread;
import com.CometEngine.DeskTopGL.CEDeskTopGL;

import TESTSCENES.TESTSCENE2;
import cometengine.config.CELaunchConfig;

public class Run {

	public static void main(String[] argc) {

		CEDeskTop.INIT();
		CometEngine.getInstance().getSceneManager().setScene(CELaunchConfig.CurrentScene);
		CEDeskTop.RUN();
	}

}
