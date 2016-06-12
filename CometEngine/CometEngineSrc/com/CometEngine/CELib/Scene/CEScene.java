package com.CometEngine.CELib.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CELib.Camera.CE2DDefaultCamera;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Node.CENode;
import com.CometEngine.CELib.Node.CENode2D;
import com.CometEngine.CELib.Node.CENode3D;
import com.CometEngine.CELib.Node.CERenderableNode;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Commend.Manager.CERenderCommandManager;
import com.CometEngine.Util.Meth.CEPosition2D;

public class CEScene extends CERenderableNode /* extends root class ex) Node*/ {
	
	private CECamera DefaultCamera = new CE2DDefaultCamera();
	
	//Maybe move to Node
	
	
	CESprite2D sprite;
	CESprite2D sprite2;
	CESprite2D sprite3;
	public CEScene() {

		sprite = new CESprite2D("0.png");
		this.add(sprite);
		
		sprite2 = new CESprite2D("1.png");
		sprite3 = new CESprite2D("2.png");
	
		this.add(sprite2,2);
		this.add(sprite3);

	}
	
	int timer = 0;
	
	
	public void tick ()
	{
		timer ++;
		CEPosition2D pos = sprite.getPosition();
		
		sprite.setAngle(sprite.getAngle() + 0.1f);
		sprite.getPosition().x += 0.01f;
		sprite.getPosition().y += 0.01f;
		
		
		sprite.getScale().x -= 0.01f;
		if(sprite.getScale().x < 0) sprite.getScale().x = 0;
		
		CEPosition2D pos2 = sprite2.getPosition();
		sprite2.setAngle(sprite2.getAngle() + 0.0f);
		
		pos2.x += 0.02f;
		pos2.y += 0.02f;
		
		

		CEPosition2D pos3 = sprite3.getPosition();
		sprite3.setAngle(sprite3.getAngle() + 0.02f);
		sprite3.getPosition().x += 0.01f;
		sprite3.getPosition().y += 0.01f;
	
		
		
	}
	
	private void VisitAndGenChildCommands()
	{
		for(CENode child : ChildList)
		{
			if(CERenderableNode.class.isAssignableFrom(child.getClass()))
			{
					RenderingList.add((CERenderableNode)child);				
			}
			
		}

	}
	
	
 	private final List<CERenderableNode> RenderingList = new ArrayList<CERenderableNode>();

	private void UpdateRenderList()
	{
		RenderingList.clear();
		VisitAndGenChildCommands();
	}
	
		private CERenderCommandCustom SpriteRenderCommand = new CERenderCommandCustom(new CERenderCustomCommandInvoker() {
		@Override
		public void invoke() {
			
			if(isChildUpdated == true)
				{
					UpdateRenderList();
					isChildUpdated = false;
				}
			
			CESceneManager.getInstence().nowRender2DCamera =  DefaultCamera; 
		
			Drawing();
			
			
			for(int i = 0 ; i< RenderingList.size(); i++)
			{
				CERenderCommand command = RenderingList.get(i).genRenderCommand();
				if(command != null)
				{
					if(CENode2D.class.isAssignableFrom(RenderingList.get(i).getClass()))
					{
						command.execute();
					}
					else if(CENode3D.class.isAssignableFrom(RenderingList.get(i).getClass()))
					{
						
						command.execute();
					}
				}
			}
			
		}
	});
	
	
	@Override
	public CERenderCommandCustom genRenderCommand() {
		return SpriteRenderCommand;
	}

	@Override
	public void onDraw() {
		
	}





	@Override
	public void onInit() {
		// TODO Auto-generated method stub
		
	}
	

}
