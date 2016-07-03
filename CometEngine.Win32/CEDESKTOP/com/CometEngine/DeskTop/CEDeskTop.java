package com.CometEngine.DeskTop;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import com.CometEngine.CometEngine;
import com.CometEngine.CometEngineInitObject;
import com.CometEngine.DeskTopGL.CEDeskTopGL;

public class CEDeskTop {
	private static boolean SCENE_RESIZEABLE = false;
	private static int WINDOW_WIDTH = 600;
	private static int WINDOW_HEIGHT = 600;
	private static long WINDOW;
	private static int COORD_WIDTH = 0;
	private static int COORD_HEIGHT = 0;
	private static CEDeskTopKeyboard KeybordCallBack = new CEDeskTopKeyboard();
	private static CEDeskTopMouseClick MouseClickEvent = new CEDeskTopMouseClick();
	private static CEDeskTopMouseMove MouseMoveEvent = new CEDeskTopMouseMove();

	public static int getFrameWidth() {
		return WINDOW_WIDTH;
	}

	public static int getFrameHeight() {
		return WINDOW_HEIGHT;
	}

	private static void LoadLWJGL() {
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

		if (GLFW.glfwInit() != GLFW.GLFW_TRUE)
			throw new IllegalStateException("GLFW INIT FALL");

		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);

		if (SCENE_RESIZEABLE)
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		else
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

		WINDOW = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "CometEngine Tester", MemoryUtil.NULL,
				MemoryUtil.NULL);
		if (WINDOW == MemoryUtil.NULL)
			throw new RuntimeException("Failed Create GLFW WINDWO");

		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetKeyCallback(WINDOW, KeybordCallBack);
		GLFW.glfwSetMouseButtonCallback(WINDOW, MouseClickEvent);
		GLFW.glfwSetCursorPosCallback(WINDOW, MouseMoveEvent);

		GLFW.glfwSetWindowPos(WINDOW, vidmode.width() / 2, vidmode.height() / 2);
		GLFW.glfwMakeContextCurrent(WINDOW);
		GLFW.glfwShowWindow(WINDOW);
		GL.createCapabilities();
	}

	
	public static void Run(int FrameWidth, int FrameHeight, int XcoordSize, int YCoordSize) {

		WINDOW_HEIGHT = FrameHeight;
		WINDOW_WIDTH = FrameWidth;
		COORD_HEIGHT = YCoordSize;
		COORD_WIDTH = XcoordSize;
		LoadLWJGL();


		CometEngineInitObject init = new CometEngineInitObject();
		init.GL = new CEDeskTopGL();
		init.platformFileUtil = new CEDeskTopFileUtil();

		CometEngine.getInstance().Run(CometEngine.PLATFORM.CE_WIN32, init);
		CometEngine.getInstance().getRenderer().setViewSize(COORD_WIDTH, COORD_HEIGHT);

		CEDesktopEventThread thread = new CEDesktopEventThread();
		thread.start();

		GLFW.glfwGetJoystickName(0);
		GLFW.glfwSwapInterval(1);
		while (GLFW.glfwWindowShouldClose(WINDOW) == GLFW.GLFW_FALSE) {
			CometEngine.getInstance().getRenderer().RenderingCommands();

			GLFW.glfwSwapBuffers(WINDOW);

			CometEngine.getInstance().setPauseEvent(true);
			GLFW.glfwPollEvents();
			CometEngine.getInstance().setPauseEvent(false);
			;

		}
		CometEngine.getInstance().ExitCometEngine();
	}
}