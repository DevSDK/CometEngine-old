package com.CometEngine.CELib.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Camera.CE2DDefaultCamera;
import com.CometEngine.CELib.Camera.CECamera;
import com.CometEngine.CELib.Node.CEObject;
import com.CometEngine.CELib.Node.CERenderableObject;
import com.CometEngine.CELib.Text.CETextLabel;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Event.CEEvent;
import com.CometEngine.Event.CEEventDispatcher;
import com.CometEngine.Event.CEEventKeyboard;
import com.CometEngine.Event.CEEventListenerKeyboard;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Commend.Manager.CERenderCommandManager;
import com.CometEngine.Util.Meth.CEPosition2D;


public class CEScene extends CERenderableObject /* extends root class ex) Node*/ {
	
	private CECamera DefaultCamera = new CE2DDefaultCamera();
	
	//Maybe move to Node
	  
	CETextLabel label;
	CETextLabel label2;
	CEObject sprite;
	CESprite2D sprite2;
	CESprite2D sprite3;
	CETextLabel FPSCounter;
	
	public CEScene() {

		sprite = new CESprite2D("0.png");
		
		sprite2 = new CESprite2D("1.png");
		sprite3 = new CESprite2D("2.png");
		 label = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, false,   "Timer");
		 label2 = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt" ), 0.8f, true,"Comet Engine Tester" ,"DSM !","CometEngineTester","CometEngineTester","CometEngineTester" );


		CEEventListenerKeyboard listener =  CEEventListenerKeyboard.CreateWithScene(this);
		
		listener.CallBack = new CEEventListenerKeyboard.KeyBoardEventCallBack() {
			
			@Override
			public void KeyBoardEvent(CEEventKeyboard event) {
		
					if(event.isKeyPush(CEKeyBoard.CE_KEY_UP, CEKeyBoard.CE_KEY_REPEAT))
					{
							label.getPosition().y += 3;						
					}
					if(event.isKeyPush(CEKeyBoard.CE_KEY_DOWN, CEKeyBoard.CE_KEY_REPEAT))
					{
						label.getPosition().y += -3;
					}
					if(event.isKeyPush(CEKeyBoard.CE_KEY_RIGHT, CEKeyBoard.CE_KEY_REPEAT))
					{
						label.getPosition().x += 3;
					}
					if(event.isKeyPush(CEKeyBoard.CE_KEY_LEFT, CEKeyBoard.CE_KEY_REPEAT))
					{
						label.getPosition().x += - 3;
					}
					
				}
		};
		CEEventDispatcher.getInstance().addEventListener(listener);
		
		 label2.getPosition().y = 1000;
		 label2.getControlPoint().x = 0;
		 label2.getControlPoint().y = 1f;
		 this.add(label2,0);
		 label.getControlPoint().x = 0;
		 label.getControlPoint().y = 1;
		 label.getPosition().x = 300;
		 label.getPosition().y = 500;
		 this.add(label,4);
		
		this.add(sprite2,2);
		this.add(sprite3);
		FPSCounter = CETextLabel.CreateBMPText(CEBMPFont.create("font.png", "font.fnt"), 1f, false,   "FPS : ");
		FPSCounter.getControlPoint().y  =0;
		FPSCounter.getControlPoint().x  =0;
		this.add(FPSCounter);
	}
	
	int timer = 0;
	
	
	public void tick ()
	{
		timer ++;
		CEPosition2D pos = sprite.getPosition();
		
		sprite.setAngle(sprite.getAngle() + 0.1f);
		sprite.getPosition().x += 0.5f;
		sprite.getPosition().y += 0.5f;
		
		label.setString("Tick :  " + timer );
		FPSCounter.setString("FPS : " + CometEngine.getInstance().getRenderer().getFPS());
		CEPosition2D pos2 = sprite2.getPosition();
		sprite2.setAngle(sprite2.getAngle() + 0.0f);
		
		pos2.x += 0.02f;
		pos2.y += 0.02f;
		
		
		CEPosition2D pos3 = sprite3.getPosition();
		sprite3.setAngle(sprite3.getAngle() - 0.02f);
		sprite3.getPosition().x += 0.5f;
		sprite3.getPosition().y += 0.5f;
		
		
	
		
		
	}
	
	private void VisitAndGenChildCommands()
	{
		for(CEObject child : ChildList)
		{
			if(CERenderableObject.class.isAssignableFrom(child.getClass()))
			{
					RenderingList.add((CERenderableObject)child);				
			}	
		}
	}
	
	
 	private final List<CERenderableObject> RenderingList = new ArrayList<CERenderableObject>();

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
			
			CometEngine.getInstance().getSceneManager().nowRender2DCamera =  DefaultCamera; 
		
			Drawing();
			
			
			for(int i = 0 ; i< RenderingList.size(); i++)
			{
				CERenderCommand command = RenderingList.get(i).genRenderCommand();
				if(command != null)
				{
						
						command.execute();
			
				}			
		      }}
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
		
	}
	

}
