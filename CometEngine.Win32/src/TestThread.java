import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestThread extends Thread{
	private long window;
	public TestThread(long Window)
	{
		window = Window;
	}
	public void renderTest()
	{
		
	}
	public void run()
	{
		while (GLFW.glfwWindowShouldClose(window) == GLFW.GLFW_FALSE)
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BITS);
			GL11.glClearColor(1, 0, 0, 1);
			GLFW.glfwPollEvents();
		}
		renderTest();
	}
}
