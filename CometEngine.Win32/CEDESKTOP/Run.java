

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
import org.newdawn.slick.command.Control;

import com.CometEngine.*;
import com.CometEngine.DeskTop.CEDeskTopAsyncFileIO;
import com.CometEngine.DeskTop.CEDeskTopFileUtil;
import com.CometEngine.DeskTop.CEDeskTopKeyboard;
import com.CometEngine.DeskTop.CEDeskTopPlatForm;
import com.CometEngine.DeskTop.CEDeskTopSyncFileIO;
import com.CometEngine.DeskTop.CEDesktopEventThread;
import com.CometEngine.DeskTopGL.CEDeskTopGL;

public class Run {

	private static boolean SCENE_RESIZEABLE = false;
	private static int		WINDOW_WIDTH  = 1000;
	private static int		WINDOW_HEIGHT  = 1000;
	private static long	WINDOW;
	private static CEDeskTopKeyboard KeybordCallBack = new CEDeskTopKeyboard();
	public static void LoadLWJGL()
	{
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
		
		if(GLFW.glfwInit() != GLFW.GLFW_TRUE)
			throw new IllegalStateException("GLFW INIT FALL");
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		
		if(SCENE_RESIZEABLE)
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		else
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		
		WINDOW = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "CometEngine Tester", MemoryUtil.NULL, MemoryUtil.NULL);
		if(WINDOW == MemoryUtil.NULL)
			throw new RuntimeException("Failed Create GLFW WINDWO");
	
		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetKeyCallback(WINDOW, KeybordCallBack);
		GLFW.glfwSetWindowPos(WINDOW, vidmode.width() / 2,vidmode.height()/2);
		GLFW.glfwMakeContextCurrent(WINDOW);
		GLFW.glfwShowWindow(WINDOW);
		GL.createCapabilities();	
	}
	
	
	public static void main(String [] argc)
	{
		LoadLWJGL();
		
		CometEngineInitObject init = new CometEngineInitObject();
		init.GL = new CEDeskTopGL();
		init.platformFileUtil = new CEDeskTopFileUtil();
		
		CometEngine.getInstance().Run(CometEngine.PLATFORM.CE_WIN32, init);
		CometEngine.getInstance().getRenderer().setViewSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		CEDesktopEventThread thread = new CEDesktopEventThread();
		thread.start();
		
		
		
		GLFW.glfwGetJoystickName(0);
		GLFW.glfwSwapInterval(1);
		while (GLFW.glfwWindowShouldClose(WINDOW) == GLFW.GLFW_FALSE)
		{
			CometEngine.getInstance().getRenderer().RenderingCommands();
			
			GLFW.glfwSwapBuffers(WINDOW);

			
			CometEngine.getInstance().setPauseEvent(true); 
			
			GLFW.glfwPollEvents();
			CometEngine.getInstance().setPauseEvent(false);;
			
			
		}
			CometEngine.getInstance().ExitCometEngine();
	}
}
	
 
