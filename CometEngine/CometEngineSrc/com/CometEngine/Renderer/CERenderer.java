package com.CometEngine.Renderer;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Commend.Manager.CERenderCommandManager;
import com.CometEngine.Renderer.Texture.TextureManager.CETextureManager;
import com.CometEngine.Tester.Tester;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Meth.CESize;

public class CERenderer {
	public enum RENDERER_TYPE {
		CE_RENDERER_NULL, CE_RENDERER_GL, CE_RENDERER_GLES
	}

	private double Timer = 0;
	private int RenderFPS = 0;

	public CERenderer(RENDERER_TYPE target, CEGLInterface gl) {

		if (CEGL.init(gl) == true) {
			m_RendererType = target;
		} else {
			System.err.println("CEGL INIT ERROR");
		}
	}

	public int getRenderWidth() {
		return Renderer_Weidth;
	}

	public int getRenderHeight() {
		return Renderer_Height;
	}

	public int getFPS() {
		return RenderFPS;
	}

	public void init() {

	}

	public void AddRenderCommend(CERenderCommand render) {
		m_RenderingQue.add(render);
	}

	public RENDERER_TYPE getType() {
		return m_RendererType;
	}

	public void GL_CLEAR() {
		CEGL.Clear(CEGL.GL_DEPTH_BUFFER_BIT | CEGL.GL_COLOR_BUFFER_BIT);
		CEGL.Enable(CEGL.GL_BLEND);
		CEGL.BlendFunc(CEGL.GL_SRC_ALPHA, CEGL.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void VisitRenderTarget() {
		CEScene scene = CometEngine.getInstance().getSceneManager().getCurrentScene();
		CERenderCommandCustom command = null;
		if (scene != null) {
			command = scene.genRenderCommand();
			if (command != null)
				command.execute();
		}

		if (command != null)
			CERenderCommandManager.getInstence().AddCommand(command);
	}

	private int Frame = 0;

	private void CalculateFPS() {
		Frame++;
		startTime = System.currentTimeMillis();

		if (startTime - EndTime != 0) {
			RenderFPS = (int) (Frame * 1000 / (startTime - EndTime));
		}

		if (startTime - EndTime > 1000) {
			EndTime = startTime;
			Frame = 0;
		}
	}

	private long EndTime = 0;
	private long startTime = 0;

	public void RenderingCommands() {

		if (CEGLResourceManager.getInstence().isLoadeingListEmpty() == false)
			CEGLResourceManager.getInstence().LoadUPGLResrouce();

		GL_CLEAR();
		CEScene scene = CometEngine.getInstance().getSceneManager().getCurrentScene();
		CERenderCommandCustom command = null;
		if (scene != null) {
			command = scene.genRenderCommand();
			if (command != null)
				command.execute();
		}

		if (command != null)
			command.execute();

		CalculateFPS();
	}

	public void setViewSize(int width, int height) {
		this.Renderer_Weidth = width;
		this.Renderer_Height = height;
	}

	public CESize getViewSize() {
		return new CESize(Renderer_Weidth, Renderer_Height);
	}

	private int Renderer_Height = 0;
	private int Renderer_Weidth = 0;
	private RENDERER_TYPE m_RendererType = RENDERER_TYPE.CE_RENDERER_NULL;
	private List<CERenderCommand> m_RenderingQue = new ArrayList<CERenderCommand>();
}
