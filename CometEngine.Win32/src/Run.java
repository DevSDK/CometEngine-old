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
import com.CometEngine.CometEngine;

public class Run {

	private static boolean SCENE_RESIZEABLE = false;
	private static int		WINDOW_WIDTH  = 1000;
	private static int		WINDOW_HEIGHT  = 1000;
	private static long	WINDOW;

	

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
		
		WINDOW = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "SHADER TEST", MemoryUtil.NULL, MemoryUtil.NULL);
		if(WINDOW == MemoryUtil.NULL)
			throw new RuntimeException("Failed Create GLFW WINDWO");
	
		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
		GLFW.glfwSetWindowPos(WINDOW, vidmode.width() / 2,vidmode.height()/2);
		GLFW.glfwMakeContextCurrent(WINDOW);
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(WINDOW);
		GL.createCapabilities();
		
	}
	

	
	
	public static void main(String [] argc)
	{
		
		
		LoadLWJGL();
		CometEngine.getInstece().Run(CometEngine.PLATFORM.CE_WIN32);
		while (GLFW.glfwWindowShouldClose(WINDOW) == GLFW.GLFW_FALSE)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(1, 0, 0, 1);
			
			CometEngine.getInstece().TESTER_RENDERER_SHOUD_BE_DISTORY();
			
			GLFW.glfwSwapBuffers(WINDOW);
			GLFW.glfwPollEvents();
		}
		
	}
}
	
 
