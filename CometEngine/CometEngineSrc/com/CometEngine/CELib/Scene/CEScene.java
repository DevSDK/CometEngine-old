package com.CometEngine.CELib.Scene;

import java.util.LinkedList;

import com.CometEngine.CELib.Node.CECamera;
import com.CometEngine.CELib.Node.CERenderableNode;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Commend.Manager.CERenderCommandManager;

public class CEScene extends CERenderableNode /* extends root class ex) Node*/ {
	
	CECamera DefaultCamera = new CECamera();

	CETestSprite sprite;
	public CEScene() {
		super(NODE_TYPE.CE_SCENE);
		 sprite = new CETestSprite("5.png");
		}

	@Override
	public CERenderCommandCustom genRenderCommand() {
		final LinkedList<CERenderCommand> ChildCommandList = new LinkedList<CERenderCommand>();
		
	 final CERenderCommand  commandsprite = sprite.genRenderCommand();
		
	 
	 	CESceneManager.getInstence().nowRenderCamera =  DefaultCamera; 
		
	 	CERenderCommandCustom custom = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
			@Override
			public void invoke() {
				Drawing();
				//test
				commandsprite.execute();
				for(CERenderCommand command : ChildCommandList)
				{
					command.execute();
				}
			}
		});
		 //
		return custom;
	}

	@Override
	public void onDraw() {
		
	}





	@Override
	public void onInit() {
		// TODO Auto-generated method stub
		
	}
	

}
